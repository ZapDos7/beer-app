package com.beerapp.domain.repository;

import com.beerapp.domain.BeerInfo;
import com.beerapp.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Rating.RatingId> {
    @Query(value = "DELETE FROM rating r WHERE r.beer_id = :beerId", nativeQuery = true)
    void deleteByBeerId(UUID beerId);

    @Query(value = "DELETE FROM rating r WHERE r.user_id = :userId", nativeQuery = true)
    void deleteByUserId(UUID userId);
}
