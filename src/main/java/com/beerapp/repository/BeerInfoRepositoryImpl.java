package com.beerapp.repository;

import com.beerapp.domain.BeerInfo;
import com.beerapp.domain.enums.BeerType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class BeerInfoRepositoryImpl implements CustomizedBeerInfoRepository{

    private final MongoTemplate mongoTemplate;

    public BeerInfoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<BeerInfo> findByCriteria(String country, Integer rating, BeerType beerType) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        if (country!=null) {
            criteriaList.add(Criteria.where("countryOfOrigin").is(country));
        }
        if (rating!=null) {
            criteriaList.add(Criteria.where("rating").is(rating));
        }
        if (beerType!=null) {
            criteriaList.add(Criteria.where("beerType").is(beerType));
        }
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }
        return mongoTemplate.find(query, BeerInfo.class);
    }
}
