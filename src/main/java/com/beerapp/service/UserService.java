package com.beerapp.service;

import com.beerapp.domain.User;
import com.beerapp.domain.repository.UserRepository;
import com.beerapp.exceptions.BeerException;
import com.beerapp.web.request.EditUserRequest;
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

    public User getUserDetails(UUID userId) throws BeerException {
        logger.trace("Fetching info for user {}", userId);
        return userRepository.findById(userId).orElseThrow(() -> {
            logger.error("Could not find user with id {}", userId);
            return new BeerException("User", userId);
        });
    }

    public List<User> getAll() {
        logger.trace("Fetching all users");
        return userRepository.findAll();
    }

    public User editUserDetails(UUID id, EditUserRequest request) throws BeerException {
        logger.trace("Editing user {} with new name {}", id, request.getName());
        User user = getUserDetails(id);
        user.setName(request.getName());
        return userRepository.save(user);
    }

    public void deleteById(UUID userId) {
        logger.trace("Deleting user {}", userId);
        userRepository.deleteById(userId);
    }
}
