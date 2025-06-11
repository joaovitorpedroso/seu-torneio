package com.java.development.resources;

import com.java.development.entities.Jogador;
import com.java.development.services.JogadorService;
import com.java.development.services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/partida")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

}
