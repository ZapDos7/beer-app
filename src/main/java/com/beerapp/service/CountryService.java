package com.beerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CountryService {
    /** Returns a list of all countries as Strings
     * Source: <a href="https://gist.github.com/rogargon/5534902">gist</a> */

    private final SetOperations<String, String> setOps;
    private static final String KEY = "countries";


    @Autowired
    public CountryService(StringRedisTemplate redisTemplate) {
        this.setOps = redisTemplate.opsForSet();
    }

    public void addCountry(String country) {
        setOps.add(KEY, country);
    }

    public void removeCountry(String country) {
        setOps.remove(KEY, country);
    }

    public Set<String> getAllCountries() {
        return setOps.members(KEY);
    }

    public boolean isSupportedCountryName(String country) {
        return Boolean.TRUE.equals(setOps.isMember(KEY, country));
    }
}
