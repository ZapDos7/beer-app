package com.beerapp.web.request;

public class EditRatingRequest {
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
