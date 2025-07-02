package com.java.development.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="escalacao")
public class Escalacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEscalacao;

    /*@OneToMany(mappedBy = "idEscalacaoJogadorHistorico", fetch = FetchType.LAZY)
    private List<EscalacaoJogadorHistorico> dadosEscalacao;

    @OneToOne
    @JoinColumn(name = "idPartidaEquipe")
    private PartidaEquipe partidaEquipe;*///TODO refatorar
}
