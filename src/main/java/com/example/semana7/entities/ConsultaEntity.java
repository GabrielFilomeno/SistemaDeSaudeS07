package com.example.semana7.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

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

    public Long getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Long consultaId) {
        this.consultaId = consultaId;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public NutricionistaEntity getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(NutricionistaEntity nutricionista) {
        this.nutricionista = nutricionista;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }
}
