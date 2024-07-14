package com.example.semana7.repositories;

import com.example.semana7.entities.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {

    Optional<PerfilEntity> findByNomePerfil(String nomePerfil);
}
