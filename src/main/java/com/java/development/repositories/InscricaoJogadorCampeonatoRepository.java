package com.java.development.repositories;

import com.java.development.entities.ContratoJogador;
import com.java.development.entities.ContratoTecnico;
import com.java.development.entities.InscricaoJogadorCampeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscricaoJogadorCampeonatoRepository extends JpaRepository<InscricaoJogadorCampeonato,Long> {

    @Query("Select ij.contratoJogador from InscricaoJogadorCampeonato ij where ij.contratoJogador.equipe.idEquipe = :idEquipe")
    public List<ContratoJogador> findJogadoresInscritos(Long idEquipe);
}
