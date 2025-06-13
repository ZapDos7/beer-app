package com.beerapp.web.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class EditRatingRequest {
    @Max(value=5)
    @Min(value=1)
    private int newRating;

    public EditRatingRequest() {
    }

    public int getNewRating() {
        return newRating;
    }

    public void setNewRating(int newRating) {
        this.newRating = newRating;
    }
}
