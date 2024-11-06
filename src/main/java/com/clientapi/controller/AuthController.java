package com.clientapi.controller;

import client.api.AuthenticateApi;
import client.model.AuthRequestDto;
import client.model.AuthenticateResponseDto;
import com.clientapi.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthenticateApi {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<AuthenticateResponseDto> authenticate(AuthRequestDto authRequestDto) {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    authRequestDto.getUsername(),
                    authRequestDto.getPassword()
            );

            Authentication authenticate = authenticationManager.authenticate(authentication);
            if (!authenticate.isAuthenticated()) {
                return ResponseEntity.badRequest().build();
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }
        String jwt = jwtUtil.generateToken(authRequestDto.getUsername());
        return ResponseEntity.ok(new AuthenticateResponseDto().jwt(jwt));
    }
}


