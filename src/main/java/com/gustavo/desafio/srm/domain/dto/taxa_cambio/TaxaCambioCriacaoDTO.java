package com.gustavo.desafio.srm.domain.dto.taxa_cambio;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaxaCambioCriacaoDTO {

    @NotNull
    @DecimalMin("1.01")
    private Double novaTaxa;
}
