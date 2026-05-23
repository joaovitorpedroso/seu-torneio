package com.java.development.resources;

import com.java.development.entities.dto.EscalacaoEquipeDtoResponse;
import com.java.development.entities.dto.EscalacaoPartidaDtoResponse;
import com.java.development.entities.dto.PartidaDtoResponse;
import com.java.development.services.EscalacaoService;
import com.java.development.services.PartidaService;
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
    @Autowired
    private PartidaService partidaService;

    @GetMapping("/escalacao-equipe/{idEquipe}")
    public ResponseEntity<EscalacaoEquipeDtoResponse> findJogadores(@PathVariable Long idEquipe) {
        return ResponseEntity.ok().body(escalacaoService.listarEscalacaoPorTime(idEquipe));
    }

    @GetMapping("/{idPartida}")
    public ResponseEntity<PartidaDtoResponse> consultarPartida(@PathVariable Long idPartida) throws Exception {
        return ResponseEntity.ok().body(partidaService.consultarPartidaPorId(idPartida));
    }

    @GetMapping("/escalacao-completa/{idPartida}")
    public ResponseEntity<EscalacaoPartidaDtoResponse> consultarEscalacaoParaPartida(@PathVariable Long idPartida) throws Exception {
        return ResponseEntity.ok().body(partidaService.consultarEscalacaoCompletaPorId(idPartida));
    }


}
