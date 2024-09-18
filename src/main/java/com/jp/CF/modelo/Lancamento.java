package com.jp.CF.modelo;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
}
