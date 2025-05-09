package com.gustavo.desafio.srm.domain.dto.transacao;

import com.gustavo.desafio.srm.domain.dto.produto.ProdutoTransacaoDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TransacaoCriacaoDTO {

    @NotNull
    private Integer reinoId;

    @NotNull
    private Integer moedaOrigemId;

    @NotNull
    private Integer moedaDestinoId;

    private List<ProdutoTransacaoDTO> produtos;
}
