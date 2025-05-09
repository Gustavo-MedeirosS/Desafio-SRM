package com.gustavo.desafio.srm.specification;

import com.gustavo.desafio.srm.domain.entity.Moeda;
import com.gustavo.desafio.srm.domain.entity.Reino;
import com.gustavo.desafio.srm.domain.entity.Transacao;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TransacaoSpecification {

    public static Specification<Transacao> moedaOrigemIdEqual(Integer moedaOrigemId) {
        return (((root, query, criteriaBuilder) -> {
            Join<Transacao, Moeda> moedaJoin = root.join("moedaOrigem");
            return criteriaBuilder.equal(moedaJoin.get("id"), moedaOrigemId);
        }));
    }

    public static Specification<Transacao> moedaDestinoIdEqual(Integer moedaDestinoId) {
        return (((root, query, criteriaBuilder) -> {
            Join<Transacao, Moeda> moedaJoin = root.join("moedaDestino");
            return criteriaBuilder.equal(moedaJoin.get("id"), moedaDestinoId);
        }));
    }

    public static Specification<Transacao> reinoIdEqual(Integer reinoId) {
        return (((root, query, criteriaBuilder) -> {
            Join<Transacao, Reino> reinoJoin = root.join("reino");
            return criteriaBuilder.equal(reinoJoin.get("id"), reinoId);
        }));
    }

    public static Specification<Transacao> dataHoraGreatherThanOrEqual(LocalDateTime dataHora) {
        return (((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("dataHora"), dataHora);
        }));
    }

    public static Specification<Transacao> dataHoraLessThanOrEqual(LocalDateTime dataHora) {
        return (((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("dataHora"), dataHora);
        }));
    }
}
