package com.java.development.resources;

import com.java.development.entities.dto.ContratosEquipeDtoResponse;
import com.java.development.entities.dto.EquipeDtoResponse;
import com.java.development.services.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/equipe")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @GetMapping("/elenco/{id}")
    public ResponseEntity<EquipeDtoResponse> listarTodosJogadoresPorEquipe(@PathVariable Long id) {
        return ResponseEntity.ok().body(equipeService.findById(id));
    }

    @GetMapping("/elenco/contratos/{id}")
    public ResponseEntity<ContratosEquipeDtoResponse> listarTodosContratosEquipePorIdEquipe(@PathVariable Long id) {
        return ResponseEntity.ok().body(equipeService.findContratosEquipeById(id));
    }

    /*@GetMapping("/escalacao/{id}")
    public Optional<Escalacao> listarTodosContratosEquipePorIdEquipe2(@PathVariable Long id){
        return escalacaoRepository.findById(id);
    }*/
}
