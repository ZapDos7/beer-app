package com.beerapp.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "rating")
@IdClass(Rating.RatingId.class)
public class Rating {

    public static class RatingId implements Serializable {
        private Long beerId;
        private Long userId;

        public RatingId() {
        }

        public RatingId(Long beerId, Long userId) {
            this.beerId = beerId;
            this.userId = userId;
        }

        public Long getBeerId() {
            return beerId;
        }

        public void setBeerId(Long beerId) {
            this.beerId = beerId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
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
    private Long beerId;
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "rating")
    private int rating;
    @Column(name = "rating_date")
    @CreatedDate
    private LocalDateTime ratingDate;

    public Rating() {
    }

    public Rating(Long userId, Long beerId, int rating) {
        this.beerId = beerId;
        this.userId = userId;
        this.rating = rating;
        this.ratingDate = LocalDateTime.now();
    }

    public Long getBeerId() {
        return beerId;
    }

    public void setBeerId(Long beerId) {
        this.beerId = beerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(LocalDateTime ratingDate) {
        this.ratingDate = ratingDate;
    }
}