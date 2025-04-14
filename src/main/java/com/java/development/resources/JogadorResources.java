package com.java.development.resources;

import com.java.development.entities.Jogador;
import com.java.development.entities.dto.JogadorDtoRequest;
import com.java.development.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/jogador")
public class JogadorResources {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    public ResponseEntity<List<Jogador>> findJogadores(){
        List<Jogador> list = jogadorService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Jogador> findJogadorById(@PathVariable Long id){
        Jogador obj = jogadorService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping
    public ResponseEntity<Jogador> criarJogador(@RequestBody JogadorDtoRequest jogadorDtoRequest){
        Jogador obj = jogadorService.criarJogadorRequest(jogadorDtoRequest.toDtoResponse(jogadorDtoRequest));
        return ResponseEntity.ok().body(obj);
    }
}
