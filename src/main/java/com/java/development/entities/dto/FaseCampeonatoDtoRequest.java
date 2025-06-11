package com.java.development.entities.dto;

import com.java.development.entities.Campeonato;
import com.java.development.entities.EdicaoCampeonato;
import com.java.development.entities.FaseCampeonato;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaseCampeonatoDtoRequest {

    private String nomeFase;

    private int ordem;

    private Long idEdicaoCampeonato;

    public FaseCampeonato converterParaEntity(FaseCampeonatoDtoRequest faseCampeonatoDtoRequest, EdicaoCampeonato edicaoCampeonato){
        FaseCampeonato faseCampeonato = new FaseCampeonato();
        faseCampeonato.setNomeFase(faseCampeonatoDtoRequest.getNomeFase());
        faseCampeonato.setOrdem(faseCampeonatoDtoRequest.getOrdem());
        faseCampeonato.setEdicaoCampeonato(edicaoCampeonato);
        return faseCampeonato;
    }
}
