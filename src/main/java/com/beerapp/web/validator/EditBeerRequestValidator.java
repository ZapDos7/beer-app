package com.beerapp.web.validator;

import com.beerapp.domain.enums.BeerType;
import com.beerapp.service.CountryService;
import com.beerapp.web.request.EditBeerRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.stream.Stream;

public class EditBeerRequestValidator implements Validator {

    private final CountryService countryService;

    public EditBeerRequestValidator(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return EditBeerRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EditBeerRequest request = (EditBeerRequest) target;
        if (request.getName().isPresent() && request.getName().isEmpty()) {
            errors.rejectValue("name", "name cannot be empty");
        }
        if (request.getCountryOfOrigin().isPresent() && request.getCountryOfOrigin().isEmpty()) {
            errors.rejectValue("countryOfOrigin", "countryOfOrigin cannot be empty");
        }
        if (!countryService.countryNames().contains(request.getCountryOfOrigin().get())) {
            errors.rejectValue("countryOfOrigin", "invalid country name");
        }
        // description, moreInfo can be null
        if (request.getType().isPresent() && !Stream.of(BeerType.values()).toList().contains(request.getType().get())) {
            errors.rejectValue("type", "invalid beer type");
        }
        if (errors.hasErrors()) return;
    }
}
