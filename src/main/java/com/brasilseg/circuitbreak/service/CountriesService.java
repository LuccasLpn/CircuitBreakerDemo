package com.brasilseg.circuitbreak.service;

import com.brasilseg.circuitbreak.client.CountriesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesService {

    @Autowired
    private CountriesClient countriesClient;

    public List<Object> getCountries() throws Exception {
        List<Object> countries;
        try {
            countries = countriesClient.getAllCountries();
        } catch (Exception e) {
            throw new Exception("Failed to fetch countries from the API");
        }
        return countries.subList(1, 10);
    }
}
