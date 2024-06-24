package com.fabiansebastianj1.tripbooking.infraestructure;

import com.fabiansebastianj1.tripbooking.domain.models.TripBooking;

import java.util.List;
import java.util.Optional;

public interface TripBookingRepository {
    void save(TripBooking tripBooking);
    void update(TripBooking tripBooking);
    void delete(int id);
    Optional<TripBooking> findById(int id);
    List<TripBooking> findAll();
    Optional<TripBooking> findLast();
    void deleteTripBookingDetailForId(int id);
}
