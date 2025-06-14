package com.beerapp.web.request;

import com.beerapp.domain.enums.BeerType;
import jakarta.validation.constraints.NotBlank;

public class AddBeerRequest {
    @NotBlank(message = "Must provide name")
    private String name;
    @NotBlank(message = "Must provide country of origin")
    private String countryOfOrigin;
    private String description;
    @NotBlank(message = "Must provide beer type")
    private BeerType type;

    public AddBeerRequest() {
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

    public BeerType getType() {
        return type;
    }

    public void setType(BeerType type) {
        this.type = type;
    }
}