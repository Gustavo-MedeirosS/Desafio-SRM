package com.gustavo.desafio.srm.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class TaxaCambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double taxa;
    private LocalDateTime dataHora;
    @ManyToOne
    private Moeda moedaOrigem;

    @ManyToOne
    private Moeda moedaDestino;

    @ManyToOne
    private Produto produto;
}
