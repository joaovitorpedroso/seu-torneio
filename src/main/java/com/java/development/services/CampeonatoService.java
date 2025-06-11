package com.java.development.services;

import com.java.development.entities.Campeonato;
import com.java.development.entities.EdicaoCampeonato;
import com.java.development.entities.FaseCampeonato;
import com.java.development.entities.dto.CampeonatoDtoRequest;
import com.java.development.entities.dto.CampeonatoDtoResponse;
import com.java.development.entities.dto.EdicaoCampeonatoDtoRequest;
import com.java.development.entities.dto.FaseCampeonatoDtoRequest;
import com.java.development.repositories.CampeonatoRepository;
import com.java.development.repositories.EdicaoCampeonatoRepository;
import com.java.development.repositories.FaseCampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CampeonatoService {

    @Autowired
    CampeonatoRepository campeonatoRepository;

    @Autowired
    EdicaoCampeonatoRepository edicaoCampeonatoRepository;

    @Autowired
    FaseCampeonatoRepository faseCampeonatoRepository;

    public CampeonatoDtoResponse criarCampeonato(CampeonatoDtoRequest campeonatoDtoRequest){
        Campeonato campeonato = campeonatoDtoRequest.converterParaEntity(campeonatoDtoRequest);
        return CampeonatoDtoResponse.toDtoResponse(campeonatoRepository.save(campeonato));
    }

    public String criarEdicao(EdicaoCampeonatoDtoRequest edicaoCampeonatoDtoRequest){
        Campeonato campeonato = campeonatoRepository.findById(edicaoCampeonatoDtoRequest.getIdCampeonato()).stream().toList().get(0);
        EdicaoCampeonato edicaoCampeonato = edicaoCampeonatoDtoRequest.converterParaEntity(edicaoCampeonatoDtoRequest,campeonato);
        edicaoCampeonatoRepository.save(edicaoCampeonato);
        return "ok";
    }

    public String criarFaseCampeonato(FaseCampeonatoDtoRequest faseCampeonatoDtoRequest){
        EdicaoCampeonato edicaoCampeonato = edicaoCampeonatoRepository.findById(faseCampeonatoDtoRequest.getIdEdicaoCampeonato()).stream().toList().get(0);
        FaseCampeonato faseCampeonato = faseCampeonatoDtoRequest.converterParaEntity(faseCampeonatoDtoRequest,edicaoCampeonato);
        faseCampeonatoRepository.save(faseCampeonato);
        return "OK";
    }
}
