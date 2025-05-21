package com.java.development.entities.dto;

import com.java.development.entities.Equipe;
import com.java.development.entities.Jogador;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EquipeDtoRequest {

    public Long idEquipe;

    private String nomeEquipe;

    private Date fundacao;

    private String presidente;

    public static Equipe converterParaEntity(EquipeDtoRequest equipeDtoRequest){
        Equipe equipe = new Equipe();
        equipe.setNomeEquipe(equipeDtoRequest.getNomeEquipe());
        equipe.setFundacao(equipeDtoRequest.getFundacao());
        equipe.setFundacao(equipeDtoRequest.getFundacao());
        return equipe;
    }
}
