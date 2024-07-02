package com.example.semana7.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultaId;
    private LocalDate dataConsulta;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_nutricionista")
    private NutricionistaEntity nutricionista;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;
}
