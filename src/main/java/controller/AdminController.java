package controller;

import javax.validation.constraints.NotNull;

import exception.BeerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import request.AddBeerRequest;
import request.EditBeerRatingRequest;
import resource.BeerInfoResource;
import service.BeerInfoService;
import validator.AddBeerRequestValidator;
import validator.EditBeerRatingRequestValidator;

import java.util.UUID;

@Controller(value = "/admin")
public class AdminController {

    @Autowired
    BeerInfoService beerInfoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof AddBeerRequest || binder.getTarget() instanceof EditBeerRatingRequest) {
            binder.addValidators(new AddBeerRequestValidator());
            binder.addValidators(new EditBeerRatingRequestValidator());
        }

    }

    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(name = "id") UUID id) {
        return beerInfoService.deleteById(id);
    }

    @PostMapping
    public BeerInfoResource addBeer(@RequestBody @Validated @NotNull AddBeerRequest body) {
        return new BeerInfoResource(beerInfoService.addBeer(body.getName(), body.getCountry(), body.getDescription()));
    }

    @PutMapping(value = "/{id}")
    public BeerInfoResource changeRating(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Validated @NotNull EditBeerRatingRequest body) throws BeerException {
        return new BeerInfoResource(beerInfoService.editBeerRating(id, body.getNewRating()));
    }
}
