package com.gustavo.desafio.srm.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataHora;
    private Double valorFinal;

    @ManyToOne
    private Reino reino;

    @ManyToOne
    private Moeda moedaOrigem;

    @ManyToOne
    private Moeda moedaDestino;
}
