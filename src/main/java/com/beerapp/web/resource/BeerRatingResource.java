package com.beerapp.web.resource;

import com.beerapp.domain.Rating;

import java.time.Instant;
import java.util.UUID;

public class BeerRatingResource {

    private UUID beerId;
    private UUID userId;
    private int rating;
    private Instant ratingDate;

    public BeerRatingResource() {
    }

    public BeerRatingResource(Rating rating) {
        this.beerId = rating.getBeerId();
        this.userId = rating.getUserId();
        this.rating = rating.getRating();
        this.ratingDate = rating.getRatingDate();
    }

    public UUID getBeerId() {
        return beerId;
    }

    public void setBeerId(UUID beerId) {
        this.beerId = beerId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Instant getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Instant ratingDate) {
        this.ratingDate = ratingDate;
    }
}
