package com.beerapp.web.controller;

import com.beerapp.service.BeerService;
import com.beerapp.service.CountryService;
import com.beerapp.web.request.AddBeerRequest;
import com.beerapp.web.request.EditBeerRequest;
import com.beerapp.web.resource.BeerResource;
import com.beerapp.web.validator.AddBeerRequestValidator;
import com.beerapp.web.validator.EditBeerRequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/beers")
public class AdminBeerController {

    private final CountryService countryService;
    private final BeerService beerService;

    @InitBinder(value = "rate")
    public void initRateBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof AddBeerRequest) {
            binder.addValidators(new AddBeerRequestValidator(countryService));
        } else if (binder.getTarget() instanceof EditBeerRequest) {
            binder.addValidators(new EditBeerRequestValidator(countryService));
        }
    }

    public AdminBeerController(CountryService countryService, BeerService beerService) {
        this.countryService = countryService;
        this.beerService = beerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerResource addBeer(@RequestBody @Validated AddBeerRequest request) {
        return new BeerResource(beerService.addBeer(request));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BeerResource editBeer(@PathVariable(name = "id") UUID beerId, @RequestBody @Validated EditBeerRequest request) {
        return new BeerResource(beerService.editBeer(beerId, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable(name = "id") UUID beerId) {
        beerService.deleteBeer(beerId);
    }
}
