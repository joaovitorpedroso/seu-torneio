package com.java.development.services;

import com.java.development.entities.*;
import com.java.development.entities.dto.AdminPartidaDtoRequest;
import com.java.development.entities.dto.PartidaDtoResponse;
import com.java.development.repositories.*;
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
    private InscricaoJogadorCampeonatoRepository inscricaoJogadorCampeonatoRepository;

    @Autowired
    private ContratoJogadorRepository contratoJogadorRepository;

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
}
