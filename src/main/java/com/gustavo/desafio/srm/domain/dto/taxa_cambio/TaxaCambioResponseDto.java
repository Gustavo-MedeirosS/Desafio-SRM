package com.gustavo.desafio.srm.domain.dto.taxa_cambio;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaxaCambioResponseDto {

    private Double taxa;
    private LocalDateTime dataHora;
}
