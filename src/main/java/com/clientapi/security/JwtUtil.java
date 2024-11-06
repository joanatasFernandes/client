package com.clientapi.security;

import com.clientapi.config.ApplicationProperties;
import com.clientapi.model.UserEntity;
import com.clientapi.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserService userService;
    private final ApplicationProperties applicationProperties;

    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora em milissegundos

    // Gera um token JWT com base no UserDetails
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        UserEntity user = userService.findByUsername(username);
        return createToken(claims, user);
    }

    private String createToken(Map<String, Object> claims, UserEntity user) {
        byte[] keyBytes = Base64.getDecoder().decode(applicationProperties.getJwtSecret());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(user.getToken()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, keyBytes)
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
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // Extrai todas as claims do token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(applicationProperties.getJwtSecret())
                .parseClaimsJws(token).getBody();
    }
}
