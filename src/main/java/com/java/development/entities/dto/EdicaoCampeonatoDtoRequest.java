package com.java.development.entities.dto;

import com.java.development.entities.Campeonato;
import com.java.development.entities.EdicaoCampeonato;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EdicaoCampeonatoDtoRequest {

    private String nomeEdicao;

    private int ano;

    private String descricaoEdicao;

    private Date dtInicio;

    private Date dtFim;

    private Long idCampeonato;


    public EdicaoCampeonato converterParaEntity(EdicaoCampeonatoDtoRequest edicaoCampeonatoDtoRequest,Campeonato campeonato){
        EdicaoCampeonato edicaoCampeonato = new EdicaoCampeonato();
        edicaoCampeonato.setNomeEdicao(edicaoCampeonatoDtoRequest.getNomeEdicao());
        edicaoCampeonato.setDescricaoEdicao(edicaoCampeonato.getDescricaoEdicao());
        edicaoCampeonato.setAno(edicaoCampeonatoDtoRequest.getAno());
        edicaoCampeonato.setDtInicio(edicaoCampeonatoDtoRequest.getDtInicio());
        edicaoCampeonato.setDtFim(edicaoCampeonatoDtoRequest.getDtFim());
        edicaoCampeonato.setCampeonato(campeonato);
        return edicaoCampeonato;
    }
}
