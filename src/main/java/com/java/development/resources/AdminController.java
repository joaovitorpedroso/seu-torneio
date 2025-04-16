package com.java.development.resources;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.Equipe;
import com.java.development.entities.Jogador;
import com.java.development.entities.dto.ContratosEquipeDtoResponse;
import com.java.development.entities.dto.EquipeDtoRequest;
import com.java.development.entities.dto.EquipeDtoResponse;
import com.java.development.entities.dto.JogadorDtoRequest;
import com.java.development.services.AdminService;
import com.java.development.services.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PutMapping(value="/equipe")
    public ResponseEntity<ContratosEquipeDtoResponse> listarTodosContratosEquipePorIdEquipe(@RequestBody EquipeDtoRequest equipe){
        return ResponseEntity.ok().body(adminService.criarEquipeCompleta(equipe));
    }

    /*@PutMapping(value="/jogadores")
    //public ResponseEntity<ContratosEquipeDtoResponse> listarTodosContratosEquipePorIdEquipe(@RequestBody EquipeDtoRequest equipe){
    public List<Jogador> listarTodosContratosEquipePorIdEquipe(){
        return adminService.criarJogadoresFakes();
    }

    @GetMapping(value="/contratos")
    //public ResponseEntity<ContratosEquipeDtoResponse> listarTodosContratosEquipePorIdEquipe(@RequestBody EquipeDtoRequest equipe){
    public List<ContratoJogador> listarTodosContratosCriados(){
        return adminService.criarJogadoresEContratos();
    }*/
}
