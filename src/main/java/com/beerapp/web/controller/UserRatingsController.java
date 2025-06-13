package com.beerapp.web.controller;

import com.beerapp.exceptions.BeerException;
import com.beerapp.service.RatingService;
import com.beerapp.web.request.EditRatingRequest;
import com.beerapp.web.resource.BeerRatingResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@Tag(name = "Ratings", description = "Manage user's ratings")
@RestController
@RequestMapping("/user/{userId}/rate/")
public class UserRatingsController {

    private final RatingService ratingService;

    public UserRatingsController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Operation(summary = "Get all ratings of a user")
    @GetMapping
    public List<BeerRatingResource> viewUserRatings(
            @PathVariable(name = "userId") UUID userId) {
        return ratingService.getAllUserRatings(userId).stream().map(BeerRatingResource::new).toList();
    }

    @Operation(summary = "Edit a rating of a user")
    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerRatingResource rate(
            @PathVariable(name = "userId") UUID userId,
            @PathVariable(name = "beerId") UUID beerId,
            @RequestBody @Valid EditRatingRequest request) throws BeerException {
        return new BeerRatingResource(ratingService.rate(userId, beerId, request));
    }

    @Operation(summary = "Delete a rating of a user")
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteARating(
            @PathVariable(name = "userId") UUID userId,
            @PathVariable(name = "beerId") UUID beerId) {
        ratingService.deleteRating(userId, beerId);
    }
}
