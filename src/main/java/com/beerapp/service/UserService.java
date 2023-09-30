package com.beerapp.service;

import com.beerapp.domain.User;
import com.beerapp.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteById(UUID userId) {
        userRepository.deleteById(userId);
    }
}
