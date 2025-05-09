package com.gustavo.desafio.srm.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;
    private Double precoBase;

    @ManyToOne
    private Reino reino;
}
