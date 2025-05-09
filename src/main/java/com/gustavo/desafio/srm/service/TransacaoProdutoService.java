package com.gustavo.desafio.srm.service;

import com.gustavo.desafio.srm.repository.TransacaoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoProdutoService {

    @Autowired
    private TransacaoProdutoRepository repository;
}
