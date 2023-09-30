package com.beerapp.web.controller;

import com.beerapp.web.request.AddBeerRequest;
import com.beerapp.web.request.EditBeerRequest;
import com.beerapp.web.resource.BeerResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/beers")
public class AdminBeerController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerResource addBeer(@RequestBody AddBeerRequest request) {
        return new BeerResource();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BeerResource editBeer(@PathVariable(name = "id") UUID beerId, @RequestBody EditBeerRequest request) {
        return new BeerResource();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable(name = "id") UUID beerId) {
        //
    }
}
