package com.beerapp.domain.repository;

import com.beerapp.domain.Beer;
import com.beerapp.domain.enums.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
    @Query(value = "SELECT b.* FROM beer b "
            + "WHERE (:country is null or b.country_of_origin = :country) "
            + "AND (:type is null or b.type = :type)", nativeQuery = true)
    public List<Beer> findByCriteria(@Param("country") String country,
                                     @Param("type") BeerType type);
}
