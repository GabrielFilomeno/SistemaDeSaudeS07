package com.example.semana7.services;

import com.example.semana7.DTOs.PerfilRequest;
import com.example.semana7.entities.PerfilEntity;
import com.example.semana7.repositories.PerfilRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {
    
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }


    public void cadastrarPerfil(PerfilRequest perfilRequest) {
        if (perfilRepository.findByNomePerfil(perfilRequest.nomePerfil()).isPresent()) {
            throw new RuntimeException("Perfil já existe com o nome : " + perfilRequest.nomePerfil());
        }

        PerfilEntity perfilEntity = new PerfilEntity();
        perfilEntity.setNomePerfil(perfilRequest.nomePerfil());
        perfilRepository.save(perfilEntity);
    }

    public PerfilEntity validaPerfil(String nomePerfil) {
        PerfilEntity perfil = perfilRepository.findByNomePerfil(nomePerfil)
                .orElseThrow(() -> new RuntimeException("Perfil não existe com nome : " + nomePerfil));

        return perfil;
    }
}
