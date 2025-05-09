package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TaxaCambioRepository extends JpaRepository<TaxaCambio, Integer>, JpaSpecificationExecutor<TaxaCambio> {
    TaxaCambio findTopByMoedaOrigemIdAndMoedaDestinoIdOrderByDataHoraDesc(Integer moedaOrigemId, Integer moedaDestinoId);

    Optional<TaxaCambio> findTopByMoedaOrigemIdAndMoedaDestinoIdAndProdutoIdOrderByDataHoraDesc(Integer moedaOrigemId, Integer moedaDestinoId, Integer produtoId);
}
