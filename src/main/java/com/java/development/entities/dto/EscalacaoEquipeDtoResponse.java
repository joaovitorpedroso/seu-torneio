package com.java.development.entities.dto;

import com.java.development.entities.EscalacaoJogadorHistorico;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EscalacaoEquipeDtoResponse {

    String nomeEquipe;
    String situacaoEquipe;//TODO utilizar esse campo na exibicao
    List<JogadorEscaladoDtoResponse> titulares;
    List<JogadorEscaladoDtoResponse> reservas;

    public static JogadorEscaladoDtoResponse toDtoResponse(EscalacaoJogadorHistorico jogadorEscalado){
        return JogadorEscaladoDtoResponse.toDtoResponse(jogadorEscalado);
    }

    public static EscalacaoEquipeDtoResponse toDtoResponseList(List<EscalacaoJogadorHistorico> titularesEscalados, List<EscalacaoJogadorHistorico> reservasEscalados){
        return EscalacaoEquipeDtoResponse.builder()
                .titulares(titularesEscalados.stream().map(EscalacaoEquipeDtoResponse::toDtoResponse).toList())
                .reservas(reservasEscalados.stream().map(EscalacaoEquipeDtoResponse::toDtoResponse).toList())
                .nomeEquipe(titularesEscalados.stream().toList().get(0).getContratoJogador().getEquipe().getNomeEquipe())
                .build();
    }

    public static EscalacaoEquipeDtoResponse toDtoResponseListEquipeUnica(List<EscalacaoJogadorHistorico> jogadoresEscalados){
        return EscalacaoEquipeDtoResponse.builder()
                .titulares(jogadoresEscalados.stream().filter(jogador -> (jogador.getStatus().equals("TITULAR"))).map(EscalacaoEquipeDtoResponse::toDtoResponse).toList())
                .reservas(jogadoresEscalados.stream().filter(jogador -> (!jogador.getStatus().equals("TITULAR"))).map(EscalacaoEquipeDtoResponse::toDtoResponse).toList())
                .nomeEquipe(jogadoresEscalados.stream().toList().get(0).getContratoJogador().getEquipe().getNomeEquipe())
                .build();
    }
}
