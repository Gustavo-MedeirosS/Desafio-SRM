package com.gustavo.desafio.srm.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemTransacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Transacao transacao;

    @ManyToOne
    private Produto produto;

    private Integer quantidade;
    private Double valorOrigem;
    private Double valorDestino;

    @ManyToOne
    private TaxaCambio taxaReferencia;
}
