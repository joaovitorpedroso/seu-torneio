package com.java.development.resources;

import com.java.development.entities.ContratoJogador;
import com.java.development.services.ContratoJogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/contrato")
public class ContratoController {

    @Autowired
    private ContratoJogadorService contratoJogadorService;

    @GetMapping
    public ResponseEntity<List<ContratoJogador>> listarTodosContratos(){
        List<ContratoJogador> list = contratoJogadorService.findAll();
        return ResponseEntity.ok().body(list);
    }
}




