package com.beerapp.controller;

import javax.validation.constraints.NotNull;

import com.beerapp.exception.BeerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.beerapp.request.AddBeerRequest;
import com.beerapp.request.EditBeerRatingRequest;
import com.beerapp.resource.BeerInfoResource;
import com.beerapp.service.BeerInfoService;
import com.beerapp.validator.AddBeerRequestValidator;
import com.beerapp.validator.EditBeerRatingRequestValidator;

import java.util.UUID;

@Controller(value = "/admin")
public class AdminController {

    @Autowired
    BeerInfoService beerInfoService;

    @InitBinder(value = "add")
    public void initAddBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof AddBeerRequest) {
            binder.addValidators(new AddBeerRequestValidator());
        }
    }

    @InitBinder(value = "rate")
    public void initRateBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof EditBeerRatingRequest) {
            binder.addValidators(new EditBeerRatingRequestValidator());
        }
    }

    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(name = "id") UUID id) {
        return beerInfoService.deleteById(id);
    }

    @PostMapping
    public BeerInfoResource addBeer(@RequestBody @Validated @NotNull AddBeerRequest body) {
        return new BeerInfoResource(beerInfoService.addBeer(body.getName(), body.getCountry(), body.getDescription(), body.getBeerType()));
    }

    @PutMapping(value = "/{id}")
    public BeerInfoResource changeRating(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Validated @NotNull EditBeerRatingRequest body) throws BeerException {
        return new BeerInfoResource(beerInfoService.editBeerRating(id, body.getNewRating()));
    }
}
