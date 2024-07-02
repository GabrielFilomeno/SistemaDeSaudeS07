package com.example.semana7.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Paciente")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pacienteId;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String telefone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private EnderecoEntity endereco;
}
