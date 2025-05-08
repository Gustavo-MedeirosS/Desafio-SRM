package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.Reino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReinoRepository extends JpaRepository<Reino, Integer> {

    Boolean existsByNomeIgnoreCase(String nome);
}
