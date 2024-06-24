package com.fabiansebastianj1.connection.application;

import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.connection.infraestructure.ConnectionRepository;

import java.util.List;
import java.util.Optional;

public class ConnectionService {
    private final ConnectionRepository connectionRepository;

    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
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
}
