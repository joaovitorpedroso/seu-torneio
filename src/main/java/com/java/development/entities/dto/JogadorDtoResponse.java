package com.java.development.entities.dto;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.Equipe;
import com.java.development.entities.Jogador;
import com.java.development.entities.Posicao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class JogadorDtoResponse {

    private Long id;

    private String nomeJogador;

    private Date dtNascJogador;

    private int numeroJogador;

    private String statusJogador;

    private String peDominante;

    private Posicao posicao;


    public static JogadorDtoResponse converterParaDtoResponse(Jogador jogador) {
        return JogadorDtoResponse.builder()
                .id(jogador.getId())
                .nomeJogador(jogador.getNomeJogador())
                .dtNascJogador(jogador.getDtNascJogador())
                .statusJogador(jogador.getStatusJogador())
                .peDominante(jogador.getPeDominante())
                .posicao(jogador.getPosicao())
                .build();
    }

    public static List<JogadorDtoResponse> toDtoResponseListJogadores(List<ContratoJogador> jogadores){
        List<Jogador> jogadoresLista = new ArrayList<>();

        for(int i = 0; i<jogadores.size();i++){
            jogadoresLista.add(jogadores.get(i).getJogador());
        }

        return jogadoresLista.stream().map(JogadorDtoResponse::converterParaDtoResponse).toList();
    }

    public static List<JogadorDtoResponse> toDtoResponseListJogadoresPorContrato(List<Jogador> jogadores){

        return jogadores.stream().map(JogadorDtoResponse::converterParaDtoResponse).toList();
    }

}
