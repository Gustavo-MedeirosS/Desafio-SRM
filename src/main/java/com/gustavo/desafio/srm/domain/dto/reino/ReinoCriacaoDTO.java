package com.gustavo.desafio.srm.domain.dto.reino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReinoCriacaoDTO {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    private Integer moedaId;
}
