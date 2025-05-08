package com.gustavo.desafio.srm.controller;

import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioResponseDto;
import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import com.gustavo.desafio.srm.mapper.TaxaCambioMapper;
import com.gustavo.desafio.srm.service.TaxaCambioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/taxa-cambio")
public class TaxaCambioController {

    @Autowired
    private TaxaCambioService service;
    @Autowired
    private TaxaCambioMapper mapper;

    @GetMapping
    public ResponseEntity<TaxaCambioResponseDto> exibirTaxaCambioAtual() {
        TaxaCambio taxaCambio = service.buscarTaxaCambioAtual();
        return ResponseEntity.ok(mapper.toDto(taxaCambio));
    }

    @PostMapping
    public ResponseEntity<TaxaCambioResponseDto> cadastrarNovaTaxaCambio(@RequestBody @Valid TaxaCambioCriacaoDTO dto) {
        TaxaCambio entity = mapper.toEntity(dto);
        entity.setDataHora(LocalDateTime.now());
        TaxaCambio taxaCambioSalvo = service.cadastrarNovaTaxaCambio(entity);

        return ResponseEntity.ok(mapper.toDto(taxaCambioSalvo));
    }
}
