package com.beerapp.service;

import com.beerapp.domain.Beer;
import com.beerapp.domain.repository.BeerRepository;
import com.beerapp.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class BeerServiceTest {
    @Autowired
    private BeerService beerService;

    @MockBean
    private BeerRepository beerRepository;

    @Test
    void getOne_returnsBeer() {
        long id = 1L;
        Beer beer = new Beer();
        beer.setId(id);
        Mockito.when(beerRepository.findById(id)).thenReturn(Optional.of(beer));

        Beer found = null;
        try {
            found = beerService.getOne(id);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(id, found.getId());
    }
}
