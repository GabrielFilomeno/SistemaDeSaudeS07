package com.example.semana7.repositories;

import com.example.semana7.entities.NutricionistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutricionistaRepository extends JpaRepository<NutricionistaEntity, Long> {
}
