package com.beerapp.domain;

import com.beerapp.domain.enums.BeerType;
import com.beerapp.web.request.AddBeerRequest;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "beer")
public class Beer {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "country_of_origin")
    private String countryOfOrigin;
    @Column(name = "description")
    private String description;
    @Column(name = "type") //('ALE', 'LAGER', 'PORTER', 'STOUT', 'BLONDE_ALE', 'BROWN_ALE', 'PALE_ALE', 'IPA', 'WHEAT', 'PILSNER', 'SOUR_ALE') NOT NULL,
    @Enumerated(EnumType.STRING)
    private BeerType type;
    @Column(name = "date_created")
    @CreatedDate
    private Instant dateCreated;
    @Column(name = "more_info")
    private String moreInfo;

    public Beer() {
    }

    public Beer(AddBeerRequest request) {
        this.name = request.getName();
        this.countryOfOrigin = request.getCountryOfOrigin();
        this.description = request.getDescription();
        this.type = request.getType();
        this.dateCreated = Instant.now();
        this.moreInfo = request.getMoreInfo();
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