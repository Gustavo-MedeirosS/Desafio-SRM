package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.domain.entity.Moeda;
import com.gustavo.desafio.srm.repository.MoedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MoedaService {

    @Autowired
    private MoedaRepository repository;

    public Moeda buscarMoedaPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }
}
