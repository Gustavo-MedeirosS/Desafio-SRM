package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.ItemTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemTransacaoRepository extends JpaRepository<ItemTransacao, Integer> {

    Boolean existsByTransacaoIdAndProdutoId(Integer transacaoId, Integer produtoId);

    List<ItemTransacao> findAllByTransacaoId(Integer transacaoId);
}
