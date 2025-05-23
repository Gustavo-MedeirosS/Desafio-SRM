package com.gustavo.desafio.srm.controller;

import com.gustavo.desafio.srm.domain.dto.reino.ReinoCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.reino.ReinoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.Reino;
import com.gustavo.desafio.srm.mapper.ReinoMapper;
import com.gustavo.desafio.srm.service.MoedaService;
import com.gustavo.desafio.srm.service.ReinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reinos")
@RequiredArgsConstructor
public class ReinoController {

    private final ReinoService service;
    private final ReinoMapper mapper;
    private final MoedaService moedaService;

    @GetMapping
    public ResponseEntity<List<ReinoResponseDTO>> listarTodos() {
        List<Reino> reinos = service.buscarTodos();

        if (reinos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mapper.toDto(reinos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReinoResponseDTO> exibirPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.toDto(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ReinoResponseDTO> cadastrar(@RequestBody @Valid ReinoCriacaoDTO dto) {
        Reino entity = mapper.toEntity(dto);
        entity.setMoeda(moedaService.buscarPorId(dto.getMoedaId()));

        Reino reinoSalvo = service.cadastrar(entity);
        URI uri = URI.create("/reinos/" + reinoSalvo.getId());

        return ResponseEntity.created(uri).body(mapper.toDto(reinoSalvo));
    }
}
