package com.fabiansebastianj1.planes.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airlines.domain.models.Airline;
import com.fabiansebastianj1.airlines.infrastructure.AirlineRepository;
import com.fabiansebastianj1.model.domain.models.Model;
import com.fabiansebastianj1.model.infrastructure.ModelRepository;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.infrastructure.PlaneRepository;
import com.fabiansebastianj1.status.domain.models.Status;
import com.fabiansebastianj1.status.infrastructure.StatusRepository;

public class PlaneService {

    private final PlaneRepository planeRepository;
    private final StatusRepository statusRepository;
    private final AirlineRepository airlineRepository;
    private final ModelRepository modelRepository;

    public PlaneService (PlaneRepository planeRepository, StatusRepository statusRepository,AirlineRepository airlineRepository,ModelRepository modelRepository){
        this.planeRepository = planeRepository;
        this.statusRepository = statusRepository;
        this.airlineRepository = airlineRepository;
        this.modelRepository = modelRepository;
    }

    public void createPlane(Plane plane){
        planeRepository.save(plane);
    }

    public Optional<Plane> findPlaneById (int id){
        return planeRepository.findById(id);
    }

    public Optional<Plane> findPlaneByPlate (String plate){
        return planeRepository.findByPlate(plate);
    }

    public List<Plane> findAllPlanes(){
        return planeRepository.findAll();
    }

    public void deletePlane(int id){
        planeRepository.delete(id);
    }

    public void updatePlane(Plane plane){
        planeRepository.update(plane);
    }

    public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }

    public Optional<Status> findStatusById (int id){
        return statusRepository.findById(id);
    }

    public List<Airline> findAllAirlines(){
        return airlineRepository.findAll();
    }

    public Optional<Airline> findAirlineById (int id){
        return airlineRepository.findById(id);
    }

    public List<Model> findAllModels(){
        return modelRepository.findAll();
    }

    public Optional<Model> findModelById (int id){
        return modelRepository.findById(id);
    }
}
