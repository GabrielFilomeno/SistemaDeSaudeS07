package com.example.semana7.DTOs;

import com.example.semana7.entities.NutricionistaEntity;
import com.example.semana7.entities.PacienteEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Optional;

public class ConsultaResponseDTO {

    private Long consultaId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataConsulta;
    private String observacoes;
    private NutricionistaEntity nutricionistaEntity;
    private PacienteEntity pacienteEntity;

    public ConsultaResponseDTO() {
    }

    public ConsultaResponseDTO(Long consultaId, LocalDate dataConsulta, String observacoes, NutricionistaEntity nutricionista, PacienteEntity paciente) {
    }


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

    public NutricionistaEntity getNutricionistaEntity() {
        return nutricionistaEntity;
    }

    public void setNutricionistaEntity(NutricionistaEntity nutricionistaEntity) {
        this.nutricionistaEntity = nutricionistaEntity;
    }

    public PacienteEntity getPacienteEntity() {
        return pacienteEntity;
    }

    public void setPacienteEntity(PacienteEntity pacienteEntity) {
        this.pacienteEntity = pacienteEntity;
    }
}
