package com.example.semana7.controllers;

import com.example.semana7.DTOs.LoginRequest;
import com.example.semana7.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody LoginRequest cadastroRequest) {

        usuarioService.cadastrarUsuario(cadastroRequest);

        return new ResponseEntity<>("Usuario Criado", HttpStatus.CREATED);
    }
}
