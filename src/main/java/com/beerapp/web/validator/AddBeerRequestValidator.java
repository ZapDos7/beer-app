package com.beerapp.web.validator;


import com.beerapp.domain.enums.BeerType;
import com.beerapp.service.CountryService;
import com.beerapp.web.request.AddBeerRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.stream.Stream;

public class AddBeerRequestValidator implements Validator {

    private final CountryService countryService;

    public AddBeerRequestValidator(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AddBeerRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddBeerRequest request = (AddBeerRequest) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "add.beer.must.have.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryOfOrigin", "add.beer.must.have.country");
        if (!countryService.countryNames().contains(request.getCountryOfOrigin())) {
            errors.rejectValue("countryOfOrigin", "invalid country name");
        }
        // description, moreInfo can be null
        if (!Stream.of(BeerType.values()).toList().contains(request.getType())) {
            errors.rejectValue("type", "invalid beer type");
        }
        if (errors.hasErrors()) return;
    }
}
