package com.beerapp.web.request;

import com.beerapp.domain.enums.BeerType;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public class EditBeerRequest {
    private String name;
    private String countryOfOrigin;
    private String description;
    private String type;
    private String moreInfo;

    public EditBeerRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
