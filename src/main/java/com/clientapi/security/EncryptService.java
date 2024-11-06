package com.clientapi.security;

import com.clientapi.config.ApplicationProperties;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EncryptService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EncryptService(ApplicationProperties applicationProperties) {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(applicationProperties.getEncryptStrength());
    }

    public EncryptData encrypt(String password) {
        String encryptedPass = bCryptPasswordEncoder.encode(password);
        String token = bCryptPasswordEncoder.encode(password + encryptedPass);
        return new EncryptData(encryptedPass, token);
    }

    public record EncryptData(String password, String token) {
    }
}
