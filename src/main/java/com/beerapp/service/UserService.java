package com.beerapp.service;

import com.beerapp.domain.User;
import com.beerapp.domain.VerificationToken;
import com.beerapp.domain.enums.Role;
import com.beerapp.domain.repository.UserRepository;
import com.beerapp.domain.repository.VerificationTokenRepository;
import com.beerapp.exceptions.BadRequestException;
import com.beerapp.exceptions.ErrorCode;
import com.beerapp.exceptions.NotFoundException;
import com.beerapp.web.request.SignUpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Value("${beer.token-expiration}")
    private int tokenExpirationDuration;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
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
        userRepository.save(user);
        // verification token
        UUID token = UUID.randomUUID();
        VerificationToken verificationToken = new VerificationToken(token, user, Instant.now().plus(tokenExpirationDuration, ChronoUnit.MINUTES));
        verificationTokenRepository.save(verificationToken);
        // return
        return user;
    }

    public void deleteById(Long userId, boolean userRequest) {
        if (userRequest) {
            logger.trace("User {} requested account deletion", userId);
        } else {
            logger.trace("Purging user {} account", userId);
        }
        userRepository.deleteById(userId);
    }

    public void verifyUser(UUID token) throws BadRequestException {
        var verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(() -> {
            logger.error("Could not find token with value {}", token);
            return new BadRequestException(ErrorCode.AUTH_TOKEN_NOT_FOUND);
        });
        if (verificationToken == null) {
            throw new BadRequestException(ErrorCode.AUTH_TOKEN_INVALID);
        }
        if (verificationToken.getExpiryDate().isBefore(Instant.now())) {
            throw new BadRequestException(ErrorCode.AUTH_TOKEN_EXPIRED);
        }
        User user = verificationToken.getUser();
        user.setVerified(true);
        userRepository.save(user);

        // cleanup token
        verificationTokenRepository.delete(verificationToken);
    }
}
