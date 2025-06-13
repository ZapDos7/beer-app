package com.beerapp.web.controller;

import com.beerapp.domain.Beer;
import com.beerapp.domain.enums.BeerType;
import com.beerapp.exceptions.BeerException;
import com.beerapp.service.BeerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Beer>> getAllBeers(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) BeerType type) {
        return ResponseEntity.ok(beerService.getBeers(country, type));
    }

    @Operation(summary = "Public: See an available beer's details")
    @GetMapping("/{id}")
    public ResponseEntity<Beer> getBeerDetails(@PathVariable(name = "id") UUID id) throws BeerException {
        return ResponseEntity.ok(beerService.getOne(id));
    }
}
