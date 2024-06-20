package com.fabiansebastianj1.country.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.country.domain.models.Country;
import com.fabiansebastianj1.country.infraestructure.CountryRepository;

public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void createCountry(Country country){
        countryRepository.save(country);
    }

    public void updateCountry(Country country){
        countryRepository.update(country);
    }

    public Optional<Country> getCountryById(String id){
        return countryRepository.findById(id);
    }

    public void deleteCountry(String id){
        countryRepository.delete(id);
    }

    public List<Country> getAllCountry(){
        return countryRepository.findAll();
    }
}
