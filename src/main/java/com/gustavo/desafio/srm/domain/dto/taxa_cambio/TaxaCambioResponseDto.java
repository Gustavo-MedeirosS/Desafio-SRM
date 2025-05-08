package com.gustavo.desafio.srm.domain.dto.taxa_cambio;

import com.gustavo.desafio.srm.domain.dto.moeda.MoedaResponseDTO;
import com.gustavo.desafio.srm.domain.dto.produto.ProdutoResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaxaCambioResponseDto {

    private Double taxa;
    private LocalDateTime dataHora;
    private MoedaResponseDTO moedaOrigem;
    private MoedaResponseDTO moedaDestino;
    private ProdutoResponseDTO produto;
}
