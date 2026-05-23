package com.java.development.entities.dto;

import com.java.development.entities.EscalacaoJogadorHistorico;
import com.java.development.entities.Partida;
import com.java.development.entities.enums.SituacaoPartida;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class EscalacaoPartidaDto {

    List<EscalacaoEquipeDto> equipes;

    EscalacaoEquipeDto mandante;

    EscalacaoEquipeDto visitante;

    Partida partida;

    //TODO criar atributos para separar visitante do mandante

    public static EscalacaoPartidaDto toDtoList(List<List<EscalacaoJogadorHistorico>> equipesEscaladas) {
        List<EscalacaoEquipeDto> equipesEscaladasDtoResponse = new ArrayList<>();
        EscalacaoEquipeDto mandante = new EscalacaoEquipeDto();
        EscalacaoEquipeDto visitante = new EscalacaoEquipeDto();
        for(List<EscalacaoJogadorHistorico> escalacaoPorEquipe : equipesEscaladas){
            List<EscalacaoJogadorHistorico> titulares = new ArrayList<>();
            List<EscalacaoJogadorHistorico> reservas = new ArrayList<>();
            for(EscalacaoJogadorHistorico jogadorEscalado : escalacaoPorEquipe){

                if(jogadorEscalado.getStatus().equalsIgnoreCase("TITULAR"))
                    titulares.add(jogadorEscalado);
                else
                    reservas.add(jogadorEscalado);
            }
            if(escalacaoPorEquipe.get(0).getPartidaEquipe().getSituacao().equals(SituacaoPartida.MANDANTE)) {
                mandante = EscalacaoEquipeDto.toDtoList(titulares,reservas);
            }
            else {
                visitante = EscalacaoEquipeDto.toDtoList(titulares,reservas);
            }
            equipesEscaladasDtoResponse.add(EscalacaoEquipeDto.toDtoList(titulares,reservas));
        }
        return EscalacaoPartidaDto.builder().equipes(equipesEscaladasDtoResponse)
                .mandante(mandante)
                .visitante(visitante)
                .partida(mandante.getPartidaEquipe().getPartida())
                .build();
    }
}
