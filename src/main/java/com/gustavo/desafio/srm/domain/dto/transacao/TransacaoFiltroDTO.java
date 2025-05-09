package com.gustavo.desafio.srm.domain.dto.transacao;

import lombok.Data;

@Data
public class TransacaoFiltroDTO {

    private Integer moedaOrigemId;
    private Integer moedaDestinoId;
    private Integer reinoId;
    private Double valorMinimo;
    private Double valorMaximo;
}
