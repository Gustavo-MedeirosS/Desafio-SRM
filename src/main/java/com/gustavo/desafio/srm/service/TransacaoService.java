package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.domain.entity.Transacao;
import com.gustavo.desafio.srm.repository.MoedaRepository;
import com.gustavo.desafio.srm.repository.ReinoRepository;
import com.gustavo.desafio.srm.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private MoedaRepository moedaRepository;
    @Autowired
    private ReinoRepository reinoRepository;

    public List<Transacao> buscarTodas() {
        return transacaoRepository.findAll();
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
