package com.java.development.services.faker;

import com.java.development.entities.Jogador;
import com.java.development.entities.Posicao;
import com.java.development.repositories.PosicaoRepository;
import org.apache.tomcat.jni.Pool;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JogadorFaker {


    public static Jogador criarJogadorPorPosicao(Posicao posicao){
        Jogador jogador = new Jogador();
        jogador.setStatusJogador("Normal");
        jogador.setNomeJogador(FakerUtil.gerarNomeSimples());
        jogador.setDtNascJogador(FakerUtil.gerarDataNascimentoJogador());
        jogador.setPeDominante(FakerUtil.gerarPeDominante());
        jogador.setPosicao(posicao);
        return jogador;
    }

}
