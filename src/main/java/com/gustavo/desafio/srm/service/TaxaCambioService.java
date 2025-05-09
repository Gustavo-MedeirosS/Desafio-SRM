package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioFiltroDTO;
import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import com.gustavo.desafio.srm.repository.TaxaCambioRepository;
import com.gustavo.desafio.srm.specification.TaxaCambioSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaxaCambioService {

    private final TaxaCambioRepository repository;

    public TaxaCambio buscarTaxaCambioAtual(Integer moedaOrigemId, Integer moedaDestinoId) {
        return repository.findTopByMoedaOrigemIdAndMoedaDestinoIdOrderByDataHoraDesc(moedaOrigemId, moedaDestinoId);
    }

    public Page<TaxaCambio> buscarTodas(TaxaCambioFiltroDTO filtro, Pageable pageable) {

        Specification<TaxaCambio> specification = Specification.where(null);

        if (filtro.getMoedaOrigemId() != null) {
            specification = specification.and(TaxaCambioSpecification.moedaOrigemIdEqual(filtro.getMoedaOrigemId()));
        }

        if (filtro.getMoedaDestinoId() != null) {
            specification = specification.and(TaxaCambioSpecification.moedaDestinoIdEqual(filtro.getMoedaDestinoId()));
        }

        if (filtro.getProdutoId() != null) {
            specification = specification.and(TaxaCambioSpecification.produtoIdEqual(filtro.getProdutoId()));
        }

        if (filtro.getTaxa() != null) {
            specification = specification.and(TaxaCambioSpecification.taxaEqual(filtro.getTaxa()));
        }

        return repository.findAll(specification, pageable);
    }

    public TaxaCambio cadastrarNovaTaxaCambio(TaxaCambio entity) {
        return repository.save(entity);
    }

    public TaxaCambio buscarTaxaCambioPorMoedaOrigemEMoedaDestinoEProdutoId(
            Integer moedaOrigemId,
            Integer moedaDestinoId,
            Integer produtoId
    ) {
        Optional<TaxaCambio> taxaCambio = repository.findTopByMoedaOrigemIdAndMoedaDestinoIdAndProdutoIdOrderByDataHoraDesc(moedaOrigemId, moedaDestinoId, produtoId);

        return taxaCambio.orElse(null);
    }
}
