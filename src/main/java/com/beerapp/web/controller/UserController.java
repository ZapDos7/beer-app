package com.beerapp.web.controller;

import com.beerapp.service.UserService;
import com.beerapp.web.request.AddUserRequest;
import com.beerapp.web.resource.UserResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResource addUser(@RequestBody /*@Validated @NotNull*/ AddUserRequest body) {
        return new UserResource(userService.addUser(body));
    }

    @GetMapping("/{id]")
    public UserResource getUser(@PathVariable("id") UUID id) {
        return new UserResource(userService.getUserById(id));
    }
}
