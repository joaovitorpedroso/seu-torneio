package com.java.development.services;

import com.java.development.entities.ContratoJogador;
import com.java.development.repositories.ContratoJogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoJogadorService {

    @Autowired
    private ContratoJogadorRepository contratoJogadorRepository;

    public List<ContratoJogador> findAll(){
        return contratoJogadorRepository.findAll();
    }

}
