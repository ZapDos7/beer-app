package com.beerapp.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beerapp.domain.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Rating.RatingId> {
    List<Rating> findByUserId(Long userId);

    Optional<Rating> findByUserIdAndBeerId(Long userId, Long beerId);

    void deleteByUserIdAndBeerId(Long userId, Long beerId);
}
