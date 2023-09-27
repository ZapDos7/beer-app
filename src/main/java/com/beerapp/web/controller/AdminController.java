package com.beerapp.web.controller;

import com.beerapp.service.BeerInfoService;
import com.beerapp.service.UserService;
import com.beerapp.web.request.AddBeerRequest;
import com.beerapp.web.resource.BeerInfoResource;
import com.beerapp.web.resource.UserResource;
import com.beerapp.web.validator.AddBeerRequestValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Controller(value = "/admin")
public class AdminController {

    private final BeerInfoService beerInfoService;
    private final UserService userService;

    public AdminController(BeerInfoService beerInfoService, UserService userService) {
        this.beerInfoService = beerInfoService;
        this.userService = userService;
    }

    @InitBinder(value = "add")
    public void initAddBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof AddBeerRequest) {
            binder.addValidators(new AddBeerRequestValidator());
        }
    }

    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(name = "id") UUID id) {
        return beerInfoService.deleteById(id);
    }

    @GetMapping
    public List<UserResource> getUsers() {
        return userService.getAll().stream().map(UserResource::new).toList();
    }

    @PostMapping
    public BeerInfoResource addBeer(@RequestBody @Validated @NotNull AddBeerRequest body) {
        return new BeerInfoResource(beerInfoService.addBeer(body));
    }
}
