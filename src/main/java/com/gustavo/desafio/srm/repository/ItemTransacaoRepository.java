package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.ItemTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTransacaoRepository extends JpaRepository<ItemTransacao, Integer> {

    Boolean existsByTransacaoIdAndProdutoId(Integer transacaoId, Integer produtoId);
}
