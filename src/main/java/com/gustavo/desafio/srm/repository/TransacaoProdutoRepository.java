package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.TransacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoProdutoRepository extends JpaRepository<TransacaoProduto, Integer> {
}
