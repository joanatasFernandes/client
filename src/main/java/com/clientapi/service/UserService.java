package com.clientapi.service;

import com.clientapi.model.UserEntity;
import com.clientapi.repository.UserRepository;
import com.clientapi.security.EncryptService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EncryptService encryptService;
    private final UserRepository userRepository;

    @Transactional
    public void createUser(String username, String password, String... roles) {
        UserEntity user = new UserEntity();
        EncryptService.EncryptData encrypt = encryptService.encrypt(password);
        user.setUsername(username);
        user.setRoles(String.join(",", roles));
        user.setPassword(encrypt.password());
        user.setToken(encrypt.token());

        userRepository.save(user);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
