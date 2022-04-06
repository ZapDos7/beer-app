package com.beerapp.resource;

import com.beerapp.domain.BeerInfo;
import com.beerapp.domain.enums.BeerType;
import lombok.Data;


@Data
public class BeerInfoResource {
    private String name;
    private String countryOfOrigin;
    private String description;
    private Integer rating;
    private BeerType beerType;

    public BeerInfoResource(BeerInfo beerInfo) {
        this.name = beerInfo.getName();
        this.countryOfOrigin = beerInfo.getCountryOfOrigin();
        this.description = beerInfo.getDescription();
        this.rating = beerInfo.getRating();
        this.beerType = beerInfo.getBeerType();
    }
}
