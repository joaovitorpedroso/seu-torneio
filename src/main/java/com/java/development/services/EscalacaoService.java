package com.java.development.services;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.EscalacaoJogadorHistorico;
import com.java.development.entities.Partida;
import com.java.development.entities.PartidaEquipe;
import com.java.development.entities.dto.EscalacaoEquipeDtoResponse;
import com.java.development.entities.dto.EscalacaoPartidaDtoResponse;
import com.java.development.repositories.*;
import jakarta.transaction.Transactional;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EscalacaoService {

    @Autowired
    private PartidaEquipeRepository partidaEquipeRepository;

    @Autowired
    private EdicaoCampeonatoRepository edicaoCampeonatoRepository;

    @Autowired
    private InscricaoJogadorCampeonatoRepository inscricaoJogadorCampeonatoRepository;

    @Autowired
    private ContratoJogadorRepository contratoJogadorRepository;


    @Autowired
    private EscalacaoJogadorHistoricoRepository escalacaoJogadorHistoricoRepository;

    @Transactional
    public EscalacaoPartidaDtoResponse escalarJogadoresAutomatico(Long idPartida){
        List<PartidaEquipe> dadosPartidaEquipe = partidaEquipeRepository.findPartidaEquipeByIdPartida(idPartida);
        List<List<EscalacaoJogadorHistorico>> jogadoresEscalados = new ArrayList<>();
        if (dadosPartidaEquipe.size()>2) throw new ExecutionException("Mais de 2 equipes na mesma partida");//TODO melhorar trativa
        for(PartidaEquipe partidaEquipe : dadosPartidaEquipe){
            escalarEquipeAutomaticamente(partidaEquipe,idPartida);
            //partidaEquipeRepository.save(partidaEquipe);
            jogadoresEscalados.add(escalacaoJogadorHistoricoRepository.findJogadoresByIdEquipe(partidaEquipe.getEquipe().getIdEquipe()));
        }


        return EscalacaoPartidaDtoResponse.toDtoResponseList(jogadoresEscalados);
    }

    /*@Transactional
    public EscalacaoPartidaDtoResponse escalarJogadoresAutomatico2(Long idPartida){
        List<Escalacao> escalacoes = new ArrayList();
        List<PartidaEquipe> dadosPartidaEquipe = partidaEquipeRepository.findPartidaEquipeByIdPartida(idPartida);
        if (dadosPartidaEquipe.size()>2) throw new ExecutionException("Mais de 2 equipes na mesma partida");//TODO melhorar trativa


        for(PartidaEquipe partidaEquipe : dadosPartidaEquipe){
            Escalacao escalacao = new Escalacao();
            if(partidaEquipe.getEscalacao()!=null){
                escalacao = partidaEquipe.getEscalacao();
            }
            escalacao.setHistoricos(escalarEquipeAutomaticamente2(partidaEquipe));
            escalacao = escalacaoRepository.save(escalacao);
            partidaEquipe.setEscalacao(escalacao);
            partidaEquipeRepository.save(partidaEquipe);
            escalacoes.add(escalacao);
            //jogadoresEscalados.add(escalacaoJogadorHistoricoRepository.findJogadoresByIdEquipeAndIdEscalacao(escalacao.getIdEscalacao(),partidaEquipe.getEquipe().getIdEquipe()));
        }

        return EscalacaoPartidaDtoResponse.toDtoResponseList2(escalacoes);
    }*/

    @Transactional//TODO refatorar esse método
    public void escalarEquipeAutomaticamente(PartidaEquipe partidaEquipe, Long idPartida){
        List<ContratoJogador> jogadores = contratoJogadorRepository.findByIdEquipe(partidaEquipe.getEquipe().getIdEquipe());
        List<ContratoJogador> titulares = new ArrayList<>();
        List<ContratoJogador> reservas = new ArrayList<>();
        int goleirosTitulares = 1;//TODO deixar essa e as variaveis abaixo como constantes
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

        escalarJogadoresPartida(titulares,partidaEquipe,true);
        escalarJogadoresPartida(reservas,partidaEquipe,false);
    }

    @Transactional
    public Set<EscalacaoJogadorHistorico> escalarEquipeAutomaticamente2(PartidaEquipe partidaEquipe){
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
        return (Set<EscalacaoJogadorHistorico>) escalarEquipeInteira(titulares,reservas);
    }

    @Transactional//TODO refatorar esse método
    public void escalarJogadoresPartida(List<ContratoJogador> jogadores,PartidaEquipe partidaEquipe,boolean titular){
        for(ContratoJogador jogador : jogadores) {
            EscalacaoJogadorHistorico escalacaoHistorico = new EscalacaoJogadorHistorico();
            escalacaoHistorico.setContratoJogador(jogador);
            escalacaoHistorico.setStatus(titular ? "TITULAR" : "RESERVA");
            escalacaoHistorico.setPosicao(jogador.getJogador().getPosicao());
            escalacaoHistorico.setPartidaEquipe(partidaEquipe);
            escalacaoJogadorHistoricoRepository.save(escalacaoHistorico);
        }

    }

    @Transactional
    public List<EscalacaoJogadorHistorico> escalarEquipeInteira(List<ContratoJogador> jogadoresTitulares, List<ContratoJogador> jogadoresReservas){
        List<EscalacaoJogadorHistorico> escalacaoCompleta = new ArrayList<>();
        for(ContratoJogador jogador : jogadoresTitulares) {
            EscalacaoJogadorHistorico jogadorEscalado = new EscalacaoJogadorHistorico();
            jogadorEscalado.setContratoJogador(jogador);
            jogadorEscalado.setStatus("TITULAR");
            jogadorEscalado.setPosicao(jogador.getJogador().getPosicao());
            escalacaoCompleta.add(jogadorEscalado);
        }

        for(ContratoJogador jogador : jogadoresTitulares) {
            EscalacaoJogadorHistorico jogadorEscalado = new EscalacaoJogadorHistorico();
            jogadorEscalado.setContratoJogador(jogador);
            jogadorEscalado.setStatus("RESERVA");
            jogadorEscalado.setPosicao(jogador.getJogador().getPosicao());
            escalacaoCompleta.add(jogadorEscalado);
        }
        return escalacaoCompleta;
    }

    public EscalacaoEquipeDtoResponse listarEscalacaoPorTime(Long idEquipe){

        return EscalacaoEquipeDtoResponse.toDtoResponseListEquipeUnica(escalacaoJogadorHistoricoRepository.findJogadoresByIdEquipe(idEquipe));
    }

    @Transactional
    public List<List<EscalacaoJogadorHistorico>> listarEscalacoesCompletasDaPartida(Partida partida) throws Exception {

        List<PartidaEquipe> equipes = partidaEquipeRepository.findPartidaEquipeByIdPartida(partida.getIdPartida());


        List<List<EscalacaoJogadorHistorico>> jogadoresEscalados = new ArrayList<>();
        if (equipes.size()>2) throw new ExecutionException("Mais de 2 equipes na mesma partida");//TODO melhorar trativa
        for(PartidaEquipe partidaEquipe : equipes){
            jogadoresEscalados.add(escalacaoJogadorHistoricoRepository.findJogadoresByIdEquipeAndIdPartida(partidaEquipe.getPartida().getIdPartida(),partidaEquipe.getEquipe().getIdEquipe()));
            //jogadoresEscalados.add(escalacaoJogadorHistoricoRepository.findJogadoresByIdPartida(partidaEquipe.getEquipe().getIdEquipe(),partidaEquipe.getPartida().getIdPartida()));
        }
        return jogadoresEscalados;
    }
}
