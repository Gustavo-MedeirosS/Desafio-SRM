package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.ItemTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoProdutoRepository extends JpaRepository<ItemTransacao, Integer> {
}
