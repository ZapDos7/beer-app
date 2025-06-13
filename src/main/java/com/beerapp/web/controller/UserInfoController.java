package com.beerapp.web.controller;

import com.beerapp.domain.User;
import com.beerapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User", description = "Manage info as user")
@RestController
@RequestMapping("/user/{userId}/")
public class UserInfoController {

    private final UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get user's details")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public User getUserDetails(@PathVariable UUID id) {
        return userService.getUserDetails(id);
    }
}
