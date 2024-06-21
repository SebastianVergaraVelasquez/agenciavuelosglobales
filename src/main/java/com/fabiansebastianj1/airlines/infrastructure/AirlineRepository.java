package com.fabiansebastianj1.airlines.infrastructure;

import com.fabiansebastianj1.airlines.domain.models.Airport;
import java.util.Optional;
import java.util.List;

public interface AirlineRepository {
    void save(Airport airline);

    void update(Airport airline);

    void delete(int id);

    Optional<Airport> findById(int id);

    List<Airport> findAll();
}
