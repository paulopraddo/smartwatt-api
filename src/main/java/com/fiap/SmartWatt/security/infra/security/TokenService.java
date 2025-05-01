package com.fiap.SmartWatt.security.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fiap.SmartWatt.security.app.exceptions.GenerationTokenException;
import com.fiap.SmartWatt.security.app.exceptions.ValidateTokenException;
import com.fiap.SmartWatt.security.app.model.User;

@Component
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("auth-api")
                .withSubject(user.getLogin())
                .withExpiresAt(genExpirationDate())
                .sign(algorithm);
            return token;
        } catch (Exception e) {
            throw new GenerationTokenException("Erro na geração do token");
        }        
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("auth-api")
            .build().verify(token)
            .getSubject();
        } catch (Exception e) {
            throw new ValidateTokenException("Erro durante a validação do token");
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
