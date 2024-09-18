package com.jp.CF.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Meta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
}

