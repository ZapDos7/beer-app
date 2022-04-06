package com.beerapp.service;

import com.beerapp.domain.BeerInfo;
import com.beerapp.domain.enums.BeerType;
import com.beerapp.exception.BeerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beerapp.repository.BeerInfoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BeerInfoService {
    @Autowired
    BeerInfoRepository beerInfoRepository;

    public List<BeerInfo> getByCriteria(String country, Integer rating, BeerType beerType) {
        return beerInfoRepository.findByCriteria(country, rating, beerType);
    }

    public Optional<BeerInfo> getById(UUID id) {
        return beerInfoRepository.findById(id);
    }

    public boolean deleteById(UUID id) {
        beerInfoRepository.deleteById(id);
        return beerInfoRepository.findById(id).isEmpty();
    }

    public BeerInfo addBeer(String name, String country, String description, BeerType beerType) {
        return beerInfoRepository.save(new BeerInfo(UUID.randomUUID(), name, country, description, null, beerType));
    }

    public BeerInfo editBeerRating(UUID id, Integer newRating) throws BeerException {
        return beerInfoRepository.findById(id).map(beerInfo -> {
            beerInfo.setRating(newRating);
            beerInfoRepository.save(beerInfo);
            return  beerInfo;
        }).orElseThrow(() -> new BeerException(BeerException.BEER_NOT_FOUND));
    }

}
