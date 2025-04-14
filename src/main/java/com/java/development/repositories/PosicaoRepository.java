package com.java.development.repositories;

import com.java.development.entities.Jogador;
import com.java.development.entities.Posicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosicaoRepository  extends JpaRepository<Posicao,Long> {
}
