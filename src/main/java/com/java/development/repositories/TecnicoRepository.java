package com.java.development.repositories;

import com.java.development.entities.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico,Long> {
}
