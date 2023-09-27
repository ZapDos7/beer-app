package com.beerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories//("com...jpa")
public class BeerApp implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(BeerApp.class, args);
    }
}
