package com.beerapp.service.translator;

import com.beerapp.domain.BeerInfo;
import com.beerapp.domain.Rating;
import com.beerapp.domain.dto.BeerRatingDto;
import com.beerapp.service.UserService;
import org.springframework.stereotype.Service;

public class BeerInfoTranslator {

    private BeerInfoTranslator() {

    }

    public static BeerRatingDto toDto(BeerInfo beerInfo, Rating rating, String username) {
        BeerRatingDto dto = new BeerRatingDto();
        dto.setName(beerInfo.getName());
        dto.setCountryOfOrigin(beerInfo.getCountryOfOrigin());
        dto.setDescription(beerInfo.getDescription());
        dto.setRating(rating != null ? rating.getRating() : null);
        dto.setBeerType(beerInfo.getBeerType());
        dto.setRatingDate(rating != null ? rating.getRatingDate() : null);
        dto.setUsername(username);
        return dto;
    }
}
