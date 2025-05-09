package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.domain.entity.ItemTransacao;
import com.gustavo.desafio.srm.repository.ItemTransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemTransacaoService {

    private final ItemTransacaoRepository repository;

    public List<ItemTransacao> buscarItensPorTransacaoId(Integer transacaoId) {
        return repository.findAllByTransacaoId(transacaoId);
    }

    public ItemTransacao cadastrar(ItemTransacao entity) {
        if (repository.existsByTransacaoIdAndProdutoId(
                entity.getTransacao().getId(),
                entity.getProduto().getId()
        )) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este item já existe na transação.");
        }
        return repository.save(entity);
    }

    public List<ItemTransacao> cadastrar(List<ItemTransacao> entities) {
        return entities.stream().map(this::cadastrar).toList();
    }
}
