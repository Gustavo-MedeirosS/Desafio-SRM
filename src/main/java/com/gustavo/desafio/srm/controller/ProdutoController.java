package com.gustavo.desafio.srm.controller;

import com.gustavo.desafio.srm.domain.dto.produto.ProdutoCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.produto.ProdutoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.Produto;
import com.gustavo.desafio.srm.mapper.ProdutoMapper;
import com.gustavo.desafio.srm.service.ProdutoService;
import com.gustavo.desafio.srm.service.ReinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoMapper mapper;
    @Autowired
    private ReinoService reinoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodas() {
        List<Produto> produtos = service.buscarTodos();

        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mapper.toDto(produtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> exibirPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.toDto(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody @Valid ProdutoCriacaoDTO dto) {
        Produto entity = mapper.toEntity(dto);
        entity.setReino(reinoService.buscarPorId(dto.getReinoId()));

        Produto produtoSalvo = service.cadastrar(entity);
        URI uri = URI.create("/produtos/" + produtoSalvo.getId());

        return ResponseEntity.created(uri).body(mapper.toDto(produtoSalvo));
    }
}
