package com.java.development.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="escalacaojogadorhistorico")
public class EscalacaoJogadorHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEscalacaoJogadorHistorico;

    private String status;
    private int minutoInicial;
    private int minutoFinal;

    @OneToOne
    @JoinColumn(name = "idPartidaEquipe")
    private PartidaEquipe partidaEquipe;

    @ManyToOne
    @JoinColumn(name = "idContratoJogador")
    private ContratoJogador contratoJogador;

    @ManyToOne
    @JoinColumn(name = "idPosicao")
    private Posicao posicao;
}
