package com.beerapp.web.controller;

import com.beerapp.domain.Beer;
import com.beerapp.exceptions.BadRequestException;
import com.beerapp.exceptions.NotFoundException;
import com.beerapp.service.BeerService;
import com.beerapp.web.request.AddBeerRequest;
import com.beerapp.web.request.EditBeerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admin Beers", description = "Manage beers as admin")
@RestController
@PreAuthorize("hasRole('ADMIN')")
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
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Operation(summary = "Admin: Edit a beer")
    @PatchMapping("/{id}")
    public ResponseEntity<Beer> editBeer(@PathVariable(name = "id") Long id, @RequestBody @Valid EditBeerRequest request) {
        try {
            return ResponseEntity.ok(beerService.editBeer(id, request));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Admin: Delete a beer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable(name = "id") Long beerId) {
        beerService.deleteBeer(beerId);
        return ResponseEntity.noContent().build();
    }
}
