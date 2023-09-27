package com.beerapp.web.controller;

import com.beerapp.domain.User;
import com.beerapp.domain.enums.BeerType;
import com.beerapp.domain.enums.UserRole;
import com.beerapp.domain.exception.BeerException;
import com.beerapp.service.BeerInfoService;
import com.beerapp.web.request.EditBeerRatingRequest;
import com.beerapp.web.resource.BeerInfoResource;
import com.beerapp.web.validator.EditBeerRatingRequestValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Controller(value = "/public")
public class PublicController {

    private final BeerInfoService beerInfoService;

    @InitBinder(value = "rate")
    public void initRateBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof EditBeerRatingRequest) {
            binder.addValidators(new EditBeerRatingRequestValidator());
        }
    }

    public PublicController(BeerInfoService beerInfoService) {
        this.beerInfoService = beerInfoService;
    }

    @GetMapping
    public List<BeerInfoResource> getAllBeers(
            @RequestParam(required = false, name= "country") String country,
            @RequestParam(required = false, name= "rating") Integer rating,
            @RequestParam(required = false, name= "beerType") BeerType beerType
    ) {
        return beerInfoService.getByCriteria(country,rating, beerType).stream().map(BeerInfoResource::new).toList();
    }

    @GetMapping(value = "{id}")
    public BeerInfoResource getOneBeer (@PathVariable(name = "id") UUID id) throws BeerException {
        return new BeerInfoResource(beerInfoService.getById(id));
    }


    @PutMapping(value = "/{id}")
    public BeerInfoResource changeRating(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Validated @NotNull EditBeerRatingRequest body) throws BeerException {
        User user = new User("name", "mail", "pw", UserRole.USER); // ToDo: authorize
        return new BeerInfoResource(beerInfoService.editBeerRating(id, body.getNewRating(), user));
    }
}
