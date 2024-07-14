package com.example.semana7.services;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {

    private final JwtDecoder jwtDecoder;

    public TokenService(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    public void validaToken(String token, String perfil) {

        System.out.println("token : " + token);
        String tokenReal = token.split(" ")[1];
        System.out.println("token real : " + tokenReal);

        System.out.println(jwtDecoder.decode(tokenReal).getClaim("sub").toString()); //retorna o usuario

        List<String> perfisToken = List.of(jwtDecoder.decode(tokenReal).getClaim("scope").toString().split(" "));

        if (!perfisToken.contains(perfil)) {
            throw new RuntimeException("Esse usuário não tem acesso a " + perfil);
        }
    }
}
