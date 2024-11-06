package com.clientapi.controller;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data// Classe auxiliar para receber as credenciais
public class AuthRequest {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;

    private String password;

}
