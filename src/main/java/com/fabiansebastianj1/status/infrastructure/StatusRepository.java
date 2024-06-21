package com.fabiansebastianj1.status.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.status.domain.models.Status;

public interface StatusRepository {
    void save(Status status);
    void update(Status status);
    void delete(int id);
    Optional<Status> findById(int id);
    List<Status> findAll();
}
