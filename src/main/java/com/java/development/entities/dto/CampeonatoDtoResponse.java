package com.java.development.entities.dto;

import com.java.development.entities.Campeonato;
import com.java.development.entities.ContratoTecnico;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CampeonatoDtoResponse {

    public Long id;

    public String nomeCampeonato;

    public static CampeonatoDtoResponse toDtoResponse(Campeonato campeonato) {
        return CampeonatoDtoResponse.builder()
                .id(campeonato.getIdCampeonato())
                .nomeCampeonato(campeonato.getNomeCampeonato())
                .build();
    }
}
