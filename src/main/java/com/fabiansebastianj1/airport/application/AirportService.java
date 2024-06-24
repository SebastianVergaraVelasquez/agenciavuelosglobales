package com.fabiansebastianj1.airport.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.airport.infrastructure.AirportRepository;
import com.fabiansebastianj1.city.domain.models.City;
import com.fabiansebastianj1.city.infrastructure.CityRepository;

public class AirportService {
    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    public AirportService(AirportRepository airportRepository, CityRepository cityRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }

    public void createAirport(Airport airport) {
        airportRepository.save(airport);
    }

    public Optional<Airport> findAirportById(String id) {
        return airportRepository.findById(id);
    }

    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }

    public void deleteAirport(String id) {
        airportRepository.delete(id);
    }

    public void updateAirport(Airport airport) {
        airportRepository.update(airport);
    }

    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> findCityById(String id) {
        return cityRepository.findById(id);
    }

}
