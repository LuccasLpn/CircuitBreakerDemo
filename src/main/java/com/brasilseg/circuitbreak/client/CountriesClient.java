package com.brasilseg.circuitbreak.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "countriesClient", url = "https://invalid-url.com/v3.1")
public interface CountriesClient {

    @GetMapping("/all")
    List<Object> getAllCountries();
}

