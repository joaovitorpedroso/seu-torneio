package com.java.development.entities.dto;

import com.java.development.entities.Campeonato;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampeonatoDtoRequest {

    private String nome;

    public Campeonato converterParaEntity(CampeonatoDtoRequest campeonatoDtoRequest){
        Campeonato campeonato = new Campeonato();
        campeonato.setNomeCampeonato(campeonatoDtoRequest.getNome());
        return campeonato;
    }

}
