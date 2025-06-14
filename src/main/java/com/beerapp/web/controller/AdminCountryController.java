package com.beerapp.web.controller;

import com.beerapp.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Countries", description = "Manage countries as admin")
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/countries")
public class AdminCountryController {
    private final CountryService countryService;

    public AdminCountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get countries")
    @GetMapping
    public ResponseEntity<List<String>> getCountryNames() {
        return ResponseEntity.ok(countryService.countryNames());
    }

    @Operation(summary = "Add a country")
    @PostMapping
    public ResponseEntity<String> addCountry(@RequestBody AddCountryRequest request) {
        return ResponseEntity.ok(countryService.addCountry(request));
    }

    @Operation(summary = "Delete a country")
    @DeleteMapping(path = "{name}")
    public ResponseEntity<String> deleteCountry(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(countryService.deleteCountry(name));
    }
}
