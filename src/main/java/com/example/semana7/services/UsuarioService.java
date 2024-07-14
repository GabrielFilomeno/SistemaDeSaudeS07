package com.example.semana7.services;

import com.example.semana7.DTOs.LoginRequest;
import com.example.semana7.entities.PerfilEntity;
import com.example.semana7.entities.UsuarioEntity;
import com.example.semana7.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PerfilService perfilService;

    public UsuarioService(BCryptPasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, PerfilService perfilService) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.perfilService = perfilService;
    }

    public UsuarioEntity validaUsuario(LoginRequest loginRequest) {
        UsuarioEntity usuarioEntity = usuarioRepository
                .findByUsername(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("Usuário não existe com o nome " + loginRequest.username())
                );
        if(!passwordEncoder.matches(loginRequest.password(), usuarioEntity.getPassword())) {
            throw new RuntimeException("Senha errada para usuario com nome " + loginRequest.username());
        }

        return usuarioEntity;
    }

    public void cadastrarUsuario(LoginRequest cadastroRequest) {
        if (usuarioRepository.findByUsername(cadastroRequest.username()).isPresent()) {
            throw new RuntimeException("Usuário ja existe com nome: " + cadastroRequest.username());
        }
        PerfilEntity perfilEntity = perfilService.validaPerfil(cadastroRequest.nomePerfil());

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setUsername(cadastroRequest.username());
        usuarioEntity.setPassword(passwordEncoder.encode(cadastroRequest.password()));
        usuarioEntity.setPerfilEntityList(Set.of(perfilEntity));

        usuarioRepository.save(usuarioEntity);
    }
}
