package com.example.semana7.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Nutricionista")
public class NutricionistaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutricionistaId;
    private String crn;
    private String especialidade;

    public Long getNutricionistaId() {
        return nutricionistaId;
    }

    public void setNutricionistaId(Long nutricionistaId) {
        this.nutricionistaId = nutricionistaId;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
