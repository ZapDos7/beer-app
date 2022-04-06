package com.beerapp.repository;

import com.beerapp.domain.BeerInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface BeerInfoRepository extends MongoRepository<BeerInfo, UUID>, CustomizedBeerInfoRepository {
}
