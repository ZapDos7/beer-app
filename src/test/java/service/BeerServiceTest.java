package service;

import com.beerapp.domain.Beer;
import com.beerapp.domain.enums.BeerType;
import com.beerapp.domain.repository.BeerRepository;
import com.beerapp.service.BeerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)

public class BeerServiceTest {
    @InjectMocks // inject Mocked repo into Service
    BeerService beerService;

    @Mock
    BeerRepository beerRepository;

    @Test
    void getAllBeers(){
        Mockito.doReturn(getMockCustomers(2)).when(beerRepository).findAll();
        List<Beer> beers = this.beerService.getBeers(null, null); // implemented in service layer
        assertEquals(2, beers.size()); // we added 2 customers in repository so the fetching method should return 2
    }
    private Iterable<Beer> getMockCustomers(int size){
        List<Beer> beers = new ArrayList<>(size);
        for(int i=0;i<size;i++){ // add dummy data
            beers.add(new Beer());
        }
        return beers;
    }
}
