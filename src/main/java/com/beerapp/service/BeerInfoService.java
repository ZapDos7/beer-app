package com.beerapp.service;

import com.beerapp.domain.BeerInfo;
import com.beerapp.domain.Rating;
import com.beerapp.domain.User;
import com.beerapp.domain.dto.BeerRatingDto;
import com.beerapp.domain.enums.BeerType;
import com.beerapp.domain.exception.BeerException;
import com.beerapp.domain.repository.BeerInfoRepository;
import com.beerapp.domain.repository.RatingRepository;
import com.beerapp.service.translator.BeerInfoTranslator;
import com.beerapp.web.request.AddBeerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BeerInfoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final BeerInfoRepository beerInfoRepository;
    private final RatingRepository ratingRepository;

    public BeerInfoService(BeerInfoRepository beerInfoRepository, RatingRepository ratingRepository) {
        this.beerInfoRepository = beerInfoRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<BeerRatingDto> getByCriteria(String country, Integer rating, BeerType beerType) {
        logger.debug("Fetching beer by criteria: ");
        var beers = beerInfoRepository.findByCriteria(country, rating, beerType);
        List<BeerRatingDto> result = new ArrayList<>();
        beers.forEach( b -> result.add(BeerInfoTranslator.toDto(b, null, null)));
        return result;
    }

    public BeerRatingDto getById(UUID id) throws BeerException {
        logger.debug("Fetching beer with id {}", id);
        var beerInfo = beerInfoRepository.findById(id).orElseThrow(() -> new BeerException(BeerException.BEER_NOT_FOUND));
        return BeerInfoTranslator.toDto(beerInfo, null, null);
    }

    public BeerRatingDto getById(UUID id, Integer rating, String username) throws BeerException {
        logger.debug("Fetching beer with id {}", id);
        var beerInfo = beerInfoRepository.findById(id).orElseThrow(() -> new BeerException(BeerException.BEER_NOT_FOUND));
        return BeerInfoTranslator.toDto(beerInfo, null, null);
    }

    public BeerRatingDto editBeerRating(UUID id, Integer newRating, User user) throws BeerException {
        logger.debug("Rating beer with id {}", id);
        Rating rating = ratingRepository.findById(new Rating().new RatingId(id, user.getId())).orElseThrow(() -> new BeerException(BeerException.RATING_NOT_FOUND));
        rating.setRating(newRating);
        ratingRepository.save(rating);
        return getById(id, rating.getRating(), user.getName());
    }

    /* Admin */
    public boolean deleteById(UUID id) {
        logger.debug("Deleting beer with id {}", id);
        beerInfoRepository.deleteById(id);
        ratingRepository.deleteByBeerId(id);
        return beerInfoRepository.findById(id).isEmpty();
    }

    public BeerRatingDto addBeer(AddBeerRequest body) {
        logger.debug("Adding beer with name: {}", body.getName());
        var beerInfo = beerInfoRepository.save(new BeerInfo(UUID.randomUUID(), body.getName(), body.getCountry(), body.getDescription(), body.getBeerType()));
        return BeerInfoTranslator.toDto(beerInfo, null, null);
    }
}
