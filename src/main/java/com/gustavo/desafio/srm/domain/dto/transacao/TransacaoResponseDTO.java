package com.gustavo.desafio.srm.domain.dto.transacao;

import com.gustavo.desafio.srm.domain.dto.moeda.MoedaResponseDTO;
import com.gustavo.desafio.srm.domain.dto.reino.ReinoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.ItemTransacao;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TransacaoResponseDTO {

    private Integer id;
    private LocalDateTime dataHora;
    private Double valorFinal;
    private ReinoResponseDTO reino;
    private MoedaResponseDTO moedaOrigem;
    private MoedaResponseDTO moedaDestino;
    private List<ItemTransacao> produtos;
}
