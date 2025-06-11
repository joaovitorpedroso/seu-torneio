package com.java.development.repositories;

import com.java.development.entities.EdicaoCampeonato;
import com.java.development.entities.PartidaEquipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdicaoCampeonatoRepository extends JpaRepository<EdicaoCampeonato,Long> {
}
