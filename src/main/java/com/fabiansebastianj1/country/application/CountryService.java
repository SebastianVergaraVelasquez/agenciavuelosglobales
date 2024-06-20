package com.fabiansebastianj1.country.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.country.domain.models.Countries;
import com.fabiansebastianj1.country.infraestructure.CountriesRepository;

public class CountriesService {
    private final CountriesRepository countriesRepository;

    public CountriesService(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    public void createCountries(Countries countries){
        countriesRepository.save(countries);
    }

    public void updateCountries(Countries countries){
        countriesRepository.update(countries);
    }

    public Optional<Countries> getCountriesById(String id){
        return countriesRepository.findById(id);
    }

    public void deleteCountries(String id){
        countriesRepository.delete(id);
    }

    public List<Countries> getAllCountries(){
        return countriesRepository.findAll();
    }
}
