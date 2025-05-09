package com.gustavo.desafio.srm.domain.dto.taxa_cambio;

import lombok.Data;

@Data
public class TaxaCambioFiltroDTO {

    private Integer moedaOrigemId;
    private Integer moedaDestinoId;
    private Integer produtoId;
    private Double taxa;
}
