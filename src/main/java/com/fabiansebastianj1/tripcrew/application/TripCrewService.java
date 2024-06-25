package com.fabiansebastianj1.tripcrew.application;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.connection.infraestructure.ConnectionRepository;
import com.fabiansebastianj1.employee.domain.models.Employee;
import com.fabiansebastianj1.employee.infrastructure.EmployeeRepository;
import com.fabiansebastianj1.trip.domain.models.Trip;
import com.fabiansebastianj1.trip.infrastructure.TripRepository;
import com.fabiansebastianj1.tripcrew.domain.models.TripCrew;
import com.fabiansebastianj1.tripcrew.domain.models.TripCrewDTO;
import com.fabiansebastianj1.tripcrew.infrastructure.TripCrewRepository;

public class TripCrewService {

    private final TripCrewRepository tripCrewRepository;
    private final ConnectionRepository connectionRepository;
    private final EmployeeRepository employeeRepository;
    private final TripRepository tripRepository;

    public TripCrewService(TripCrewRepository tripCrewRepository, ConnectionRepository connectionRepository,
            EmployeeRepository employeeRepository, TripRepository tripRepository) {
        this.tripCrewRepository = tripCrewRepository;
        this.connectionRepository = connectionRepository;
        this.employeeRepository = employeeRepository;
        this.tripRepository = tripRepository;
    }

    public void createTripCrew(TripCrew tripCrew){
        tripCrewRepository.save(tripCrew);
    }

    public List<ConnectionDTO> findFlightNoConnection(){
        return connectionRepository.listFlights();
    }

    public List<Employee> listEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Connections> findConnectionById(int id){
        return connectionRepository.findById(id);
    }

    public Optional<Employee> findEmployeeById(String id){
        return employeeRepository.findById(id);
    }

    public Optional<Trip> findTripById(int id){
        return tripRepository.findById(id);
    }

    public Optional<ConnectionDTO> findTripAsConnectionByTripId(int id){
        return connectionRepository.findConnectionDTO(id);
    }

    public List<TripCrewDTO> listTripCrewDTOByConnectionId(int id){
        return tripCrewRepository.listTripCrewDTO(id);
    }
}
