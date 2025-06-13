package com.beerapp.service;

import com.beerapp.domain.User;
import com.beerapp.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserDetails(UUID userId) {
        logger.trace("Fetching info for user {}", userId);
        return userRepository.getById(userId);
    }

    public List<User> getAll() {
        logger.trace("Fetching all users");
        return userRepository.findAll();
    }

    public void deleteById(UUID userId) {
        logger.trace("Deleting user {}", userId);
        userRepository.deleteById(userId);
    }
}
