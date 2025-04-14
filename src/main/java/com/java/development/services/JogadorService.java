package com.java.development.services;

import com.java.development.entities.Jogador;
import com.java.development.entities.dto.JogadorDtoRequest;
import com.java.development.entities.operation.OperationMessage;
import com.java.development.repositories.JogadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public List<Jogador> findAll(){
        return jogadorRepository.findAll();
    }

    public Jogador findById(Long id){
        Optional<Jogador> obj = jogadorRepository.findById(id);
        return obj.get();
    }

    @Transactional
    public Jogador criarJogadorRequest(Jogador jogador){
        return jogadorRepository.save(jogador);
    }
}
