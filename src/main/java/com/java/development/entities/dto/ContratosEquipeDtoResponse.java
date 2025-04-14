package com.java.development.entities.dto;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.ContratoTecnico;
import com.java.development.entities.Equipe;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Builder
@Getter
@Setter
public class ContratosEquipeDtoResponse {

    private Long idEquipe;

    private String nomeEquipe;

    private Date fundacao;

    private String presidente;

    private List<ContratoJogadorDtoResponse> jogadores;

    private List<ContratoTecnicoDtoResponse> tecnicos;

    public static ContratosEquipeDtoResponse toDtoResponseList(Optional<Equipe> equipe, List<ContratoJogador> jogadores, List<ContratoTecnico> tecnicos){
        return ContratosEquipeDtoResponse.builder()
                .idEquipe(equipe.get().getIdEquipe())
                .nomeEquipe(equipe.get().getNomeEquipe())
                .fundacao(equipe.get().getFundacao())
                .presidente(equipe.get().getPresidente())
                .jogadores(ContratoJogadorDtoResponse.toDtoResponseList(jogadores))
                .tecnicos(ContratoTecnicoDtoResponse.toDtoResponseList(tecnicos))
                .build();
    }
}
