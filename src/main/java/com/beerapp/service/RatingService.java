package com.beerapp.service;

import com.beerapp.domain.Rating;
import com.beerapp.domain.repository.RatingRepository;
import com.beerapp.web.request.EditRatingRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> getAllUserRatings(Long userId) {
        return ratingRepository.findByUserId(userId);
    }

    public Rating rate(Long userId, Long beerId, EditRatingRequest request) {
        var ratingOptional = ratingRepository.findByUserIdAndBeerId(userId, beerId);
        if (ratingOptional.isPresent()) {
            ratingOptional.get().setRating(request.getNewRating());
            return ratingRepository.save(ratingOptional.get());
        } else {
            return ratingRepository.save(new Rating(userId, beerId, request.getNewRating()));
        }
    }

    @Transactional
    public void deleteRating(Long userId, Long beerId) {
        ratingRepository.deleteByUserIdAndBeerId(userId, beerId);
    }
}
