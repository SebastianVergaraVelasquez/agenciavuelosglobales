package com.fabiansebastianj1.status.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.status.infrastructure.StatusRepository;
import com.fabiansebastianj1.status.domain.models.Status;

public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public void createStatus(Status status){
        statusRepository.save(status);
    }

    public Optional<Status> findStatusById (int id){
        return statusRepository.findById(id);
    }

    public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }

    public void deleteStatus(int id){
        statusRepository.delete(id);
    }

    public void updateStatus(Status status){
        statusRepository.update(status);
    }
}
