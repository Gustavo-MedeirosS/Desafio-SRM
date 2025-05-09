package com.gustavo.desafio.srm.domain.dto.transacao;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransacaoFiltroDTO {

    private Integer moedaOrigemId;
    private Integer moedaDestinoId;
    private Integer reinoId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
}
