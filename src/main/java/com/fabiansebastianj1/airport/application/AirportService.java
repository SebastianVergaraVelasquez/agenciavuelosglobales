package com.fabiansebastianj1.airport.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.City;
import com.fabiansebastianj1.airport.infrastructure.AirportRepository;

public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public void createAirport(City airport) {
        airportRepository.save(airport);
    }

    public Optional<City> findAirportById(String id) {
        return airportRepository.findById(id);
    }

    public List<City> findAllAirports() {
        return airportRepository.findAll();
    }

    public void deleteAirport(String id) {
        airportRepository.delete(id);
    }

    public void updateAirport(City airport) {
        airportRepository.update(airport);
    }
}
