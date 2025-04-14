package com.java.development.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="Tecnico")
@EqualsAndHashCode(of = {"idTecnico"})
public class Tecnico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTecnico;

    private String nomeTecnico;

    private Date dtNascTecnico;
}