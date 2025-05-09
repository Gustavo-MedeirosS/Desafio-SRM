package com.gustavo.desafio.srm.specification;

import com.gustavo.desafio.srm.domain.entity.Moeda;
import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class TaxaCambioSpecification {

    public static Specification<TaxaCambio> moedaOrigemIdEqual(Integer moedaOrigemId) {
        return (((root, query, criteriaBuilder) -> {
            Join<TaxaCambio, Moeda> moedaJoin = root.join("moedaOrigem");
            return criteriaBuilder.equal(moedaJoin.get("id"), moedaOrigemId);
        }));
    }

    public static Specification<TaxaCambio> moedaDestinoIdEqual(Integer moedaDestinoId) {
        return (((root, query, criteriaBuilder) -> {
            Join<TaxaCambio, Moeda> moedaJoin = root.join("moedaDestino");
            return criteriaBuilder.equal(moedaJoin.get("id"), moedaDestinoId);
        }));
    }

    public static Specification<TaxaCambio> produtoIdEqual(Integer produtoId) {
        return (((root, query, criteriaBuilder) -> {
            Join<TaxaCambio, Moeda> produtoJoin = root.join("produto");
            return criteriaBuilder.equal(produtoJoin.get("id"), produtoId);
        }));
    }

    public static Specification<TaxaCambio> taxaEqual(Double taxa) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("taxa"), taxa)));
    }
}
