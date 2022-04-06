package com.beerapp.exception;

public class BeerException extends Exception {

    public static final String BEER_NOT_FOUND = "beer.not.found";

    public BeerException(String errorMessage) {
        super(errorMessage);
    }
}
