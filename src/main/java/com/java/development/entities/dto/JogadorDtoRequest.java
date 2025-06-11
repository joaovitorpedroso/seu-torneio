package com.java.development.entities.dto;

import com.java.development.entities.Jogador;
import com.java.development.entities.Posicao;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JogadorDtoRequest {

    public Long idJogador;

    public String nomeJogador;

    public Date dtNascJogador;

    public int numeroJogador;

    public String statusJogador;

    public String peDominante;

    private Posicao posicao;

    public Jogador toDtoResponse(JogadorDtoRequest jogadorDtoRequest){
        Jogador jogador = new Jogador();
        if(jogadorDtoRequest.getIdJogador()!=null&&jogadorDtoRequest.getIdJogador()!=0){
            jogador.setIdJogador(jogadorDtoRequest.getIdJogador());
        }
        jogador.setNomeJogador(jogadorDtoRequest.getNomeJogador());
        jogador.setDtNascJogador(jogadorDtoRequest.getDtNascJogador());
        jogador.setStatusJogador(jogadorDtoRequest.getStatusJogador());
        jogador.setPeDominante(jogadorDtoRequest.getPeDominante());
        jogador.setPosicao(jogadorDtoRequest.getPosicao());
        return jogador;
    }
}
