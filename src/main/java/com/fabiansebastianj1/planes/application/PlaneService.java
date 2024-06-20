package com.fabiansebastianj1.planes.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.infrastructure.PlaneRepository;

public class PlaneService {

    private final PlaneRepository planeRepository;

    public PlaneService (PlaneRepository planeRepository){
        this.planeRepository = planeRepository;
    }

    public void createManufacturer(Plane plane){
        planeRepository.save(plane);
    }

    public Optional<Plane> findManufacturerById (int id){
        return planeRepository.findById(id);
    }

    public List<Plane> findAllManufacturers(){
        return planeRepository.findAll();
    }

    public void deleteManufacturer(int id){
        planeRepository.delete(id);
    }

    public void updateManufacturer(Plane plane){
        planeRepository.update(plane);
    }
}
