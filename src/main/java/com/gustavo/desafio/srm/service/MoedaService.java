package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.domain.entity.Moeda;
import com.gustavo.desafio.srm.repository.MoedaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoedaService {

    private final MoedaRepository repository;

    public List<Moeda> buscarTodas() {
        return repository.findAll();
    }

    public Moeda buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Moeda cadastrar(Moeda entity) {
        if (repository.existsByNomeIgnoreCaseOrSimboloIgnoreCase(entity.getNome(), entity.getSimbolo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return repository.save(entity);
    }
}
