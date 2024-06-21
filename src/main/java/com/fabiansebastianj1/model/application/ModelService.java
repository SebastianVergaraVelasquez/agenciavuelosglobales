package com.fabiansebastianj1.model.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.model.domain.models.Model;
import com.fabiansebastianj1.model.infrastructure.ModelRepository;

public class ModelService {

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public void createManufacturer(Model model){
        modelRepository.save(model);
    }

    public Optional<Model> findManufacturerById (int id){
        return modelRepository.findById(id);
    }

    public List<Model> findAllManufacturers(){
        return modelRepository.findAll();
    }

    public void deleteManufacturer(int id){
        modelRepository.delete(id);
    }

    public void updateManufacturer(Model model){
        modelRepository.update(model);
    }
}
