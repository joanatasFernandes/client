package com.clientapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupRunner implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) {
        try {
            userService.createUser("admin", "admin", "MANAGER", "ADMIN");
        } catch (Exception e) {
            // Do nothing
        }
    }
}
