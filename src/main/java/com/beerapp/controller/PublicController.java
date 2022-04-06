package com.beerapp.controller;

import com.beerapp.domain.enums.BeerType;
import com.beerapp.exception.BeerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.beerapp.resource.BeerInfoResource;
import com.beerapp.service.BeerInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller(value = "/public")
public class PublicController {

    @Autowired
    BeerInfoService beerInfoService;

    @GetMapping
    public List<BeerInfoResource> getAllBeers(
            @RequestParam(required = false, name= "country") String country,
            @RequestParam(required = false, name= "rating") Integer rating,
            @RequestParam(required = false, name= "beerType") BeerType beerType
    ) {
        List<BeerInfoResource> beers = new ArrayList<>();
        beerInfoService.getByCriteria(country,rating, beerType).forEach(beerInfo -> beers.add(new BeerInfoResource(beerInfo)));
        return beers;
    }

    @GetMapping(value = "{id}")
    public BeerInfoResource getOneBeer (@PathVariable(name = "id") UUID id) throws BeerException {
        return new BeerInfoResource(beerInfoService.getById(id).orElseThrow(() -> new BeerException(BeerException.BEER_NOT_FOUND)));
    }
}
