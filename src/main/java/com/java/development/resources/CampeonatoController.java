package com.java.development.resources;

import com.java.development.entities.Campeonato;
import com.java.development.entities.ContratoJogador;
import com.java.development.entities.EdicaoCampeonato;
import com.java.development.entities.dto.*;
import com.java.development.repositories.ContratoTecnicoRepository;
import com.java.development.services.CampeonatoService;
import com.java.development.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/campeonato")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;


    @PutMapping(value="/novo")
    public ResponseEntity<CampeonatoDtoResponse> criarCampeonato(@RequestBody CampeonatoDtoRequest campeonatoDtoRequest){
        return ResponseEntity.ok().body(campeonatoService.criarCampeonato(campeonatoDtoRequest));
    }

    @PutMapping(value="/edicao")
    public ResponseEntity<String> criarEdicaoCampeonato(@RequestBody EdicaoCampeonatoDtoRequest edicaoCampeonatoDtoRequest){
        campeonatoService.criarEdicao(edicaoCampeonatoDtoRequest);
        return ResponseEntity.ok().body("Criado");
    }

    @PutMapping(value="/fase")
    public ResponseEntity<String> criarFaseCampeonato(@RequestBody FaseCampeonatoDtoRequest faseCampeonatoDtoRequest){
        campeonatoService.criarFaseCampeonato(faseCampeonatoDtoRequest);
        return ResponseEntity.ok().body("Criado");
    }
}
