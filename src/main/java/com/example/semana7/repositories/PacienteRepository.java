package com.example.semana7.repositories;

import com.example.semana7.entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository  extends JpaRepository<PacienteEntity, Long> {
}
