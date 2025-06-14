package com.beerapp.service;

import com.beerapp.domain.User;
import com.beerapp.domain.enums.Role;
import com.beerapp.domain.repository.UserRepository;
import com.beerapp.web.request.SignUpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        logger.trace("Fetching all users");
        return userRepository.findAllByRole(Role.USER);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> {
            logger.error("Could not find user with username {}", username);
            return new UsernameNotFoundException(username);
        });
    }

    public User createUser(SignUpRequest request) {
        logger.trace("Creating new user");
        User user = new User(request.getEmail(), request.getIsAdmin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public void deleteById(Long userId) {
        logger.trace("Deleting user {}", userId);
        userRepository.deleteById(userId);
    }
}
