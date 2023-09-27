package com.beerapp.domain;

import com.beerapp.domain.enums.BeerType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "beer")
public class BeerInfo {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "country_of_origin")
    private String countryOfOrigin;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private BeerType type;

    public BeerInfo(UUID id, String name, String countryOfOrigin, String description, BeerType beerType) {
        this.id = id;
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
        this.description = description;
        this.type = beerType;
    }

    public BeerInfo() {
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

    public BeerType getBeerType() {
        return type;
    }

    public void setBeerType(BeerType beerType) {
        this.type = beerType;
    }
}
