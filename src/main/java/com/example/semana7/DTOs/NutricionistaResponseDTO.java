package com.example.semana7.DTOs;

import java.util.List;

public class NutricionistaResponseDTO {

    private Long nutricionistaId;
    private String nome;
    private String crn;
    private Integer tempoExperiencia;
    private String especialidade;
    private List<String> certificacoes;

    public NutricionistaResponseDTO() {
    }

    public NutricionistaResponseDTO(Long nutricionistaId, String nome, String crn, Integer tempoExperiencia, String especialidade, List<String> certificacoes) {
    }

    public Long getNutricionistaId() {
        return nutricionistaId;
    }

    public void setNutricionistaId(Long nutricionistaId) {
        this.nutricionistaId = nutricionistaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public Integer getTempoExperiencia() {
        return tempoExperiencia;
    }

    public void setTempoExperiencia(Integer tempoExperiencia) {
        this.tempoExperiencia = tempoExperiencia;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<String> getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(List<String> certificacoes) {
        this.certificacoes = certificacoes;
    }
}
