package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import com.gustavo.desafio.srm.repository.TaxaCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxaCambioService {
    @Autowired
    private TaxaCambioRepository repository;

    public TaxaCambio buscarTaxaCambioAtual() {
        return repository.findTopByMoedaOrigemIdAndMoedaDestinoIdOrderByDataHoraDesc(1, 2);
    }

    public TaxaCambio cadastrarNovaTaxaCambio(TaxaCambio entity) {
        return repository.save(entity);
    }
}
