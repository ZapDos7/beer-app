package com.beerapp.service;

import com.beerapp.domain.User;
import com.beerapp.domain.enums.UserRole;
import com.beerapp.domain.exception.BeerException;
import com.beerapp.domain.repository.RatingRepository;
import com.beerapp.domain.repository.UserRepository;
import com.beerapp.web.request.AddUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.beerapp.domain.exception.BeerException.USER_NOT_FOUND;

@Service
public class UserService /*implements UserDetailsService*/ {
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    public UserService(UserRepository userRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    public User getUserById(UUID id) {
        return userRepository.getById(id);
    }

    public User addUser(AddUserRequest account) {
        String username = account.getUsername();
        String email = account.getEmail();
        String code = account.getPassword(); //bcryptEncoder.encode(account.getPassword());
        UserRole role = UserRole.USER;
        return userRepository.save(new User(username, email, code, role));
    }

    public void deleteUser(UUID userId) throws BeerException {
        if (userRepository.existsById(userId)) {
            ratingRepository.deleteByUserId(userId);
            userRepository.deleteById(userId);
        } else {
            throw new BeerException(USER_NOT_FOUND);
        }

    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    // TODO security
}
