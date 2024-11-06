package com.clientapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "your_secret_key"; // Substitua por uma chave segura e armazenada de forma segura
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora em milissegundos

    // Gera um token JWT com base no UserDetails
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    // Cria o token JWT
    private String createToken(Map<String, Object> claims, UserDetails subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(subject))
                .setIssuedAt(new Date(System.currentTimeMillis())) // Data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Data de expiração
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Algoritmo de assinatura
                .compact();
    }

    // Valida o token JWT
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Extrai o nome de usuário do token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Verifica se o token está expirado
    private Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Extrai todas as claims do token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}