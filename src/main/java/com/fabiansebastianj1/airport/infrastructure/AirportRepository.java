package com.fabiansebastianj1.airport.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.airport.domain.models.AirportDTO;

public interface AirportRepository {
    void save(Airport airport);

    void update(Airport airport);

    void delete(String id);

    Optional<Airport> findById(String id);

    List<Airport> findAll();

    Optional<AirportDTO> findAirportCityById(String id);

    List<Airport> findAllAirportsByCityId(String id);
    
}
