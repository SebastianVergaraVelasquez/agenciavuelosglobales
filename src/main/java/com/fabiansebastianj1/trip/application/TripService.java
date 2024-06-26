package com.fabiansebastianj1.trip.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.connection.infraestructure.ConnectionRepository;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.infrastructure.PlaneRepository;
import com.fabiansebastianj1.trip.domain.models.Trip;
import com.fabiansebastianj1.trip.infrastructure.TripRepository;

public class TripService {

    private final TripRepository tripRepository;
    private final ConnectionRepository connectionRepository;
    private final PlaneRepository planeRepository;

    public TripService(TripRepository tripRepository, ConnectionRepository connectionRepository,
            PlaneRepository planeRepository) {
        this.tripRepository = tripRepository;
        this.connectionRepository = connectionRepository;
        this.planeRepository = planeRepository;
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
}

