package com.fabiansebastianj1.connection.application;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.airport.infrastructure.AirportRepository;
import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.connection.infraestructure.ConnectionRepository;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.infrastructure.PlaneRepository;

import java.util.List;
import java.util.Optional;

public class ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;

    public ConnectionService(ConnectionRepository connectionRepository, AirportRepository airportRepository,
            PlaneRepository planeRepository) {
        this.connectionRepository = connectionRepository;
        this.airportRepository = airportRepository;
        this.planeRepository = planeRepository;
    }

    public void createConnection(Connections connections){
        connectionRepository.save(connections);
    }

    public void updateConnection(Connections connections){
        connectionRepository.update(connections);
    }

    public void deleteCOnnection(int id){
        connectionRepository.delete(id);
    }

    public Optional<Connections> getConnectionById(int id){
        return connectionRepository.findById(id);
    }

    public List<Connections> getAllConnection(){
        return connectionRepository.findAll();
    }

    public List<ConnectionDTO> getAllFlights(){
        return connectionRepository.listFlights();
    }

    public List<ConnectionDTO> findConnectionsByTripId(int tripId){
        return connectionRepository.findAllByTripId(tripId);
    }

    public Optional<ConnectionDTO> findTripByTripId(int tripId){
        return connectionRepository.findConnectionDTO(tripId);
    }

    public Optional<ConnectionDTO> findConnectionInfoById(int id){
        return connectionRepository.showConnectionInfo(id);
    }

    public List<Airport> findAllAirports(){
        return airportRepository.findAll();
    }

    public Optional<Plane> findPlaneByPlates(String plates){
        return planeRepository.findByPlate(plates);
    }
}
