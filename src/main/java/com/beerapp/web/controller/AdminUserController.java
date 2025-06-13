package com.beerapp.web.controller;

import com.beerapp.service.UserService;
import com.beerapp.web.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "Admin Users", description = "Manage users as admin")
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Admin: Get all users")
    @GetMapping
    public List<UserResource> getUsers() {
        return userService.getAll().stream().map(UserResource::new).toList();
    }

    @Operation(summary = "Admin: Delete a user")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "id") UUID userId) {
        userService.deleteById(userId);
    }
}
