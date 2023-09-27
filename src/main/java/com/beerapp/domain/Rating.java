package com.beerapp.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {

  @Embeddable
  public class RatingId implements Serializable {
    @Column(name = "beer_id")
    private UUID beerId;
    @Column(name = "user_id")
    private UUID userId;

    public RatingId(UUID beerId, UUID userId) {
      this.beerId = beerId;
      this.userId = userId;
    }

    public RatingId() {
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


  //composite key
  @EmbeddedId
  private RatingId ratingId;

  @Column(name = "rating")
  private int rating;

  @Column(name = "rating_date")
  private Instant ratingDate;

  public Rating(UUID userId, UUID beerId, int rating) {
    this.ratingId = new RatingId(userId, beerId);
    this.rating = rating;
    this.ratingDate = Instant.now();
  }

  public Rating() {
  }

  public RatingId getRatingId() {
    return ratingId;
  }

  public void setRatingId(RatingId ratingId) {
    this.ratingId = ratingId;
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
