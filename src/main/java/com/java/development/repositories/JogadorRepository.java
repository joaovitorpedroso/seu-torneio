package com.java.development.repositories;

import com.java.development.entities.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador,Long> {

}