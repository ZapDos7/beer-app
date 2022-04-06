package com.beerapp.request;

import com.beerapp.domain.enums.BeerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBeerRequest {
    private String name;
    private String country;
    private String description;
    private BeerType beerType;
}
