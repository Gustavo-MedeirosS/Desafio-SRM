package com.gustavo.desafio.srm.domain.dto.produto;

import lombok.Data;

@Data
public class ProdutoCriacaoDTO {

    private String nome;
    private String descricao;
    private Integer reinoId;
    private Double precoBase;
}
