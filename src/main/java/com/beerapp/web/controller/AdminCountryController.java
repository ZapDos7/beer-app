package com.beerapp.web.controller;

import com.beerapp.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Tag(name = "Countries", description = "Manage countries as admin")
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/countries")
public class AdminCountryController {
    private final CountryService countryService;

    public AdminCountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get countries")
    @GetMapping
    public ResponseEntity<Set<String>> getCountryNames() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @Operation(summary = "Add a country")
    @PostMapping
    public ResponseEntity<String> addCountry(@RequestParam String name) {
        countryService.addCountry(name);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a country")
    @DeleteMapping
    public ResponseEntity<String> deleteCountry(@RequestParam String name) {
        countryService.removeCountry(name);
        return ResponseEntity.noContent().build();
    }
}
