package com.fabiansebastianj1.connection.infraestructure;

import com.fabiansebastianj1.connection.domain.models.*;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository {
    void save(Connections connections);
    void update(Connections connections);
    void delete(int id);
    Optional<Connections> findById(int id);
    List<Connections> findAll();
    List<ConnectionDTO> listFlights();
    Optional<ConnectionDTO> findConnectionDTO(int id);
    List<ConnectionDTO> findAllByTripId(int tripId);
    Optional<ConnectionDTO> showConnectionInfo(int id);
    List<Connections>findAllConnectionsByTripId(int tripId);
    List<ConnectionDTO> listFlightsByAirportsId(String AirportId1, String AirportId2, String fecha);
    List<ConnectionDTO> listConnectionsAvailable(String idAirportOrigin, String idAirportDestination);
}
