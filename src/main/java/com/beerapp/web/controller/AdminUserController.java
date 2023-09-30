package com.beerapp.web.controller;

import com.beerapp.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @GetMapping
    public List<User> getUsers() {
        return new ArrayList<>();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "id") UUID userId) {
        //
    }
}
