package repository;

import domain.BeerInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BeerInfoRepository extends MongoRepository<BeerInfo, UUID> {
    @Query("{'rating':{$gte:?0}}")
    List<BeerInfo> findByRatingGreaterThan(Integer rating);

    @Query("{'country':?0}")
    List<BeerInfo> findByCountryOfOrigin(String country);

    @Query("{'country':?0, 'rating':{$gte:?1}}")
    List<BeerInfo> findByCountryOfOriginAndRatingGreaterThan(String country, Integer rating);
}
