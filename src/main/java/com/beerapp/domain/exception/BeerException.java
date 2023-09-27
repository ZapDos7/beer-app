package com.beerapp.domain.exception;

public class BeerException extends Exception {

    public static final String BEER_NOT_FOUND = "beer.not.found";
    public static final String USER_NOT_FOUND = "user.not.found";
    public static final String RATING_NOT_FOUND = "rating.not.found";

    public BeerException(String errorMessage) {
        super(errorMessage);
    }
}
