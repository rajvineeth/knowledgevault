package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Token {
    String username,token,role;

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
