package com.fabiansebastianj1.tripbooking.application;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.airport.infrastructure.AirportRepository;
import com.fabiansebastianj1.city.domain.models.City;
import com.fabiansebastianj1.city.infrastructure.CityRepository;
import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.connection.infraestructure.ConnectionRepository;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.customer.infrastructure.CustomerRepository;
import com.fabiansebastianj1.documenttype.domain.models.DocumentType;
import com.fabiansebastianj1.documenttype.infraestructure.DocumentTypeRepository;
import com.fabiansebastianj1.fare.domain.models.Fare;
import com.fabiansebastianj1.fare.infrastructure.FareRepository;
import com.fabiansebastianj1.passenger.infrastructure.PassengerRepository;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.infrastructure.PlaneRepository;
import com.fabiansebastianj1.trip.domain.models.Trip;
import com.fabiansebastianj1.trip.infrastructure.TripRepository;
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
    private final CityRepository cityRepository;
    private final AirportRepository airportRepository;
    private final TripRepository tripRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final PlaneRepository planeRepository;
    private final PassengerRepository passengerRepository;

    public TripBookingService(TripBookingRepository tripBookingRepository, CustomerRepository customerRepository,
            ConnectionRepository connectionRepository, FareRepository fareRepository,
            TripBookingDetailsRepository tripBookingDetailsRepository, CityRepository cityRepository,
            AirportRepository airportRepository, TripRepository tripRepository,
            DocumentTypeRepository documentTypeRepository, PlaneRepository planeRepository,
            PassengerRepository passengerRepository) {
        this.tripBookingRepository = tripBookingRepository;
        this.customerRepository = customerRepository;
        this.connectionRepository = connectionRepository;
        this.fareRepository = fareRepository;
        this.tripBookingDetailsRepository = tripBookingDetailsRepository;
        this.cityRepository = cityRepository;
        this.airportRepository = airportRepository;
        this.tripRepository = tripRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.planeRepository = planeRepository;
        this.passengerRepository = passengerRepository;
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

    public List<City> findAllCities(){
        return cityRepository.findAll();
    }

    public Optional<City> findCityById(String id){
        return cityRepository.findById(id);
    }

    public List<Airport> findAllAirportsByCityId(String id){
        return airportRepository.findAllAirportsByCityId(id);
    }

    public Optional <Airport> findAirportById(String id){
        return airportRepository.findById(id);
    }

    public List<ConnectionDTO> findAllFlightsByAirportsId(String airpId1, String airpId2, String fecha){
        return connectionRepository.listFlightsByAirportsId(airpId1, airpId2, fecha);
    }

    public Optional<Trip> findTripById(int id){
        return tripRepository.findById(id);
    }

    public List<DocumentType> findAllDocumentTypes(){
        return documentTypeRepository.findAll();
    }

    public Optional<DocumentType> getDocumentType(int id){
        return documentTypeRepository.findById(id);
    }

    //Esta trae la info del vuelo como DTO, pero con datos diferentes incluyendo el planeId
    public Optional<ConnectionDTO> findConnectionInfoById(int id){
        return connectionRepository.showConnectionInfo(id);
    }

    public Optional<Plane> findPlaneById(int id){
        return planeRepository.findById(id);
    }

    public List<String> getAllOccupiedSeats(int id){
        return passengerRepository.getOccupiedSeats(id);
    }

    public int getTotalOccupiedSeats(int id){
        return passengerRepository.getTotalOccupiedSeats(id);
    }
}
