package com.java.development.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="contratotecnico")
public class ContratoTecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContratoTecnico;

    private Date dtInicio;

    private Date dtFim;

    private String status;

    @ManyToOne
    @JoinColumn(name = "idEquipe")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "idTecnico")
    private Tecnico tecnico;
}
