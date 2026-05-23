package com.java.development.entities.dto;

import com.java.development.entities.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class EscalacaoPartidaDtoResponse {

    List<EscalacaoEquipeDtoResponse> equipes;

    public static EscalacaoPartidaDtoResponse toDtoResponseList(List<List<EscalacaoJogadorHistorico>> equipesEscaladas) {
        List<EscalacaoEquipeDtoResponse> equipesEscaladasDtoResponse = new ArrayList<>();
        for(List<EscalacaoJogadorHistorico> escalacaoPorEquipe : equipesEscaladas){
            List<EscalacaoJogadorHistorico> titulares = new ArrayList<>();
            List<EscalacaoJogadorHistorico> reservas = new ArrayList<>();
            for(EscalacaoJogadorHistorico jogadorEscalado : escalacaoPorEquipe){
                if(jogadorEscalado.getStatus().equalsIgnoreCase("TITULAR"))
                    titulares.add(jogadorEscalado);
                else
                    reservas.add(jogadorEscalado);
            }
            equipesEscaladasDtoResponse.add(EscalacaoEquipeDtoResponse.toDtoResponseList(titulares,reservas));
        }
        return EscalacaoPartidaDtoResponse.builder().equipes(equipesEscaladasDtoResponse).build();
    }
}
