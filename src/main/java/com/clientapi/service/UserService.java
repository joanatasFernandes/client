package com.clientapi.service;

import com.clientapi.model.User;
import com.clientapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("{noop}" + password); // O {noop} desativa a codificação para fins de teste
        userRepository.save(user);
    }
}
