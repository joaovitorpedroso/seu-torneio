package com.java.development.services;

import com.java.development.entities.Equipe;
import com.java.development.entities.Partida;
import com.java.development.entities.PartidaEquipe;
import com.java.development.entities.dto.AdminPartidaDtoRequest;
import com.java.development.entities.dto.ContratosEquipeDtoResponse;
import com.java.development.entities.dto.PartidaDtoResponse;
import com.java.development.repositories.EquipeRepository;
import com.java.development.repositories.JogadorRepository;
import com.java.development.repositories.PartidaEquipeRepository;
import com.java.development.repositories.PartidaRepository;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private PartidaEquipeRepository partidaEquipeRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    public PartidaDtoResponse criarPartidaAdmin(AdminPartidaDtoRequest request){
        Long idTimeMandante = request.getIdEquipeMandante();
        Long idTimeVisitante = request.getIdEquipeMandante();

        //obter dados das duas equipes
        Equipe equipeMandante = equipeRepository.findById(idTimeMandante).stream().toList().get(0);
        Equipe equipeVisitante = equipeRepository.findById(idTimeVisitante).stream().toList().get(0);//TODO colocar validações aqui

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
}
