package com.beerapp.web.controller;

import com.beerapp.config.JwtUtil;
import com.beerapp.domain.User;
import com.beerapp.exceptions.BadRequestException;
import com.beerapp.exceptions.NotFoundException;
import com.beerapp.service.UserService;
import com.beerapp.web.request.LogInRequest;
import com.beerapp.web.request.SignUpRequest;
import com.beerapp.web.utils.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LogInRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtUtil.generateToken((UserDetails) auth.getPrincipal());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // receive verification endpoint
    @GetMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") UUID token) throws NotFoundException, BadRequestException {
        userService.verifyUser(token);
        return ResponseEntity.noContent().build();
    }
}
