package com.beerapp.web.request;

import com.beerapp.domain.enums.BeerType;

import java.util.Optional;

public class EditBeerRequest {
    private Optional<String> name;
    private Optional<String> countryOfOrigin;
    private Optional<String> description;
    private Optional<BeerType> type;
    private Optional<String> moreInfo;

    public EditBeerRequest() {
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(Optional<String> countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    public Optional<BeerType> getType() {
        return type;
    }

    public void setType(Optional<BeerType> type) {
        this.type = type;
    }

    public Optional<String> getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(Optional<String> moreInfo) {
        this.moreInfo = moreInfo;
    }
}
