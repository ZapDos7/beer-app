package com.beerapp.web.controller;

import com.beerapp.domain.enums.BeerType;
import com.beerapp.web.resource.BeerResource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping
    public List<BeerResource> getAllBeers(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) BeerType type) {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public BeerResource getBeerDetails(
            @PathVariable(name = "id") UUID beerId) {
        return new BeerResource();
    }
}
