package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Boolean existsByNomeIgnoreCaseAndReinoId(String nome, Integer reinoId);
}
