package com.java.development.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="campeonato")
@EqualsAndHashCode(of = {"idCampeonato"})
public class Campeonato {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCampeonato;

    private String nomeCampeonato;
}
