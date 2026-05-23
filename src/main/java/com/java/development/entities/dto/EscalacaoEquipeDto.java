package com.java.development.entities.dto;

import com.java.development.entities.EscalacaoJogadorHistorico;
import com.java.development.entities.PartidaEquipe;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EscalacaoEquipeDto {

    String nomeEquipe;
    String situacaoEquipe;//TODO utilizar esse campo na exibicao
    List<EscalacaoJogadorHistorico> titulares;
    List<EscalacaoJogadorHistorico> reservas;
    PartidaEquipe partidaEquipe;


    public static EscalacaoEquipeDto toDtoList(List<EscalacaoJogadorHistorico> titularesEscalados, List<EscalacaoJogadorHistorico> reservasEscalados){
        return EscalacaoEquipeDto.builder()
                .titulares(titularesEscalados)
                .reservas(reservasEscalados)
                .nomeEquipe(titularesEscalados.stream().toList().get(0).getContratoJogador().getEquipe().getNomeEquipe())
                .partidaEquipe(titularesEscalados.stream().toList().get(0).getPartidaEquipe())
                .build();
    }

    public static EscalacaoEquipeDto toDtoListEquipeUnica(List<EscalacaoJogadorHistorico> jogadoresEscalados){
        return EscalacaoEquipeDto.builder()
                .titulares(jogadoresEscalados.stream().filter(jogador -> (jogador.getStatus().equals("TITULAR"))).toList())
                .reservas(jogadoresEscalados.stream().filter(jogador -> (!jogador.getStatus().equals("TITULAR"))).toList())
                .nomeEquipe(jogadoresEscalados.stream().toList().get(0).getContratoJogador().getEquipe().getNomeEquipe())
                .build();
    }
}
