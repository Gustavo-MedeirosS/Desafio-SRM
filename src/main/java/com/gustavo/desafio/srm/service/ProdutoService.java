package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.domain.entity.Produto;
import com.gustavo.desafio.srm.repository.ProdutoRepository;
import com.gustavo.desafio.srm.repository.ReinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ReinoRepository reinoRepository;

    public List<Produto> buscarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não existe.")
        );
    }

    public Produto cadastrar(Produto entity) {
        if (!reinoRepository.existsById(entity.getReino().getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reino não existe.");
        }

        if (repository.existsByNomeIgnoreCaseAndReinoId(entity.getNome(), entity.getReino().getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe produto com este nome e este reino.");
        }

        return repository.save(entity);
    }
}
