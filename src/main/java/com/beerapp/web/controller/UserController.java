package com.beerapp.web.controller;

import com.beerapp.exceptions.BeerException;
import com.beerapp.service.RatingService;
import com.beerapp.web.request.EditRatingRequest;
import com.beerapp.web.resource.BeerRatingResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/{userId}/rate/")
public class UserController {

    private final RatingService ratingService;

    public UserController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<BeerRatingResource> viewUserRatings(
            @PathVariable(name = "userId") UUID userId) {
        return ratingService.getAllUserRatings(userId).stream().map(BeerRatingResource::new).toList();
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerRatingResource rate(
            @PathVariable(name = "userId") UUID userId,
            @PathVariable(name = "beerId") UUID beerId,
            @RequestBody @Valid EditRatingRequest request) throws BeerException {
        return new BeerRatingResource(ratingService.rate(userId, beerId, request));
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteARating(
            @PathVariable(name = "userId") UUID userId,
            @PathVariable(name = "beerId") UUID beerId) {
        ratingService.deleteRating(userId, beerId);
    }
}
