package com.beerapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.beerapp.request.AddBeerRequest;

public class AddBeerRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AddBeerRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddBeerRequest request = (AddBeerRequest) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "add.beer.must.have.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "add.beer.must.have.country");

        if (errors.hasErrors()) return;
    }
}
