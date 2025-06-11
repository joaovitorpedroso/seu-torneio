package com.java.development.entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.reactive.GenericReactiveTransaction;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="Jogador")
@EqualsAndHashCode(of = {"idJogador"})
public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJogador;

    private String nomeJogador;

    private Date dtNascJogador;

    private String statusJogador;

    private String peDominante;

    @OneToOne
    @JoinColumn(name = "idPosicao")
    private Posicao posicao;

    public Jogador() {
    }

    public Jogador(Long idJogador, String nomeJogador, Date dtNascJogador,String statusJogador, String peDominante, Posicao posicao) {
        this.idJogador = idJogador;
        this.nomeJogador = nomeJogador;
        this.dtNascJogador = dtNascJogador;
        this.statusJogador = statusJogador;
        this.peDominante = peDominante;
        this.posicao = posicao;
    }
}