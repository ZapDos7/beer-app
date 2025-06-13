package com.beerapp.web.controller;

import com.beerapp.domain.User;
import com.beerapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user/{userId}/")
public class UserInfoController {

    private final UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public User getUserDetails(@PathVariable UUID id) {
        return userService.getUserDetails(id);
    }
}
