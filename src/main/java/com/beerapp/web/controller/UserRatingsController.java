package com.beerapp.web.controller;

import com.beerapp.domain.Rating;
import com.beerapp.exceptions.BeerException;
import com.beerapp.service.RatingService;
import com.beerapp.web.request.EditRatingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Rating>> viewUserRatings(
            @PathVariable(name = "userId") UUID userId) {
        return ResponseEntity.ok(ratingService.getAllUserRatings(userId));
    }

    @Operation(summary = "Edit a rating of a user")
    @PutMapping("/{beerId}")
    public ResponseEntity<Rating> rate(
            @PathVariable(name = "userId") UUID userId,
            @PathVariable(name = "beerId") UUID beerId,
            @RequestBody @Valid EditRatingRequest request) {
        try {
            return ResponseEntity.ok((ratingService.rate(userId, beerId, request)));
        } catch (BeerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a rating of a user")
    @DeleteMapping("/{beerId}")
    public ResponseEntity<Void> deleteARating(
            @PathVariable(name = "userId") UUID userId,
            @PathVariable(name = "beerId") UUID beerId) {
        ratingService.deleteRating(userId, beerId);
        return ResponseEntity.noContent().build();
    }
}
