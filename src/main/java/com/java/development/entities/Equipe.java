package com.java.development.entities;

import com.java.development.entities.dto.EquipeDtoResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="equipe")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String nomeEquipe;

    private Date fundacao;

    private String presidente;

}
