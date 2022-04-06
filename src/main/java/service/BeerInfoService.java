package service;

import domain.BeerInfo;
import exception.BeerException;
import org.springframework.beans.factory.annotation.Autowired;
import repository.BeerInfoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BeerInfoService {
    @Autowired
    BeerInfoRepository beerInfoRepository;

    public List<BeerInfo> getByCriteria(String country, Integer rating) {
        if (country.isEmpty() && rating == null) {
            return beerInfoRepository.findAll();
        }
        else if (country.isEmpty()) {
            return beerInfoRepository.findByRatingGreaterThan(rating);
        }
        else if (rating == null) {
            return beerInfoRepository.findByCountryOfOrigin(country);
        }
        else {
            return beerInfoRepository.findByCountryOfOriginAndRatingGreaterThan(country,rating);
        }
    }

    public Optional<BeerInfo> getById(UUID id) {
        return beerInfoRepository.findById(id);
    }

    public boolean deleteById(UUID id) {
        beerInfoRepository.deleteById(id);
        return beerInfoRepository.findById(id).isEmpty();
    }

    public BeerInfo addBeer(String name, String country, String description) {
        return beerInfoRepository.save(new BeerInfo(UUID.randomUUID(), name, country, description, null));
    }

    public BeerInfo editBeerRating(UUID id, Integer newRating) throws BeerException {
        return beerInfoRepository.findById(id).map(beerInfo -> {
            beerInfo.setRating(newRating);
            beerInfoRepository.save(beerInfo);
            return  beerInfo;
        }).orElseThrow(() -> new BeerException(BeerException.BEER_NOT_FOUND));
    }

}
