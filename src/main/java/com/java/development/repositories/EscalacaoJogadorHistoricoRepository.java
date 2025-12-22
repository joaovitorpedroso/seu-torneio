package com.java.development.repositories;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.EscalacaoJogadorHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscalacaoJogadorHistoricoRepository extends JpaRepository<EscalacaoJogadorHistorico, Long> {

    @Query("select ejh from EscalacaoJogadorHistorico ejh " +
            "WHERE ejh.partidaEquipe.equipe.id = :idEquipe")
    public List<EscalacaoJogadorHistorico> findJogadoresByIdEquipe(Long idEquipe);
}
