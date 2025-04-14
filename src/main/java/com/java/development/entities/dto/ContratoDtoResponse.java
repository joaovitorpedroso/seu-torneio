package com.java.development.entities.dto;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.ContratoTecnico;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class ContratoDtoResponse {

    private Long idContrato;

    private Date dtInicio;

    private Date dtFim;

    private String status;

    public static ContratoDtoResponse toDtoResponse(ContratoJogador contrato){
        return ContratoDtoResponse.builder().
                idContrato(contrato.getIdContratoJogador())
                .dtInicio(contrato.getDtInicio())
                .dtFim(contrato.getDtFim())
                .status(contrato.getStatus())
                .build();
    }

    public static ContratoDtoResponse toDtoResponse(ContratoTecnico contrato){
        return ContratoDtoResponse.builder().
                idContrato(contrato.getIdContratoTecnico())
                .dtInicio(contrato.getDtInicio())
                .dtFim(contrato.getDtFim())
                .status(contrato.getStatus())
                .build();
    }
}
