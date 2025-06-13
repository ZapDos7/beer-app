package com.beerapp.service;


import com.beerapp.domain.Beer;
import com.beerapp.domain.enums.BeerType;
import com.beerapp.domain.repository.BeerRepository;
import com.beerapp.exceptions.BeerException;
import com.beerapp.web.request.AddBeerRequest;
import com.beerapp.web.request.EditBeerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BeerService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BeerRepository beerRepository;
    private final CountryService countryService;

    BeerService(BeerRepository beerRepository, CountryService countryService) {
        this.beerRepository = beerRepository;
        this.countryService = countryService;
    }

    /* Public */

    public List<Beer> getBeers(String country, BeerType type) {
        return beerRepository.findByCriteria(country, type);
    }

    public Beer getOne(UUID id) throws BeerException {
        return beerRepository.findById(id).orElseThrow(() -> {
            logger.error("Could not find beer with id {}", id);
            return new BeerException("Beer", id);
        });
    }

    /* Admin */

    //add
    public Beer addBeer(AddBeerRequest request) throws BeerException {
        if (!countryService.isSupportedCountryName(request.getCountryOfOrigin())) {
            throw new BeerException("edit beer: invalid country name");
        }
        return beerRepository.save(new Beer(request));
    }

    //edit

    public Beer editBeer(UUID beerId, EditBeerRequest request) throws BeerException{
        var beer = getOne(beerId);

        if (request.getName() != null) {
            beer.setName(request.getName());
        }
        if (request.getCountryOfOrigin() != null) {
            if (!countryService.isSupportedCountryName(request.getCountryOfOrigin())) {
                throw new BeerException("edit beer: invalid country name");
            }
            beer.setCountryOfOrigin(request.getCountryOfOrigin());
        }
        if (request.getDescription() != null) {
            beer.setDescription(request.getDescription());
        }
        if (request.getType() != null) {
            beer.setType(BeerType.valueOf(request.getType()));
        }
        if (request.getMoreInfo() != null) {
            beer.setMoreInfo(request.getMoreInfo());
        }
        return beerRepository.save(beer);
    }

    //delete
    public void deleteBeer(UUID beerId) {
        beerRepository.deleteById(beerId);
    }
}
