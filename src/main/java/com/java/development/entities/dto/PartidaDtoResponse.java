package com.java.development.entities.dto;

import com.java.development.entities.Campeonato;
import com.java.development.entities.Equipe;
import com.java.development.entities.Partida;
import com.java.development.entities.PartidaEquipe;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class PartidaDtoResponse {

    private Long idPartida;

    private Date dataPartida;

    private int golsTotais;

    private String estadio;

    private Long idFaseCampeonato;
    public Equipe equipeMandante;
    public Equipe equipeVisitante;

    public static PartidaDtoResponse toDtoResponse(Partida partida, Equipe equipeMandante, Equipe equipeVisitante) {
        return PartidaDtoResponse.builder()
                .idPartida(partida.getIdPartida())
                .dataPartida(partida.getDataInicio())
                .golsTotais(partida.getGolsTotais())
                .estadio(partida.getEstadio())
                .idFaseCampeonato(partida.getIdFaseCampeonato())
                .equipeMandante(equipeMandante)
                .equipeVisitante(equipeVisitante)
                .build();
    }

    public static PartidaDtoResponse toDtoResponse(Partida partida, List<PartidaEquipe> equipes) {
        equipes.sort(Comparator.comparing(PartidaEquipe::getSituacao));
        return PartidaDtoResponse.toDtoResponse(partida,equipes.get(0).getEquipe(),equipes.get(1).getEquipe());
    }
}
