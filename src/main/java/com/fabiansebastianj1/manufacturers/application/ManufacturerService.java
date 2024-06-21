package com.fabiansebastianj1.manufacturers.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.manufacturers.domain.models.Manufacturer;
import com.fabiansebastianj1.manufacturers.infrastructure.ManufacturerRepository;

public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository = manufacturerRepository;
    }

    public void createManufacturer(Manufacturer manufacturer){
        manufacturerRepository.save(manufacturer);
    }

    public Optional<Manufacturer> findManufacturerById (int id){
        return manufacturerRepository.findById(id);
    }

    public List<Manufacturer> findAllManufacturers(){
        return manufacturerRepository.findAll();
    }

    public void deleteManufacturer(int id){
        manufacturerRepository.delete(id);
    }

    public void updateManufacturer(Manufacturer manufacturer){
        manufacturerRepository.update(manufacturer);
    }
}
