package com.java.development.repositories;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.EscalacaoJogadorHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EscalacaoJogadorHistoricoRepository extends JpaRepository<EscalacaoJogadorHistorico,Long> {

    @Query("select ejh from EscalacaoJogadorHistorico ejh " +
            "where ejh.escalacao.idEscalacao = :idEscalacao " +
            "and ejh.contratoJogador.equipe.idEquipe = :idEquipe")
    public List<EscalacaoJogadorHistorico> findJogadoresByIdEquipeAndIdEscalacao(Long idEscalacao,Long idEquipe);
}
