package com.java.development.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Posicao")
public class Posicao {

    @Id
    private Long idPosicao;

    private String nomePosicao;

    private String descPosicao;
}
