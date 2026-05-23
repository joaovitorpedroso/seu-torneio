package com.java.development.entities;

import com.java.development.entities.enums.StatusPartida;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="partida")
@EqualsAndHashCode(of = {"idPartida"})
public class Partida {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartida;

    private Date dataInicio;

    private int golsTotais;

    private String estadio;

    private StatusPartida status;

    private Long idFaseCampeonato;
}
