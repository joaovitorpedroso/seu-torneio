package com.java.development.entities.dto;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.Equipe;
import com.java.development.entities.Jogador;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Builder
@Getter
@Setter
public class EquipeDtoResponse {

    private Long idEquipe;

    private String nomeEquipe;

    private Date fundacao;

    private String presidente;

    private List<JogadorDtoResponse> jogadores;

    public static EquipeDtoResponse toDtoResponseListJogadores(Optional<Equipe> equipe,List<ContratoJogador> jogadores){
        return EquipeDtoResponse.builder()
                .idEquipe(equipe.get().getIdEquipe())
                .nomeEquipe(equipe.get().getNomeEquipe())
                .fundacao(equipe.get().getFundacao())
                .presidente(equipe.get().getPresidente())
                .jogadores(JogadorDtoResponse.toDtoResponseListJogadores(jogadores))
                .build();
    }
}
