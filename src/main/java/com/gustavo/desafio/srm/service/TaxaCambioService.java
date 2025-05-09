package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import com.gustavo.desafio.srm.repository.TaxaCambioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaxaCambioService {

    private final TaxaCambioRepository repository;

    public TaxaCambio buscarTaxaCambioAtual(Integer moedaOrigemId, Integer moedaDestinoId) {
        return repository.findTopByMoedaOrigemIdAndMoedaDestinoIdOrderByDataHoraDesc(moedaOrigemId, moedaDestinoId);
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
