package com.fabiansebastianj1.city.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.city.domain.models.*;
import com.fabiansebastianj1.city.infrastructure.CityRepository;

public class CityService {
     private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void createCity (City city){
        cityRepository.save(city);
    }

    public Optional<City> findCitytById (String id){
       return cityRepository.findById(id);
    }

    public List<City> findAllCities(){
        return cityRepository.findAll();
    }

    public void deleteCity(String id){
        cityRepository.delete(id);
    }

    public void updateCity(City city){
        cityRepository.update(city);
    }
}
