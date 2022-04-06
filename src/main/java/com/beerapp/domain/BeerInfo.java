package com.beerapp.domain;

import com.beerapp.domain.enums.BeerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@AllArgsConstructor
@Data
public class BeerInfo {
    @Id
    private UUID id;
    private String name;
    private String countryOfOrigin;
    private String description;
    private Integer rating;
    private BeerType beerType;
}
