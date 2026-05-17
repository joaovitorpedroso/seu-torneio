package com.java.development.entities;

import com.java.development.entities.enums.SituacaoPartida;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="partidaequipe")
@EqualsAndHashCode(of = {"idPartidaEquipe"})
public class PartidaEquipe {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartidaEquipe;

    private int gols;

    @Enumerated(EnumType.STRING)
    private SituacaoPartida situacao;

    @ManyToOne
    @JoinColumn(name = "idEquipe")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "idPartida")
    private Partida partida;

    @OneToMany
    @JoinColumn(name = "idEscalacaoJogadorHistorico")
    private List<EscalacaoJogadorHistorico> escalacaoJogadorHistorico;
}
