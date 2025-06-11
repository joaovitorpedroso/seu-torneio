package com.java.development.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="fasecampeonato")
@EqualsAndHashCode(of = {"idFaseCampeonato"})
public class FaseCampeonato {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFaseCampeonato;

    private String nomeFase;

    private int ordem;

    @ManyToOne
    @JoinColumn(name = "idEdicao")
    private EdicaoCampeonato edicaoCampeonato;
}
