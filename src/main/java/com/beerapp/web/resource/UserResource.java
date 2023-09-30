package com.beerapp.web.resource;

import com.beerapp.domain.User;
import com.beerapp.domain.enums.Role;

import java.time.Instant;
import java.util.UUID;

public class UserResource {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Instant signUpDate;

    public UserResource() {
    }

    public UserResource(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.signUpDate = user.getSignUpDate();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Instant signUpDate) {
        this.signUpDate = signUpDate;
    }
}
