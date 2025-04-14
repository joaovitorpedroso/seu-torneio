package com.java.development.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="contratojogador")
public class ContratoJogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContratoJogador;

    private Date dtInicio;

    private Date dtFim;

    private String status;

    private int numeroJogador;

    @ManyToOne
    @JoinColumn(name = "idEquipe")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "idJogador")
    private Jogador jogador;

}
