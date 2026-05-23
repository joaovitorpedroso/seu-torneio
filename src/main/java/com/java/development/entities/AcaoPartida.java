package com.java.development.entities;

import com.java.development.entities.enums.TipoAcao;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="campeonato")
@EqualsAndHashCode(of = {"id"})
public class AcaoPartida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoAcao status;
    private int minuto;
    private int acrescimo;

    @OneToOne
    @JoinColumn(name = "idPartidaEquipe")
    private PartidaEquipe partidaEquipe;

    @ManyToOne
    @JoinColumn(name = "idContratoJogador")
    private ContratoJogador contratoJogador;
}
