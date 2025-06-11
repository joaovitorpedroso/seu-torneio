package com.java.development.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="edicaocampeonato")
@EqualsAndHashCode(of = {"idEdicao"})
public class EdicaoCampeonato {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEdicao;

    private String nomeEdicao;

    private int ano;

    private String descricaoEdicao;

    private Date dtInicio;

    private Date dtFim;

    @ManyToOne
    @JoinColumn(name = "idCampeonato")
    private Campeonato Campeonato;
}
