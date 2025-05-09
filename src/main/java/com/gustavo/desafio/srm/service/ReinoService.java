package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.domain.entity.Reino;
import com.gustavo.desafio.srm.repository.ReinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReinoService {

    private final ReinoRepository repository;

    public List<Reino> buscarTodos() {
        return repository.findAll();
    }

    public Reino buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Reino cadastrar(Reino entity) {
        if (repository.existsByNomeIgnoreCase(entity.getNome())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return repository.save(entity);
    }
}
