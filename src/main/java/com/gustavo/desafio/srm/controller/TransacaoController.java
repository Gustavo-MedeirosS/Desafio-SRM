package com.gustavo.desafio.srm.controller;

import com.gustavo.desafio.srm.domain.dto.produto.ProdutoTransacaoDTO;
import com.gustavo.desafio.srm.domain.dto.transacao.TransacaoCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.transacao.TransacaoFiltroDTO;
import com.gustavo.desafio.srm.domain.dto.transacao.TransacaoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.ItemTransacao;
import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import com.gustavo.desafio.srm.domain.entity.Transacao;
import com.gustavo.desafio.srm.mapper.ItemTransacaoMapper;
import com.gustavo.desafio.srm.mapper.TransacaoMapper;
import com.gustavo.desafio.srm.service.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private TransacaoMapper transacaoMapper;
    @Autowired
    private ItemTransacaoMapper itemTransacaoMapper;
    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private ReinoService reinoService;
    @Autowired
    private MoedaService moedaService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemTransacaoService itemTransacaoService;
    @Autowired
    private TaxaCambioService taxaCambioService;

    @GetMapping
    public ResponseEntity<Page<TransacaoResponseDTO>> listarTodos(
        @ParameterObject TransacaoFiltroDTO filtroDTO,
        @RequestParam int pagina,
        @RequestParam int itens
    ) {
        Page<Transacao> transacoes = transacaoService.buscarTodas(filtroDTO, PageRequest.of(pagina, itens));

        if (transacoes.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Transacao> transacoesList = convertToTransacaoList(transacoes);
        List<TransacaoResponseDTO> transacoesDtoList = transacaoMapper.toDto(transacoesList);
        Page<TransacaoResponseDTO> transacoesPage = convertToTransacaoPage(transacoesDtoList, pagina, itens);
        return ResponseEntity.ok(transacoesPage);
    }

    private List<Transacao> convertToTransacaoList(Page<Transacao> transacoes) {
        return transacoes.getContent();
    }

    private Page<TransacaoResponseDTO> convertToTransacaoPage(List<TransacaoResponseDTO> transacoes, int pagina, int itens) {
        Pageable pageable = PageRequest.of(pagina, itens);
        return new PageImpl<>(transacoes, pageable, transacoes.size());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> exibirPorId(@PathVariable Integer id) {
        TransacaoResponseDTO dto = transacaoMapper.toDto(transacaoService.buscarPorId(id));

        List<ItemTransacao> itensTransacao = itemTransacaoService.buscarItemPorTransacaoId(id);
        dto.setProdutos(itemTransacaoMapper.toDto(itensTransacao));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TransacaoResponseDTO> cadastrar(@RequestBody @Valid TransacaoCriacaoDTO dto) {
        Transacao entity = transacaoMapper.toEntity(dto);
        entity.setReino(reinoService.buscarPorId(dto.getReinoId()));
        entity.setMoedaOrigem(moedaService.buscarPorId(dto.getMoedaOrigemId()));
        entity.setMoedaDestino(moedaService.buscarPorId(dto.getMoedaDestinoId()));

        List<ItemTransacao> itensTransacao = new ArrayList<>();
        double valorFinal = 0.0;

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
            double valorDestino = valorOrigem * valorTaxaCambio;

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

        TransacaoResponseDTO transacaoDto = transacaoMapper.toDto(transacaoSalva);
        transacaoDto.setProdutos(itemTransacaoMapper.toDto(itensTransacao));

        return ResponseEntity.created(uri).body(transacaoDto);
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
