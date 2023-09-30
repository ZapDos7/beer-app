package com.beerapp.domain.repository;

import com.beerapp.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository  extends JpaRepository<Rating, Rating.RatingId> {
    List<Rating> findByUserId(UUID userId);

    Optional<Rating> findByUserIdAndBeerId(UUID userId, UUID beerId);

    void deleteByUserIdAndBeerId(UUID userId, UUID beerId);
}

