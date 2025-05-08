package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxaCambioRepository extends JpaRepository<TaxaCambio, Integer> {
    TaxaCambio findTopByMoedaOrigemIdAndMoedaDestinoIdOrderByDataHoraDesc(Integer moedaOrigemId, Integer moedaDestinoId);
}
