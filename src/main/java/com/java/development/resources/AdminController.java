package com.java.development.resources;

import com.java.development.entities.dto.*;
import com.java.development.services.AdminService;
import com.java.development.services.EscalacaoService;
import com.java.development.services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PartidaService partidaService;

    @Autowired
    private EscalacaoService escalacaoService;

    @PutMapping(value="/equipe")
    public ResponseEntity<ContratosEquipeDtoResponse> listarTodosContratosEquipePorIdEquipe(@RequestBody EquipeDtoRequest equipe){
        return ResponseEntity.ok().body(adminService.criarEquipeCompleta(equipe));
    }

    @PutMapping(value="/partida")
    public ResponseEntity<PartidaDtoResponse> criarPartidaRandom(@RequestBody AdminPartidaDtoRequest request){
        return ResponseEntity.ok().body(partidaService.criarPartidaAdmin(request));
        //return "partida criada";
    }

    @PutMapping(value="/partida/escalacao/{idPartida}")
    public ResponseEntity<EscalacaoPartidaDtoResponse> criarPartidaRandom(@PathVariable Long idPartida){
        return ResponseEntity.ok().body(escalacaoService.escalarJogadoresAutomatico(idPartida));
    }

    @PutMapping(value="/partida/automatica/{idPartida}")
    public ResponseEntity<String> finalizarPartida(@PathVariable Long idPartida) throws Exception {
        adminService.criarPlacarPartida(idPartida);
        return ResponseEntity.ok("Operação em andamento");
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
