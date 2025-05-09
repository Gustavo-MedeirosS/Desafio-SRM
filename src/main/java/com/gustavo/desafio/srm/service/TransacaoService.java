package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.domain.dto.transacao.TransacaoFiltroDTO;
import com.gustavo.desafio.srm.domain.entity.Transacao;
import com.gustavo.desafio.srm.repository.MoedaRepository;
import com.gustavo.desafio.srm.repository.ReinoRepository;
import com.gustavo.desafio.srm.repository.TransacaoRepository;
import com.gustavo.desafio.srm.specification.TransacaoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private MoedaRepository moedaRepository;
    @Autowired
    private ReinoRepository reinoRepository;

    public Page<Transacao> buscarTodas(TransacaoFiltroDTO filtro, Pageable pageable) {
        Specification<Transacao> specification = Specification.where(null);

        if (filtro.getMoedaOrigemId() != null) {
            specification.and(TransacaoSpecification.moedaOrigemIdEqual(filtro.getMoedaOrigemId()));
        }

        if (filtro.getMoedaDestinoId() != null) {
            specification.and(TransacaoSpecification.moedaDestinoIdEqual(filtro.getMoedaDestinoId()));
        }

        if (filtro.getReinoId() != null) {
            specification.and(TransacaoSpecification.reinoIdEqual(filtro.getReinoId()));
        }

        if (filtro.getValorMinimo() != null) {
            specification.and(TransacaoSpecification.valorFinalGreatherThanOrEqual(filtro.getValorMinimo()));
        }

        if (filtro.getValorMaximo() != null) {
            specification.and(TransacaoSpecification.valorFinalLessThanOrEqual(filtro.getValorMaximo()));
        }

        return transacaoRepository.findAll(specification, pageable);
    }

    public Transacao buscarPorId(Integer id) {
        return transacaoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Transacao cadastrar(Transacao entity) {
        if (
                !reinoRepository.existsById(entity.getReino().getId()) ||
                        !moedaRepository.existsById(entity.getMoedaOrigem().getId()) ||
                        !moedaRepository.existsById(entity.getMoedaDestino().getId())
        ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return transacaoRepository.save(entity);
    }

}
