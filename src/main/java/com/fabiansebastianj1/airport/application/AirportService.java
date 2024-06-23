package com.fabiansebastianj1.airport.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.airport.infrastructure.AirportRepository;

public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
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
}
