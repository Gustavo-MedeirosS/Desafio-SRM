package com.gustavo.desafio.srm.controller;

import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioResponseDto;
import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import com.gustavo.desafio.srm.mapper.TaxaCambioMapper;
import com.gustavo.desafio.srm.service.MoedaService;
import com.gustavo.desafio.srm.service.TaxaCambioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/taxas-cambio")
@RequiredArgsConstructor
public class TaxaCambioController {

    private final TaxaCambioService service;
    private final MoedaService moedaService;
    private final TaxaCambioMapper mapper;

    @GetMapping("/atual")
    public ResponseEntity<TaxaCambioResponseDto> exibirTaxaCambioAtual() {
        TaxaCambio taxaCambio = service.buscarTaxaCambioAtual(1,2);
        return ResponseEntity.ok(mapper.toDto(taxaCambio));
    }

    @PostMapping
    public ResponseEntity<TaxaCambioResponseDto> cadastrarNovaTaxaCambio(@RequestBody @Valid TaxaCambioCriacaoDTO dto) {
        TaxaCambio entity = mapper.toEntity(dto);
        entity.setDataHora(LocalDateTime.now());
        entity.setMoedaOrigem(moedaService.buscarPorId(1));
        entity.setMoedaDestino(moedaService.buscarPorId(2));
        TaxaCambio taxaCambioSalvo = service.cadastrarNovaTaxaCambio(entity);

        return ResponseEntity.ok(mapper.toDto(taxaCambioSalvo));
    }
}
