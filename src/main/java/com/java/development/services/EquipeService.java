package com.java.development.services;

import com.java.development.entities.Equipe;
import com.java.development.entities.dto.ContratosEquipeDtoResponse;
import com.java.development.entities.dto.EquipeDtoResponse;
import com.java.development.repositories.ContratoJogadorRepository;
import com.java.development.repositories.ContratoTecnicoRepository;
import com.java.development.repositories.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipeService {
    @Autowired
    public EquipeRepository equipeRepository;

    @Autowired
    public ContratoJogadorRepository contratoJogadorRepository;

    @Autowired
    public ContratoTecnicoRepository contratoTecnicoRepository;

    public EquipeDtoResponse findById(Long id){
        Optional<Equipe> equipe = equipeRepository.findById(id);
        return EquipeDtoResponse.toDtoResponseListJogadores(equipe,contratoJogadorRepository.findByIdEquipe(id).stream().toList());
    }

    public ContratosEquipeDtoResponse findContratosEquipeById(Long id) {
        Optional<Equipe> equipe = equipeRepository.findById(id);
        return ContratosEquipeDtoResponse.toDtoResponseList(equipe.stream().toList().get(0),contratoJogadorRepository.findByIdEquipe(id).stream().toList(),
                contratoTecnicoRepository.findByIdEquipe(id).stream().toList());
    }

}
