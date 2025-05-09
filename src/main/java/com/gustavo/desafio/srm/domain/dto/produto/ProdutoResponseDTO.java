package com.gustavo.desafio.srm.domain.dto.produto;

import com.gustavo.desafio.srm.domain.dto.reino.ReinoResponseDTO;
import lombok.Data;

@Data
public class ProdutoResponseDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private Double precoBase;
    private ReinoResponseDTO reino;
}
