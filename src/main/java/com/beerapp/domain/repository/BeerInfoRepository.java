package com.beerapp.domain.repository;

import com.beerapp.domain.BeerInfo;
import com.beerapp.domain.enums.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BeerInfoRepository extends JpaRepository<BeerInfo, UUID> {

  @Query(value = "SELECT b.* FROM beer b "
      + "WHERE (:country is null or b.country = :country) "
      + "AND (:rating is null or b.rating = :rating) "
      + "AND (:type is null or b.type = :type)", nativeQuery = true)
  public List<BeerInfo> findByCriteria(@Param("country") String country,
      @Param("rating") Integer rating, @Param("type") BeerType type);
}
