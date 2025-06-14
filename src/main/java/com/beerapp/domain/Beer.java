package com.beerapp.domain;

import com.beerapp.domain.enums.BeerType;
import com.beerapp.web.request.AddBeerRequest;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "beer")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
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
    private LocalDateTime dateCreated;

    public Beer() {
    }

    public Beer(AddBeerRequest request) {
        this.name = request.getName();
        this.countryOfOrigin = request.getCountryOfOrigin();
        this.description = request.getDescription();
        this.type = request.getType();
        this.dateCreated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}