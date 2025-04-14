package com.java.development.entities.dto;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.Posicao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
/*
@Getter
@Setter
@Builder
public class ContratoJogadorDtoResponse extends JogadorDtoResponse{

    public ContratoDtoResponse contrato;


    public static ContratoJogadorDtoResponse toDtoResponse(ContratoJogador contrato){
        return ContratoJogadorDtoResponse.builder()
                .contrato(ContratoDtoResponse.toDtoResponse(contrato))
                .build();
    }

    public static List<ContratoJogadorDtoResponse> toDtoResponseList(List<ContratoJogador> contratos){
        return contratos.stream().map(ContratoJogadorDtoResponse::toDtoResponse).toList();
    }
}*/

@Getter
@Setter
@Builder
public class ContratoJogadorDtoResponse {

    private Long idJogador;

    private String nomeJogador;

    private Date dtNascJogador;

    private int numeroJogador;

    private String statusJogador;

    private String peDominante;

    private Posicao posicao;

    public ContratoDtoResponse contrato;

    public static ContratoJogadorDtoResponse toDtoResponse(ContratoJogador contrato){
        return ContratoJogadorDtoResponse.builder()
                .idJogador(contrato.getJogador().getId())
                .nomeJogador(contrato.getJogador().getNomeJogador())
                .dtNascJogador(contrato.getJogador().getDtNascJogador())
                .numeroJogador(contrato.getNumeroJogador())
                .statusJogador(contrato.getJogador().getStatusJogador())
                .peDominante(contrato.getJogador().getPeDominante())
                .posicao(contrato.getJogador().getPosicao())
                .contrato(ContratoDtoResponse.toDtoResponse(contrato))
                .build();
    }

    public static List<ContratoJogadorDtoResponse> toDtoResponseList(List<ContratoJogador> contratos){
        return contratos.stream().map(ContratoJogadorDtoResponse::toDtoResponse).toList();
    }
}
