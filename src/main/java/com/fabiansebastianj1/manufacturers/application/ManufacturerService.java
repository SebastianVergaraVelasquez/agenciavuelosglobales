package com.fabiansebastianj1.manufacturers.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.manufacturers.domain.models.Model;
import com.fabiansebastianj1.manufacturers.infrastructure.ManufacturerRepository;

public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository = manufacturerRepository;
    }

    public void createManufacturer(Model manufacturer){
        manufacturerRepository.save(manufacturer);
    }

    public Optional<Model> findManufacturerById (int id){
        return manufacturerRepository.findById(id);
    }

    public List<Model> findAllManufacturers(){
        return manufacturerRepository.findAll();
    }

    public void deleteManufacturer(int id){
        manufacturerRepository.delete(id);
    }

    public void updateManufacturer(Model manufacturer){
        manufacturerRepository.update(manufacturer);
    }
}
