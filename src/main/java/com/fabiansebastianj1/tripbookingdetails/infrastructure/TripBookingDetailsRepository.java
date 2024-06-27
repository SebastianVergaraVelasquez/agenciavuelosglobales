package com.fabiansebastianj1.tripbookingdetails.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetails;
import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetailsDTO;

public interface TripBookingDetailsRepository {
    void save(TripBookingDetails tripBookingDetails);
    void update(TripBookingDetails tripBookingDetails);
    void delete(int id);
    Optional<TripBookingDetails> findById(int id);
    List<TripBookingDetailsDTO> findTripBookingByCustomerId(String id);
    List<TripBookingDetailsDTO> findTripBookingByTripId(int id);
    List<TripBookingDetails> findAll();
    Optional<TripBookingDetailsDTO> findByTripBookingIdAsDTO(int id);
    Optional<TripBookingDetails> findByTripBookingId(int id);
    Optional<TripBookingDetails> findLastBDetail();
}
