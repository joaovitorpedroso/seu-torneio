package com.java.development.repositories;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.ContratoTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContratoTecnicoRepository extends JpaRepository<ContratoTecnico,Long> {

    @Query("Select ct from ContratoTecnico ct WHERE ct.equipe.idEquipe = :idEquipe")
    public List<ContratoTecnico> findByIdEquipe(Long idEquipe);
}
