package com.beerapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/** <a href="https://gist.github.com/rogargon/5534902">Values source</a> */
@Service
public class CountryService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SetOperations<String, String> setOps;
    private static final String KEY = "countries";


    @Autowired
    public CountryService(StringRedisTemplate redisTemplate) {
        this.setOps = redisTemplate.opsForSet();
    }

    public void addCountry(String country) {
        logger.trace("Adding country: {}", country);
        setOps.add(KEY, country);
    }

    public void removeCountry(String country) {
        logger.trace("Removing country: {}", country);
        setOps.remove(KEY, country);
    }

    public Set<String> getAllCountries() {
        logger.trace("Getting all countries");
        return setOps.members(KEY);
    }

    public boolean isSupportedCountryName(String country) {
        logger.trace("Checking country: {}", country);
        return Boolean.TRUE.equals(setOps.isMember(KEY, country));
    }

    @PostConstruct
    public void importFromJson() {
        if (setOps.size(KEY) == 0) {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = null;
            try {
                is = new ClassPathResource("countries.json").getInputStream();
            } catch (IOException e) {
                logger.error("Could not read countries file");
                return;
            }

            List<String> countries = null;
            try {
                countries = mapper.readValue(is, new TypeReference<>() {});
            } catch (IOException e) {
                logger.error("Could not input countries from file");
                return;
            }
            setOps.add(KEY, countries.toArray(new String[0]));

            System.out.println("Loaded countries into Redis.");
        } else {
            System.out.println("Redis already has countries, skipping init.");
        }
    }
}
