package com.beerapp.service;


import com.beerapp.domain.Beer;
import com.beerapp.domain.enums.BeerType;
import com.beerapp.domain.repository.BeerRepository;
import com.beerapp.exceptions.BadRequestException;
import com.beerapp.exceptions.ErrorCode;
import com.beerapp.exceptions.NotFoundException;
import com.beerapp.web.request.AddBeerRequest;
import com.beerapp.web.request.EditBeerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Beer getOne(Long id) throws NotFoundException {
        return beerRepository.findById(id).orElseThrow(() -> {
            logger.error("Could not find beer with id {}", id);
            return new NotFoundException(ErrorCode.BEER_NOT_FOUND);
        });
    }

    /* Admin */

    //add
    public Beer addBeer(AddBeerRequest request) throws BadRequestException {
        if (!countryService.isSupportedCountryName(request.getCountryOfOrigin())) {
            throw new BadRequestException(ErrorCode.UNSUPPORTED_COUNTRY);
        }
        return beerRepository.save(new Beer(request));
    }

    //edit

    public Beer editBeer(Long beerId, EditBeerRequest request) throws BadRequestException, NotFoundException {
        var beer = getOne(beerId);

        if (request.getName() != null) {
            beer.setName(request.getName());
        }
        if (request.getCountryOfOrigin() != null) {
            if (!countryService.isSupportedCountryName(request.getCountryOfOrigin())) {
                throw new BadRequestException(ErrorCode.UNSUPPORTED_COUNTRY);
            }
            beer.setCountryOfOrigin(request.getCountryOfOrigin());
        }
        if (request.getDescription() != null) {
            beer.setDescription(request.getDescription());
        }
        if (request.getType() != null) {
            beer.setType(BeerType.valueOf(request.getType()));
        }
        return beerRepository.save(beer);
    }

    //delete
    public void deleteBeer(Long beerId) {
        beerRepository.deleteById(beerId);
    }
}
