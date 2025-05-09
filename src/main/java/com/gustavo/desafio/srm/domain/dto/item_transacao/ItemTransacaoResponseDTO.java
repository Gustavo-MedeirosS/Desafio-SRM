package com.gustavo.desafio.srm.domain.dto.item_transacao;

import com.gustavo.desafio.srm.domain.dto.produto.ProdutoResponseDTO;
import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioResponseDto;
import com.gustavo.desafio.srm.domain.dto.transacao.TransacaoSubResponseDTO;
import lombok.Data;

@Data
public class ItemTransacaoResponseDTO {

    private Integer id;
    private Integer quantidade;
    private Double valorOrigem;
    private Double valorDestino;
    private ProdutoResponseDTO produto;
    private TransacaoSubResponseDTO transacao;
    private TaxaCambioResponseDto taxaReferencia;
}
