package com.fabiansebastianj1.tripbookingdetails.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetails;

public interface TripBookingDetailsRepository {
    void save(TripBookingDetails tripBookingDetails);
    void update(TripBookingDetails tripBookingDetails);
    void delete(int id);
    Optional<TripBookingDetails> findById(int id);
    List<TripBookingDetails> findAll();
}
