package com.beerapp.web.controller;

import com.beerapp.domain.User;
import com.beerapp.exceptions.BeerException;
import com.beerapp.service.UserService;
import com.beerapp.web.request.EditUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> getUserDetails(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(userService.getUserDetails(id));
        } catch (BeerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Edit user's details")
    @PatchMapping // partial update
    public ResponseEntity<User> editUserDetails(@PathVariable UUID id, @RequestBody EditUserRequest request) {
        try {
            return ResponseEntity.ok(userService.editUserDetails(id, request));
        } catch (BeerException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
