package com.java.development.repositories;

import com.java.development.entities.Campeonato;
import com.java.development.entities.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampeonatoRepository  extends JpaRepository<Campeonato,Long> {
}
