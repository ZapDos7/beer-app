package com.beerapp.web.controller;

import com.beerapp.domain.Rating;
import com.beerapp.service.RatingService;
import com.beerapp.web.request.EditRatingRequest;
import com.beerapp.web.utils.AuthUserProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ratings", description = "Manage user's ratings")
@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/ratings/")
public class UserRatingsController {

    private final RatingService ratingService;

    public UserRatingsController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Operation(summary = "Get all ratings of a user")
    @GetMapping
    public ResponseEntity<List<Rating>> viewUserRatings() {
        return ResponseEntity.ok(ratingService.getAllUserRatings(AuthUserProvider.getCurrentUser().getId()));
    }

    @Operation(summary = "Add a new rating or edit an existing one")
    @PutMapping("/{id}")
    public ResponseEntity<Rating> rate(@PathVariable(name = "id") Long id, @RequestBody @Valid EditRatingRequest request) {
        return ResponseEntity.ok((ratingService.rate(AuthUserProvider.getCurrentUser().getId(), id, request)));
    }

    @Operation(summary = "Delete a rating for a beer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteARating(@PathVariable(name = "id") Long id) {
        ratingService.deleteRating(AuthUserProvider.getCurrentUser().getId(), id);
        return ResponseEntity.noContent().build();
    }
}
