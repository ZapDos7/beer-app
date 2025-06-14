package com.beerapp.web.controller;

import com.beerapp.domain.User;
import com.beerapp.web.utils.AuthUserProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "Manage info as user")
@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user")
public class UserInfoController {

    @Operation(summary = "Get user's details")
    @GetMapping
    public ResponseEntity<User> getUserDetails() {
        return ResponseEntity.ok(AuthUserProvider.getCurrentUser());
    }
}
