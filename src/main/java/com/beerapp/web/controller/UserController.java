package com.beerapp.web.controller;

import com.beerapp.web.request.EditBeerRequest;
import com.beerapp.web.resource.BeerRatingResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user/{userId}/rate/{beerId}")
public class UserController {

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public BeerRatingResource rate(
            @PathVariable(name = "userId") UUID userId,
            @PathVariable(name = "beerId") UUID beerId,
            @RequestBody /* TODO Validate */ EditBeerRequest request) {
        return new BeerRatingResource();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteARating(
            @PathVariable(name = "userId") UUID userId,
            @PathVariable(name = "beerId") UUID beerId) {
        //
    }
}
