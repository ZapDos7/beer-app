package com.beerapp.web.controller;

import com.beerapp.domain.Beer;
import com.beerapp.web.request.AddBeerRequest;
import com.beerapp.web.request.EditBeerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/beers")
public class AdminBeerController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer addBeer(@RequestBody AddBeerRequest request) {
        return new Beer();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Beer editBeer(@PathVariable(name = "id") UUID beerId, @RequestBody EditBeerRequest request) {
        return new Beer();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable(name = "id") UUID beerId) {
        //
    }
}
