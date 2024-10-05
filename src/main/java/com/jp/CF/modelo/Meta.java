package com.jp.CF.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
}

