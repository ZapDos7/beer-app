package com.beerapp.web.controller;

import com.beerapp.domain.enums.BeerType;
import com.beerapp.service.BeerService;
import com.beerapp.web.resource.BeerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Beers", description = "View beers")
@RestController
@RequestMapping("/public")
public class PublicController {

    private final BeerService beerService;

    public PublicController(BeerService beerService) {
        this.beerService = beerService;
    }

    @Operation(summary = "Public: See all available beers")
    @GetMapping
    public List<BeerResource> getAllBeers(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) BeerType type) {
        return beerService.getBeers(country, type).stream().map(BeerResource::new).toList();
    }

    @Operation(summary = "Public: See an available beer's details")
    @GetMapping("/{id}")
    public BeerResource getBeerDetails(
            @PathVariable(name = "id") UUID beerId) {
        return new BeerResource(beerService.getOne(beerId));
    }
}
