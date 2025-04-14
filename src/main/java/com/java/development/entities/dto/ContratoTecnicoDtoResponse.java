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
public class ContratoTecnicoDtoResponse {

    private Long idTecnico;

    private String nomeTecnico;

    private Date dtNascTecnico;

    public ContratoDtoResponse contrato;

    public static ContratoTecnicoDtoResponse toDtoResponse(ContratoTecnico contrato) {
        return ContratoTecnicoDtoResponse.builder()
                .idTecnico(contrato.getTecnico().getIdTecnico())
                .nomeTecnico(contrato.getTecnico().getNomeTecnico())
                .dtNascTecnico(contrato.getTecnico().getDtNascTecnico())
                .contrato(ContratoDtoResponse.toDtoResponse(contrato))
                .build();
    }

    public static List<ContratoTecnicoDtoResponse> toDtoResponseList(List<ContratoTecnico> contratos){
        return contratos.stream().map(ContratoTecnicoDtoResponse::toDtoResponse).toList();
    }
}
