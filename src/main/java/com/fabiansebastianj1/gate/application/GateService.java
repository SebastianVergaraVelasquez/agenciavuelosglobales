package com.fabiansebastianj1.gate.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.gate.domain.models.Gate;
import com.fabiansebastianj1.gate.infrastructure.GateRepository;

public class GateService {

     private final GateRepository gateRepository;

    public GateService (GateRepository gateRepository){
        this.gateRepository = gateRepository;
    }

    public void createGate(Gate gate){
        gateRepository.save(gate);
    }

    public Optional<Gate> findGateById (int id){
        return gateRepository.findById(id);
    }

    public List<Gate> findAllGates(){
        return gateRepository.findAll();
    }

    public void deleteGate(int id){
        gateRepository.delete(id);
    }

    public void updateGate(Gate gate){
        gateRepository.update(gate);
    }
}
