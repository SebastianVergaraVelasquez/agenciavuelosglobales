package com.fabiansebastianj1.tripbooking.application;

import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.connection.infraestructure.ConnectionRepository;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.customer.infrastructure.CustomerRepository;
import com.fabiansebastianj1.fare.domain.models.Fare;
import com.fabiansebastianj1.fare.infrastructure.FareRepository;
import com.fabiansebastianj1.tripbooking.domain.models.TripBooking;
import com.fabiansebastianj1.tripbooking.infraestructure.TripBookingRepository;
import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetails;
import com.fabiansebastianj1.tripbookingdetails.infrastructure.TripBookingDetailsRepository;

import java.util.List;
import java.util.Optional;

public class TripBookingService {

    private final TripBookingRepository tripBookingRepository;
    private final CustomerRepository customerRepository;
    private final ConnectionRepository connectionRepository;
    private final FareRepository fareRepository;
    private final TripBookingDetailsRepository tripBookingDetailsRepository;

    public TripBookingService(TripBookingRepository tripBookingRepository, CustomerRepository customerRepository,
        ConnectionRepository connectionRepository, FareRepository fareRepository, TripBookingDetailsRepository tripBookingDetailsRepository) {
        this.tripBookingRepository = tripBookingRepository;
        this.customerRepository = customerRepository;
        this.connectionRepository = connectionRepository;
        this.fareRepository = fareRepository;
        this.tripBookingDetailsRepository = tripBookingDetailsRepository;
        
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

    public Optional<Customer> findCostumergById(String id) {
        return customerRepository.findById(id);
    }

    public Optional<Connections> findCostumergById(int id) {
        return connectionRepository.findById(id);
    }

    public List<ConnectionDTO> listFlights() {
        return connectionRepository.listFlights();
    }

    public Optional<Connections> findFlightById(int id) {
        return connectionRepository.findById(id);
    }

    public Optional<Fare> findFareById(int id) {
        return fareRepository.findById(id);
    }

    public List<Fare> listFares() {
        return fareRepository.findAll();
    }

    public void createTripBookingDetail(TripBookingDetails tripBookingDetails){
        tripBookingDetailsRepository.save(tripBookingDetails);
    }

    public Optional<TripBooking> findLastTripBooking(){
       return tripBookingRepository.findLast();
    }

    public void deleteTripBookingDetailForId(int id) {
        tripBookingDetailsRepository.delete(id);
    }

}
