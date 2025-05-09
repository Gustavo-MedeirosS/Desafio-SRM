package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaxaCambioRepository extends JpaRepository<TaxaCambio, Integer>, JpaSpecificationExecutor<TaxaCambio> {
    @Query("""
        SELECT t FROM TaxaCambio t
        WHERE t.moedaOrigem.id = :moedaOrigemId
          AND t.moedaDestino.id = :moedaDestinoId
        ORDER BY t.dataHora DESC
    """)
    List<TaxaCambio> findUltimaTaxaCambio(
            @Param("moedaOrigemId") Integer moedaOrigemId,
            @Param("moedaDestinoId") Integer moedaDestinoId,
            Pageable pageable
    );

    @Query("""
        SELECT t FROM TaxaCambio t
        WHERE t.moedaOrigem.id = :moedaOrigemId
          AND t.moedaDestino.id = :moedaDestinoId
          AND t.produto.id = :produtoId
        ORDER BY t.dataHora DESC
    """)
    List<TaxaCambio> findUltimaTaxaCambioPorProduto(
            @Param("moedaOrigemId") Integer moedaOrigemId,
            @Param("moedaDestinoId") Integer moedaDestinoId,
            @Param("produtoId") Integer produtoId,
            Pageable pageable
    );
}
