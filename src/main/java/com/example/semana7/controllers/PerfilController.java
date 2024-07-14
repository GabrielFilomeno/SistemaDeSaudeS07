package com.example.semana7.controllers;

import com.example.semana7.DTOs.PerfilRequest;
import com.example.semana7.services.PerfilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @PostMapping("/perfil")
    public ResponseEntity<String> cadastrarPerfil(@RequestBody PerfilRequest perfilRequest) {

        perfilService.cadastrarPerfil(perfilRequest);

        return new ResponseEntity<>("Perfil Criado", HttpStatus.CREATED);
    }
}
