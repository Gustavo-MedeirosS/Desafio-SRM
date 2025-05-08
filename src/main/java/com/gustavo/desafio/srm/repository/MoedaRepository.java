package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.Moeda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoedaRepository extends JpaRepository<Moeda, Integer> {
}
