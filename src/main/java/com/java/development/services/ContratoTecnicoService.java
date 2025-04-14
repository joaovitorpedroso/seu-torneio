package com.java.development.services;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.ContratoTecnico;
import com.java.development.repositories.ContratoJogadorRepository;
import com.java.development.repositories.ContratoTecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoTecnicoService {

    @Autowired
    private ContratoTecnicoRepository contratoTecnicoRepository;

    public List<ContratoTecnico> findAll(){
        return contratoTecnicoRepository.findAll();
    }
}
