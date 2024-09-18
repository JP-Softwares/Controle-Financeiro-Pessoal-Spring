package com.jp.CF.modelo;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grupo> grupos;
}

