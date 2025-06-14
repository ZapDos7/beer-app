package com.beerapp.web.utils;

import com.beerapp.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUserProvider {

    private AuthUserProvider() {}

    public static User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Principal: " + auth.getPrincipal());
        System.out.println("Authorities: " + auth.getAuthorities());
        System.out.println("Authenticated: " + auth.isAuthenticated());

        if (auth.getPrincipal() instanceof User user) {
            return user;
        } else {
            throw new IllegalStateException("Authenticated principal is not a User");
        }
    }
}
