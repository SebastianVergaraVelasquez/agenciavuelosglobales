package com.fabiansebastianj1.trip.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.airport.infrastructure.AirportRepository;
import com.fabiansebastianj1.city.domain.models.City;
import com.fabiansebastianj1.city.infrastructure.CityRepository;
import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.connection.infraestructure.ConnectionRepository;
import com.fabiansebastianj1.country.domain.models.Country;
import com.fabiansebastianj1.country.infraestructure.CountryRepository;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.domain.models.PlaneDTO;
import com.fabiansebastianj1.planes.infrastructure.PlaneRepository;
import com.fabiansebastianj1.trip.domain.models.Trip;
import com.fabiansebastianj1.trip.infrastructure.TripRepository;

public class TripService {

    private final TripRepository tripRepository;
    private final ConnectionRepository connectionRepository;
    private final PlaneRepository planeRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final AirportRepository airportRepository;

    public TripService(TripRepository tripRepository, ConnectionRepository connectionRepository,
            PlaneRepository planeRepository, CityRepository cityRepository, CountryRepository countryRepository,
            AirportRepository airportRepository) {
        this.tripRepository = tripRepository;
        this.connectionRepository = connectionRepository;
        this.planeRepository = planeRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.airportRepository = airportRepository;
    }

    public void createTrip(Trip trip){
        tripRepository.save(trip);
    }

    public Optional<Trip> findTripById (int id){
        return tripRepository.findById(id);
    }

    public List<Trip> findAllTrips(){
        return tripRepository.findAll();
    }

    public void deleteTrip(int id){
        tripRepository.delete(id);
    }

    public void updateTrip(Trip trip){
        tripRepository.update(trip);
    }

    public List<ConnectionDTO> findAllTripsAsConnectionDTO(){
        return connectionRepository.listFlights();
    }

    public Optional<ConnectionDTO> findTripAsConnectionByTripId(int tripId){
        return connectionRepository.findConnectionDTO(tripId);
    }

    public Optional<Plane> findPlaneById(int id){
        return planeRepository.findById(id);
    }

    public List<Plane> findAllAvailable(){
        return planeRepository.findAllAvailable();
    }
    
    public List<PlaneDTO> findListPlaneInfo(){
        return planeRepository.findListPlaneInfo();
    }

    public Optional<Connections> findConnectionById(int id){
        return connectionRepository.findById(id);
    } 

    public void updateConnection(Connections connection){
        connectionRepository.update(connection);
    }

    public void deleteConnection(int id){
        connectionRepository.delete(id);
    }

    public List<Connections> findAllConnectionsByTripId(int tripId){
        return connectionRepository.findAllConnectionsByTripId(tripId);
    }

    public List<Country> findAllCountries(){   //Listar paises
        return countryRepository.findAll();
    }

    public Optional<Country> findCountry(String id){   //Verificar que exista
        return countryRepository.findById(id);
    }

    public List<City> findCitiesByCountryId(String countryId){  //Función traer ciudades by country id
        return cityRepository.findAllByCityId(countryId);
    }

    public Optional<City> findCity(String cityId){ //Verificar que exista
        return cityRepository.findById(cityId);
    }

    public List<Airport> findAirportsByCity(String cityId){
        return airportRepository.findAllAirportsByCityId(cityId);
    }

    public Optional<Airport> findAirport(String id){
        return airportRepository.findById(id);
    }

    public Optional<Trip> findLastTrip(){
        return tripRepository.findLastTrip();
    }

    public void createConnection(Connections connection){
        connectionRepository.save(connection);
    }

    public List<ConnectionDTO> listConnectionsAvailable(String idAirportOrigin, String idAirportDestination){
        return connectionRepository.listConnectionsAvailable(idAirportOrigin,idAirportDestination );
    }
}

