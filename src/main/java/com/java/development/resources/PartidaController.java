package com.java.development.resources;

import com.java.development.entities.dto.EscalacaoEquipeDtoResponse;
import com.java.development.services.EscalacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/partida")
public class PartidaController {

    @Autowired
    private EscalacaoService escalacaoService;

    @GetMapping("/{idPartida}/escalacao/{idEquipe}")
    public ResponseEntity<EscalacaoEquipeDtoResponse> findJogadores(@PathVariable Long idPartida, @PathVariable Long idEquipe) {
        return ResponseEntity.ok().body(escalacaoService.listarEscalacaoPorTime(idPartida,idEquipe));
    }
}
