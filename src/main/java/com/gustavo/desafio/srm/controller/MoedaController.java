package com.gustavo.desafio.srm.controller;

import com.gustavo.desafio.srm.domain.dto.moeda.MoedaCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.moeda.MoedaResponseDTO;
import com.gustavo.desafio.srm.domain.entity.Moeda;
import com.gustavo.desafio.srm.mapper.MoedaMapper;
import com.gustavo.desafio.srm.service.MoedaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/moedas")
public class MoedaController {

    @Autowired
    private MoedaService service;

    @Autowired
    private MoedaMapper mapper;

    @GetMapping
    public ResponseEntity<List<MoedaResponseDTO>> listarTodas() {
        List<Moeda> moedas = service.buscarTodas();

        if (moedas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mapper.toDto(moedas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoedaResponseDTO> exibirPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.toDto(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<MoedaResponseDTO> cadastrar(@RequestBody @Valid MoedaCriacaoDTO dto) {
        Moeda moedaSalva = service.cadastrar(mapper.toEntity(dto));
        URI uri = URI.create("/moedas/" + moedaSalva.getId());
        return ResponseEntity.created(uri).body(mapper.toDto(moedaSalva));
    }
}
