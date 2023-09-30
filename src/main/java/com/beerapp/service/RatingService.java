package com.beerapp.service;

import com.beerapp.domain.Rating;
import com.beerapp.domain.repository.RatingRepository;
import com.beerapp.exceptions.BeerException;
import com.beerapp.web.request.EditRatingRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> getAllUserRatings(UUID userId) {
        return ratingRepository.findByUserId(userId);
    }

    public Rating rate(UUID userId, UUID beerId, EditRatingRequest request) throws BeerException {
        var rating = ratingRepository.findByUserIdAndBeerId(userId, beerId)
                .orElseThrow(() -> new BeerException(BeerException.BEER_NOT_FOUND));
        rating.setRating(request.getNewRating()); //rating.setRating(request.getNewRating())
        rating.setRatingDate(Instant.now()); // todo check if needed or omitted que to sql
        return ratingRepository.save(rating);
    }

    public void deleteRating(UUID userId, UUID beerId) {
        ratingRepository.deleteByUserIdAndBeerId(userId, beerId);
    }
}
