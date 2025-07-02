package com.java.development.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="inscricaojogadorcampeonato")
public class InscricaoJogadorCampeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscricaoJogadorCampeonato;

    private String status;
    private Timestamp dataInicio;
    private Timestamp dataFim;

    @ManyToOne
    @JoinColumn(name = "idEdicaoCampeonato")
    private EdicaoCampeonato edicaoCampeonato;

    @ManyToOne
    @JoinColumn(name = "idContratoJogador")
    private ContratoJogador contratoJogador;
}
