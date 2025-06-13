package com.beerapp.web.request;

import jakarta.validation.constraints.NotBlank;

public class EditUserRequest {
    @NotBlank(message = "Must provide name")
    private String name;

    public EditUserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
