package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import request.EditBeerRatingRequest;

public class EditBeerRatingRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EditBeerRatingRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EditBeerRatingRequest request = (EditBeerRatingRequest) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newRating", "rating.must.exist");

        if (errors.hasErrors()) return;

        if (request.getNewRating() > 5 || request.getNewRating() < 0) {
            errors.rejectValue("newRating", "rating.must.be.between.0.and.5");
        }
    }
}
