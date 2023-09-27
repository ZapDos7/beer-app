package com.beerapp.domain.dto;

import com.beerapp.domain.enums.BeerType;

import java.time.Instant;

public class BeerRatingDto {
    private String name;
    private String countryOfOrigin;
    private String description;
    private Integer rating;
    private BeerType beerType;
    private Instant ratingDate;
    private String username;

    public BeerRatingDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public BeerType getBeerType() {
        return beerType;
    }

    public void setBeerType(BeerType beerType) {
        this.beerType = beerType;
    }

    public Instant getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Instant ratingDate) {
        this.ratingDate = ratingDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
