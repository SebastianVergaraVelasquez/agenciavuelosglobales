package com.fabiansebastianj1.tripbooking.application;

import com.fabiansebastianj1.tripbooking.domain.models.TripBooking;
import com.fabiansebastianj1.tripbooking.infraestructure.TripBookingRepository;

import java.util.List;
import java.util.Optional;

public class TripBookingService {

    private final TripBookingRepository tripBookingRepository;

    public TripBookingService(TripBookingRepository tripBookingRepository) {
        this.tripBookingRepository = tripBookingRepository;
    }

    public void createTripBooking(TripBooking tripBooking) {
        tripBookingRepository.save(tripBooking);
    }

    public void deleteTripBooking(int id) {
        tripBookingRepository.delete(id);
    }

    public void updateTripBooking(TripBooking tripBooking) {
        tripBookingRepository.update(tripBooking);
    }

    public Optional<TripBooking> findTripBookingById(int id) {
        return tripBookingRepository.findById(id);
    }

    public List<TripBooking> findAllTripBookings() {
        return tripBookingRepository.findAll();
    }
}
