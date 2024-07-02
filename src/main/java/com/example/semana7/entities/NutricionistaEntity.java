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
}
