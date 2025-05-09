package com.gustavo.desafio.srm.controller;

import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioFiltroDTO;
import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioResponseDto;
import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import com.gustavo.desafio.srm.mapper.TaxaCambioMapper;
import com.gustavo.desafio.srm.service.MoedaService;
import com.gustavo.desafio.srm.service.TaxaCambioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/taxas-cambio")
@RequiredArgsConstructor
public class TaxaCambioController {

    private final TaxaCambioService service;
    private final MoedaService moedaService;
    private final TaxaCambioMapper mapper;

    @GetMapping
    public ResponseEntity<Page<TaxaCambioResponseDto>> buscarTodas(
            @ParameterObject TaxaCambioFiltroDTO filtro,
            @RequestParam int pagina,
            @RequestParam int itens
    ) {
        Page<TaxaCambio> taxas = service.buscarTodas(filtro, PageRequest.of(pagina, itens));

        if (taxas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<TaxaCambio> taxasList = convertToTaxaCambioList(taxas);
        List<TaxaCambioResponseDto> taxasDtoList = mapper.toDto(taxasList);

        Page<TaxaCambioResponseDto> taxasPage = converToTaxasPage(taxasDtoList, pagina, itens);

        return ResponseEntity.ok(taxasPage);
    }

    private List<TaxaCambio> convertToTaxaCambioList(Page<TaxaCambio> taxas) {
        return taxas.getContent();
    }

    private Page<TaxaCambioResponseDto> converToTaxasPage(List<TaxaCambioResponseDto> taxas, int pagina, int itens) {
        Pageable pageable = PageRequest.of(pagina, itens);
        return new PageImpl<>(taxas, pageable, taxas.size());
    }

    @GetMapping("/atual")
    public ResponseEntity<TaxaCambioResponseDto> exibirTaxaCambioAtual() {
        TaxaCambio taxaCambio = service.buscarTaxaCambioAtual(1, 2);
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
