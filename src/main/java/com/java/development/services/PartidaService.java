package com.java.development.services;

import com.java.development.entities.Equipe;
import com.java.development.entities.Partida;
import com.java.development.entities.PartidaEquipe;
import com.java.development.entities.dto.AdminPartidaDtoRequest;
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

    private void criarPartidaAdmin(AdminPartidaDtoRequest request){
        /*Long idTimeMandante = request.getIdEquipeMandante();
        Long idTimeVisitante = request.getIdEquipeMandante();

        Optional<Equipe> equipeMandante = equipeRepository.findById(idTimeMandante);
        Optional<Equipe> equipeVisitante = equipeRepository.findById(idTimeVisitante);
        //Partida partida = criarNovaPartida();
        Optional<Partida> Partida = partidaRepository.save()
        PartidaEquipe dadosPartidaMandante;*/
    }

    private void criarDadosPartida(Equipe equipe,boolean mandante){
        PartidaEquipe equipePartida = new PartidaEquipe();
        equipePartida.setEquipe(equipe);
        equipePartida.setSituacao(mandante?"MANDANTE":"VISITANTE");//TODO: carregar por ENUM
        //equipePartida.setPartida();
        /*Optional<Equipe> equipeMandante = equipeRepository.findById(idTimeMandante);
        Optional<Equipe> equipeVisitante = equipeRepository.findById(idTimeVisitante);

        PartidaEquipe dadosPartidaMandante = partidaEquipeRepository.save()*/
    }

    private void criarNovaPartida(){
        Partida partida = new Partida();
        partida.setDataPartida(new Date());
        partida.setEstadio("Maracanã");
    }
}
