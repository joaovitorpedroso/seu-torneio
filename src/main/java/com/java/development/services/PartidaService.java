package com.java.development.services;

import com.java.development.entities.*;
import com.java.development.entities.dto.AdminPartidaDtoRequest;
import com.java.development.entities.dto.EscalacaoPartidaDtoResponse;
import com.java.development.entities.dto.PartidaDtoResponse;
import com.java.development.repositories.*;
import com.java.development.repositories.EscalacaoJogadorHistoricoRepository;
import jakarta.transaction.Transactional;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private PartidaEquipeRepository partidaEquipeRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private FaseCampeonatoRepository faseCampeonatoRepository;

    @Autowired
    private EdicaoCampeonatoRepository edicaoCampeonatoRepository;

    @Autowired
    private InscricaoJogadorCampeonatoRepository inscricaoJogadorCampeonatoRepository;

    @Autowired
    private ContratoJogadorRepository contratoJogadorRepository;

    @Autowired
    private EscalacaoRepository escalacaoRepository;

    @Autowired
    private EscalacaoJogadorHistoricoRepository escalacaoJogadorHistoricoRepository;

    public PartidaDtoResponse criarPartidaAdmin(AdminPartidaDtoRequest request){
        Long idEquipeMandante = request.getIdEquipeMandante();
        Long idEquipeVisitante = request.getIdEquipeVisitante();

        //obter dados das duas equipes
        Equipe equipeMandante = equipeRepository.findById(idEquipeMandante).stream().toList().get(0);
        Equipe equipeVisitante = equipeRepository.findById(idEquipeVisitante).stream().toList().get(0);//TODO colocar validações aqui

        verificarInscricaoJogadores(request.getIdFaseCampeonato(),idEquipeMandante,idEquipeVisitante);

        Partida partida = criarNovaPartida(request.getIdFaseCampeonato());
        partida = partidaRepository.save(partida);

        PartidaEquipe partidaEquipeMandante = criarDadosPartida(equipeMandante,true,partida);
        PartidaEquipe partidaEquipeVisitante = criarDadosPartida(equipeVisitante,false,partida);
        return PartidaDtoResponse.toDtoResponse(partida, equipeMandante,equipeVisitante);
    }

    private PartidaEquipe criarDadosPartida(Equipe equipe,boolean mandante,Partida partida){
        PartidaEquipe equipePartida = new PartidaEquipe();
        equipePartida.setEquipe(equipe);
        equipePartida.setSituacao(mandante?"MANDANTE":"VISITANTE");//TODO: carregar por ENUM
        equipePartida.setPartida(partida);
        return partidaEquipeRepository.save(equipePartida);
    }

    private Partida criarNovaPartida(Long idFaseCampeonato){
        Partida partida = new Partida();
        partida.setDataInicio(new Date());
        partida.setEstadio("Maracanã");
        partida.setIdFaseCampeonato(idFaseCampeonato);
        return partida;
    }

    @Transactional
    private void verificarInscricaoJogadores(Long idFaseCampeonato,Long idEquipeMandante,Long idEquipeVisitante) {
        FaseCampeonato fase = faseCampeonatoRepository.findById(idFaseCampeonato).stream().toList().get(0);
        List<ContratoJogador> jogadoresInscritosMandante = inscricaoJogadorCampeonatoRepository.findJogadoresInscritos(idEquipeMandante);
        if (jogadoresInscritosMandante.isEmpty()) {
            inscreverJogadores(idEquipeMandante,fase);
        }
        List<ContratoJogador> jogadoresInscritosVisitante = inscricaoJogadorCampeonatoRepository.findJogadoresInscritos(idEquipeVisitante);
        if (jogadoresInscritosMandante.isEmpty()) {
            inscreverJogadores(idEquipeVisitante,fase);
        }
    }

    @Transactional
    public void inscreverJogadores(Long idEquipe,FaseCampeonato faseCampeonato){

        List<ContratoJogador> jogadores = contratoJogadorRepository.findByIdEquipe(idEquipe);
        if(jogadores.isEmpty()) throw new ExecutionException("Time sem jogadores");//TODO melhorar tratativa
        for(ContratoJogador contratoJogador : jogadores.stream().toList() ) {
            InscricaoJogadorCampeonato inscricao = new InscricaoJogadorCampeonato();
            inscricao.setContratoJogador(contratoJogador);
            inscricao.setStatus("ATIVO");//TODO usar enums
            inscricao.setEdicaoCampeonato(faseCampeonato.getEdicaoCampeonato());
            inscricao.setDataInicio(Timestamp.valueOf(LocalDateTime.now()));
            inscricaoJogadorCampeonatoRepository.save(inscricao);
        }
    }

    @Transactional
    public EscalacaoPartidaDtoResponse escalarJogadoresAutomatico(Long idPartida){
        List<PartidaEquipe> dadosPartidaEquipe = partidaEquipeRepository.findPartidaEquipeByIdPartida(idPartida);
        List<List<EscalacaoJogadorHistorico>> jogadoresEscalados = new ArrayList<>();
        if (dadosPartidaEquipe.size()>2) throw new ExecutionException("Mais de 2 equipes na mesma partida");//TODO melhorar trativa
        for(PartidaEquipe partidaEquipe : dadosPartidaEquipe){
            Escalacao escalacao;
            if(partidaEquipe.getEscalacao()!=null){
                escalacao = partidaEquipe.getEscalacao();
            }
            else{
                escalacao = escalacaoRepository.save(new Escalacao());
            }
            escalarEquipeAutomaticamente(partidaEquipe,idPartida,escalacao);
            partidaEquipe.setEscalacao(escalacao);
            partidaEquipeRepository.save(partidaEquipe);
            jogadoresEscalados.add(escalacaoJogadorHistoricoRepository.findJogadoresByIdEquipeAndIdEscalacao(escalacao.getIdEscalacao(),partidaEquipe.getEquipe().getIdEquipe()));
        }

        return EscalacaoPartidaDtoResponse.toDtoResponseList(jogadoresEscalados);
    }

    @Transactional
    public void escalarEquipeAutomaticamente(PartidaEquipe partidaEquipe, Long idPartida,Escalacao escalacao){
        List<ContratoJogador> jogadores = contratoJogadorRepository.findByIdEquipe(partidaEquipe.getEquipe().getIdEquipe());
        List<ContratoJogador> titulares = new ArrayList<>();
        List<ContratoJogador> reservas = new ArrayList<>();
        int goleirosTitulares = 1;
        int zagueirosTitulares = 2;
        int lateralDireitoTitular = 1;
        int lateralEsquerdoTitular = 1;
        int meiasTitulares = 3;
        int atacantesTitulares = 3;

        int goleirosEscalados = 0;
        int zagueirosEscalados = 0;
        int lateralEsquerdoEscalado = 0;
        int lateralDireitoEscalado = 0;
        int meiasEscalados = 0;
        int atacantesEscalados = 0;
        for(ContratoJogador jogador : jogadores){

            switch (jogador.getJogador().getPosicao().getNomePosicao().toUpperCase()){
                case("GOLEIRO"):
                    if (!(goleirosEscalados==goleirosTitulares)) {
                        titulares.add(jogador);
                        goleirosEscalados++;
                    } else {
                        reservas.add(jogador);
                    };
                    break;
                case("ZAGUEIRO"):
                    if (!(zagueirosEscalados==zagueirosTitulares)) {
                        titulares.add(jogador);
                        zagueirosEscalados++;
                    } else {
                        reservas.add(jogador);
                    };
                    break;
                case("LATERAL ESQUERDO"):
                    if (!(lateralEsquerdoEscalado==lateralEsquerdoTitular)) {
                        titulares.add(jogador);
                        lateralEsquerdoEscalado++;
                    } else {
                        reservas.add(jogador);
                    };
                    break;
                case("LATERAL DIREITO"):
                    if (!(lateralDireitoEscalado==lateralDireitoTitular)) {
                        titulares.add(jogador);
                        lateralDireitoEscalado++;
                    } else {
                        reservas.add(jogador);
                    };
                    break;
                case("VOLANTE"):
                case("MEIA ARMADOR"):
                case("MEIA ESQUERDA"):
                case("MEIA DIREITA"):
                    if (!(meiasEscalados==meiasTitulares)) {
                        titulares.add(jogador);
                        meiasEscalados++;
                    } else {
                        reservas.add(jogador);
                    };
                    break;
                case("SEGUNDO ATACANTE"):
                case("PONTA DIREITA"):
                case("PONTA ESQUERDA"):
                case("CENTRO-AVANTE"):
                    if (!(atacantesEscalados==atacantesTitulares)) {
                        titulares.add(jogador);
                        atacantesEscalados++;
                    } else {
                        reservas.add(jogador);
                    };
                    break;
            }
        }

        escalarJogadoresPartida(titulares,escalacao,true);
        escalarJogadoresPartida(reservas,escalacao,false);
    }

    @Transactional
    public void escalarJogadoresPartida(List<ContratoJogador> jogadores, Escalacao escalacao,boolean titular){
        for(ContratoJogador jogador : jogadores) {
            EscalacaoJogadorHistorico escalacaoHistorico = new EscalacaoJogadorHistorico();
            escalacaoHistorico.setContratoJogador(jogador);
            escalacaoHistorico.setStatus(titular ? "TITULAR" : "RESERVA");
            escalacaoHistorico.setPosicao(jogador.getJogador().getPosicao());
            escalacaoHistorico.setEscalacao(escalacao);
            escalacaoJogadorHistoricoRepository.save(escalacaoHistorico);
        }

    }
}
