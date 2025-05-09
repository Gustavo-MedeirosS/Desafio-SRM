package com.gustavo.desafio.srm.controller;

import com.gustavo.desafio.srm.domain.dto.produto.ProdutoTransacaoDTO;
import com.gustavo.desafio.srm.domain.dto.transacao.TransacaoCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.transacao.TransacaoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.ItemTransacao;
import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import com.gustavo.desafio.srm.domain.entity.Transacao;
import com.gustavo.desafio.srm.mapper.TransacaoMapper;
import com.gustavo.desafio.srm.service.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoMapper mapper;
    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private MoedaService moedaService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemTransacaoService itemTransacaoService;
    @Autowired
    private TaxaCambioService taxaCambioService;

    @GetMapping
    public ResponseEntity<List<TransacaoResponseDTO>> listarTodos() {
        List<Transacao> transacoes = transacaoService.buscarTodas();

        if (transacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mapper.toDto(transacoes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> exibirPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.toDto(transacaoService.buscarPorId(id)));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TransacaoResponseDTO> cadastrar(@RequestBody @Valid TransacaoCriacaoDTO dto) {
        Transacao entity = mapper.toEntity(dto);

        List<ItemTransacao> itensTransacao = new ArrayList<>();
        Double valorFinal = 0.0;

        for (ProdutoTransacaoDTO produto : dto.getProdutos()) {
            // mapper
            ItemTransacao itemTransacao = new ItemTransacao();
            itemTransacao.setProduto(produtoService.buscarPorId(produto.getProdutoId()));
            itemTransacao.setQuantidade(produto.getQuantidade());

            Double valorTaxaCambio = 1.0;
            TaxaCambio taxaCambioReferencia = new TaxaCambio();

            // buscando taxa_cambio
            if (!Objects.equals(dto.getMoedaOrigemId(), dto.getMoedaDestinoId())) {
                taxaCambioReferencia = getValorTaxaCambio(
                        dto.getMoedaOrigemId(),
                        dto.getMoedaDestinoId(),
                        produto.getProdutoId()
                );
                valorTaxaCambio = taxaCambioReferencia.getTaxa();
            }

            Double valorOrigem = produto.getQuantidade() * itemTransacao.getProduto().getPrecoBase();
            Double valorDestino = valorOrigem * valorTaxaCambio;

            itemTransacao.setValorOrigem(valorOrigem);
            itemTransacao.setValorDestino(valorDestino);
            itemTransacao.setTaxaReferencia(taxaCambioReferencia);

            valorFinal += valorDestino;
            itensTransacao.add(itemTransacao);
        }

        entity.setValorFinal(valorFinal);
        entity.setDataHora(LocalDateTime.now());
        Transacao transacaoSalva = transacaoService.cadastrar(entity);
        URI uri = URI.create("/transacoes/" + transacaoSalva.getId());

        // salvar os ItemTransacao
        itensTransacao.forEach(item -> item.setTransacao(transacaoSalva));
        itemTransacaoService.cadastrar(itensTransacao);

        return ResponseEntity.created(uri).body(mapper.toDto(transacaoSalva));
    }

    private TaxaCambio getValorTaxaCambio(
            Integer moedaOrigemId,
            Integer moedaDestinoId,
            Integer produtoId
    ) {
        TaxaCambio taxaCambio = taxaCambioService.buscarTaxaCambioPorMoedaOrigemEMoedaDestinoEProdutoId(moedaOrigemId, moedaDestinoId, produtoId);
        if (taxaCambio == null) {
            taxaCambio = taxaCambioService.buscarTaxaCambioAtual(moedaOrigemId, moedaDestinoId);
        }
        return taxaCambio;
    }
}
