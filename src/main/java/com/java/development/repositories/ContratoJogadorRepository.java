package com.java.development.repositories;

import com.java.development.entities.ContratoJogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContratoJogadorRepository extends JpaRepository<ContratoJogador,Long> {

    @Query("Select cj from ContratoJogador cj WHERE cj.equipe.idEquipe = :idEquipe")
    public List<ContratoJogador> findByIdEquipe(Long idEquipe);
}
