package com.java.development.entities.dto;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.EscalacaoJogadorHistorico;
import com.java.development.entities.Posicao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class JogadorEscaladoDtoResponse {

    public Long idJogador;
    public String nome;
    public int numeroJogador;
    public Posicao posicao;

    public static JogadorEscaladoDtoResponse toDtoResponse(EscalacaoJogadorHistorico jogador) {
        return JogadorEscaladoDtoResponse.builder()
                .idJogador(jogador.getContratoJogador().getJogador().getIdJogador())
                .nome(jogador.getContratoJogador().getJogador().getNomeJogador())
                .numeroJogador(jogador.getContratoJogador().getNumeroJogador())
                .posicao(jogador.getContratoJogador().getJogador().getPosicao())
                .build();
    }

    public static List<JogadorEscaladoDtoResponse> toDtoResponseList(List<EscalacaoJogadorHistorico> jogadores){
        return jogadores.stream().map(JogadorEscaladoDtoResponse::toDtoResponse).toList();
    }
}
