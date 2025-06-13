package com.beerapp.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "rating")
@IdClass(Rating.RatingId.class)
public class Rating {

    public static class RatingId implements Serializable {
        private String beerId;
        private String userId;

        public RatingId() {
        }

        public RatingId(String beerId, String userId) {
            this.beerId = beerId;
            this.userId = userId;
        }

        public String getBeerId() {
            return beerId;
        }

        public void setBeerId(String beerId) {
            this.beerId = beerId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RatingId ratingId = (RatingId) o;
            return Objects.equals(beerId, ratingId.beerId) && Objects.equals(userId, ratingId.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(beerId, userId);
        }
    }
    @Id
    @Column(name = "beer_id")
    private UUID beerId;
    @Id
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "rating")
    private int rating;
    @Column(name = "rating_date")
    @CreatedDate
    private Instant ratingDate;

    public Rating() {
    }

    public UUID getBeerId() {
        return beerId;
    }

    public void setBeerId(UUID beerId) {
        this.beerId = beerId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Instant getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Instant ratingDate) {
        this.ratingDate = ratingDate;
    }
}