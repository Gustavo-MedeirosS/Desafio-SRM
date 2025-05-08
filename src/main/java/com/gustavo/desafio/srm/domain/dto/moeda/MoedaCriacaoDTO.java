package com.gustavo.desafio.srm.domain.dto.moeda;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MoedaCriacaoDTO {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String simbolo;
}
