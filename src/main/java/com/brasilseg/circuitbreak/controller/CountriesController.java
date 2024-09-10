package com.brasilseg.circuitbreak.controller;

import com.brasilseg.circuitbreak.service.CountriesService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountriesController {


    private final CountriesService countriesService;

    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    @GetMapping("/countries")
    @CircuitBreaker(name = "countriesCircuitBreaker", fallbackMethod = "fallbackGetCountries")
    @RateLimiter(name = "countriesRateLimiter")
    @Retry(name = "countriesRetry", fallbackMethod = "fallbackGetCountries")
    public List<Object> getCountries() throws Exception {
        return countriesService.getCountries();
    }

    public List<Object> fallbackGetCountries(Throwable throwable) {
        List<Object> countries = new ArrayList<>();
        countries.add("Country service unavailable!");
        return countries;
    }
}
