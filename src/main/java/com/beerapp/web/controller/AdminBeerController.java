package com.beerapp.web.controller;

import com.beerapp.domain.Beer;
import com.beerapp.exceptions.BeerException;
import com.beerapp.service.BeerService;
import com.beerapp.web.request.AddBeerRequest;
import com.beerapp.web.request.EditBeerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Admin Beers", description = "Manage beers as admin")
@RestController
@RequestMapping("/admin/beers")
public class AdminBeerController {

    private final BeerService beerService;

    public AdminBeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @Operation(summary = "Admin: Add a beer")
    @PostMapping
    public ResponseEntity<Beer> addBeer(@RequestBody @Valid AddBeerRequest request) {
        try {
            return ResponseEntity.ok(beerService.addBeer(request));
        } catch (BeerException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Admin: Edit a beer")
    @PatchMapping("/{id}")
    public ResponseEntity<Beer> editBeer(@PathVariable(name = "id") UUID id, @RequestBody @Valid EditBeerRequest request) {
        try {
            return ResponseEntity.ok(beerService.editBeer(id, request));
        } catch (BeerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Admin: Delete a beer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable(name = "id") UUID beerId) {
        beerService.deleteBeer(beerId);
        return ResponseEntity.noContent().build();
    }
}
