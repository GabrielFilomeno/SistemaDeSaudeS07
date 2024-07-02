package com.example.semana7.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoId;
    private String logradouro;
    private String estado;
    private String cidade;
    private String numero;
    private String cep;
}
