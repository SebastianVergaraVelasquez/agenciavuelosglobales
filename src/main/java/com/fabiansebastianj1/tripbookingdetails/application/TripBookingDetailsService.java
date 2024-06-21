package com.fabiansebastianj1.tripbookingdetails.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetails;
import com.fabiansebastianj1.tripbookingdetails.infrastructure.TripBookingDetailsRepository;

public class TripBookingDetailsService {
     private final TripBookingDetailsRepository tripBookingDetailsRepository;

    public TripBookingDetailsService (TripBookingDetailsRepository tripBookingDetailsRepository){
        this.tripBookingDetailsRepository = tripBookingDetailsRepository;
    }

    public void createTripBookingDetails(TripBookingDetails tripBookingDetails){
        tripBookingDetailsRepository.save(tripBookingDetails);
    }

    public Optional<TripBookingDetails> findTripBookingDetailsById (int id){
        return tripBookingDetailsRepository.findById(id);
    }

    public List<TripBookingDetails> findAllTripBookingDetails(){
        return tripBookingDetailsRepository.findAll();
    }

    public void deletTripBookingDetails(int id){
        tripBookingDetailsRepository.delete(id);
    }

    public void updatTripBookingDetails(TripBookingDetails tripBookingDetails){
        tripBookingDetailsRepository.update(tripBookingDetails);
    }
}
