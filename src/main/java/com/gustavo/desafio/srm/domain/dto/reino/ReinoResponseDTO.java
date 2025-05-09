package com.gustavo.desafio.srm.domain.dto.reino;

import com.gustavo.desafio.srm.domain.dto.moeda.MoedaResponseDTO;
import lombok.Data;

@Data
public class ReinoResponseDTO {
    
    private Integer id;
    private String nome;
    private MoedaResponseDTO moeda;
}
