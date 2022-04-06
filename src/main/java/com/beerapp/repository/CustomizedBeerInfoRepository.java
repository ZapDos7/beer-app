package com.beerapp.repository;

import com.beerapp.domain.BeerInfo;
import com.beerapp.domain.enums.BeerType;

import java.util.List;

public interface CustomizedBeerInfoRepository {
    List<BeerInfo> findByCriteria(String country, Integer rating, BeerType beerType);
}
