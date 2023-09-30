package com.beerapp.web.controller;

import com.beerapp.service.BeerService;
import com.beerapp.web.request.AddBeerRequest;
import com.beerapp.web.request.EditBeerRequest;
import com.beerapp.web.resource.BeerResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/beers")
public class AdminBeerController {

    private final BeerService beerService;

    public AdminBeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerResource addBeer(@RequestBody /* TODO Validate */ AddBeerRequest request) {
        return new BeerResource(beerService.addBeer(request));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BeerResource editBeer(@PathVariable(name = "id") UUID beerId, @RequestBody /* TODO Validate */ EditBeerRequest request) {
        return new BeerResource(beerService.editBeer(beerId, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable(name = "id") UUID beerId) {
        beerService.deleteBeer(beerId);
    }
}
