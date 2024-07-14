package com.example.semana7.controllers;

import com.example.semana7.DTOs.LoginRequest;
import com.example.semana7.DTOs.LoginResponse;
import com.example.semana7.entities.UsuarioEntity;
import com.example.semana7.repositories.UsuarioRepository;
import com.example.semana7.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final UsuarioService usuarioService;

    public TokenController(JwtEncoder jwtEncoder, UsuarioService usuarioService) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioService = usuarioService;
    }

    private static long TEMPO_EXPIRACAO = 36000L;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> gerarToken(@RequestBody LoginRequest loginRequest) {

        UsuarioEntity usuarioEntity = usuarioService.validaUsuario(loginRequest);

        Instant agora = Instant.now();

        String scope = usuarioEntity.getAuthorities()
                .stream()
                .map(autority -> autority.getAuthority())
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(agora)
                .expiresAt(agora.plusSeconds(TEMPO_EXPIRACAO))
                .subject(usuarioEntity.getUsername())
                .claim("scope", scope)
                .build();

        var valorJwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(valorJwt, TEMPO_EXPIRACAO));
    }
}
