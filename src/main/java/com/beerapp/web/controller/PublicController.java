package com.beerapp.web.controller;

import com.beerapp.domain.enums.BeerType;
import com.beerapp.service.BeerService;
import com.beerapp.web.resource.BeerResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final BeerService beerService;

    public PublicController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping
    public List<BeerResource> getAllBeers(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) BeerType type) {
        return beerService.getBeers(country, type).stream().map(BeerResource::new).toList();
    }

    @GetMapping("/{id}")
    public BeerResource getBeerDetails(
            @PathVariable(name = "id") UUID beerId) {
        return new BeerResource(beerService.getOne(beerId));
    }
}
