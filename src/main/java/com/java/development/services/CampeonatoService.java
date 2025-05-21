package com.java.development.services;

import com.java.development.entities.Campeonato;
import com.java.development.entities.dto.CampeonatoDtoRequest;
import com.java.development.entities.dto.CampeonatoDtoResponse;
import com.java.development.repositories.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampeonatoService {

    @Autowired
    CampeonatoRepository campeonatoRepository;

    public CampeonatoDtoResponse criarCampeonato(CampeonatoDtoRequest campeonatoDtoRequest){
        Campeonato campeonato = campeonatoDtoRequest.converterParaEntity(campeonatoDtoRequest);
        return CampeonatoDtoResponse.toDtoResponse(campeonatoRepository.save(campeonato));
    }
}
