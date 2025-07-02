package com.java.development.repositories;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.PartidaEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartidaEquipeRepository extends JpaRepository<PartidaEquipe,Long> {

    @Query("select pe from PartidaEquipe pe where pe.partida.idPartida = :idPartida")
    public List<PartidaEquipe> findPartidaEquipeByIdPartida(Long idPartida);
}
