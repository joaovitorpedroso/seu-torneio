package com.java.development.services;

import com.java.development.entities.Jogador;
import com.java.development.entities.Posicao;
import com.java.development.entities.operation.OperationMessage;
import com.java.development.repositories.PosicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosicaoService {

    @Autowired
    private PosicaoRepository posicaoRepository;

    public List<Posicao> findAll(){
        return posicaoRepository.findAll();
    }
}
