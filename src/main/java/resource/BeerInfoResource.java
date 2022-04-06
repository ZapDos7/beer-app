package resource;

import domain.BeerInfo;
import lombok.Data;


@Data
public class BeerInfoResource {
    private String name;
    private String countryOfOrigin;
    private String description;
    private Integer rating;

    public BeerInfoResource(BeerInfo beerInfo) {
        this.name = beerInfo.getName();
        this.countryOfOrigin = beerInfo.getCountryOfOrigin();
        this.description = beerInfo.getDescription();
        this.rating = beerInfo.getRating();
    }
}
