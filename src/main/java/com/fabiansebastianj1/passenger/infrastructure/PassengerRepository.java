package com.fabiansebastianj1.passenger.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.passenger.domain.models.Passenger;

public interface PassengerRepository {
    List<String> getOccupiedSeats(int tripId);
    int getTotalOccupiedSeats(int tripId);
    void save(Passenger passenger);
    void update(Passenger passenger);
    Optional<Passenger> findById(String id);
    void delete(String id);
}
