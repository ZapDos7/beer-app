package com.beerapp.service;


import com.beerapp.domain.Beer;
import com.beerapp.domain.enums.BeerType;
import com.beerapp.domain.repository.BeerRepository;
import com.beerapp.web.request.AddBeerRequest;
import com.beerapp.web.request.EditBeerRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BeerService {
    private final BeerRepository beerRepository;

    BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    /* Public */

    public List<Beer> getBeers(String country, BeerType type) {
        return beerRepository.findByCriteria(country, type);
    }

    public Beer getOne(UUID id) {
        return beerRepository.getById(id);
    }

    /* Admin */

    //add
    public Beer addBeer(AddBeerRequest request) {
        return beerRepository.save(new Beer(request));
    }

    //edit

    public Beer editBeer(UUID beerId, EditBeerRequest request) {
        var beer = beerRepository.getById(beerId);
        if (request.getName().isPresent()) {
            beer.setName(request.getName().get());
        }
        if (request.getCountryOfOrigin().isPresent()) {
            beer.setCountryOfOrigin(request.getCountryOfOrigin().get());
        }
        if (request.getDescription().isPresent()) {
            beer.setDescription(request.getDescription().get());
        }
        if (request.getType().isPresent()) {
            beer.setType(request.getType().get());
        }
        if (request.getMoreInfo().isPresent()) {
            beer.setMoreInfo(request.getMoreInfo().get());
        }
        return beerRepository.save(beer);
    }

    //delete
    public void deleteBeer(UUID beerId) {
        beerRepository.deleteById(beerId);
    }
}
