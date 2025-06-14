package com.beerapp.web.request;

import jakarta.validation.constraints.NotBlank;

public class LogInRequest {
    @NotBlank(message = "Must provide email")
    private String email;
    @NotBlank(message = "Must provide password")
    private String password;

    public LogInRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
