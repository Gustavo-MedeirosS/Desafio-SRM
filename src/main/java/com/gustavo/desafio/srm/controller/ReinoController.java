package com.gustavo.desafio.srm.controller;

import com.gustavo.desafio.srm.domain.dto.reino.ReinoCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.reino.ReinoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.Reino;
import com.gustavo.desafio.srm.mapper.ReinoMapper;
import com.gustavo.desafio.srm.service.ReinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reinos")
public class ReinoController {

    @Autowired
    private ReinoService service;
    @Autowired
    private ReinoMapper mapper;

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
        Reino reinoSalvo = service.cadastrar(mapper.toEntity(dto));
        URI uri = URI.create("/reinos/" + reinoSalvo.getId());
        return ResponseEntity.created(uri).body(mapper.toDto(reinoSalvo));
    }
}
