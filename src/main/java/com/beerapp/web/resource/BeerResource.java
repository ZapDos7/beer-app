package com.beerapp.web.resource;

import com.beerapp.domain.Beer;
import com.beerapp.domain.enums.BeerType;

import java.time.Instant;
import java.util.UUID;

public class BeerResource {
    private UUID id;
    private String name;
    private String countryOfOrigin;
    private String description;
    private BeerType type;
    private Instant dateCreated;
    private String moreInfo;

    public BeerResource() {
    }

    public BeerResource(Beer beer) {
        this.id = beer.getId();
        this.name = beer.getName();
        this.countryOfOrigin = beer.getCountryOfOrigin();
        this.description = beer.getDescription();
        this.type = beer.getType();
        this.dateCreated = beer.getDateCreated();
        this.moreInfo = beer.getMoreInfo();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
