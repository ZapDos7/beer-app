package com.beerapp.web.resource;

import java.time.Instant;

import com.beerapp.domain.dto.BeerRatingDto;
import com.beerapp.domain.enums.BeerType;

public class BeerInfoResource {
    private String name;
    private String countryOfOrigin;
    private String description;
    private Integer rating;
    private BeerType beerType;
    private Instant ratingDate;
    private String username;

    public BeerInfoResource(BeerRatingDto dto) {
        this.name = dto.getName();
        this.countryOfOrigin = dto.getCountryOfOrigin();
        this.description = dto.getDescription();
        this.rating = dto.getRating();
        this.beerType = dto.getBeerType();
        this.ratingDate = dto.getRatingDate();
        this.username = dto.getUsername();
    }

    public BeerInfoResource() {
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
        return this.ratingDate;
    }

    public void setRatingDate(Instant date) {
        this.ratingDate = date;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsernameString(String username) {
        this.username = username;
    }
}
