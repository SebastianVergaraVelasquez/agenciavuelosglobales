package com.fabiansebastianj1.airport.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.Airport;

public interface AirportRepository {
    void save(Airport airport);

    void update(Airport airport);

    void delete(String id);

    Optional<Airport> findById(String id);

    List<Airport> findAll();
}
