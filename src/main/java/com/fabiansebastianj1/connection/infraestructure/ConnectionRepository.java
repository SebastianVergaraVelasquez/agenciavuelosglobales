package com.fabiansebastianj1.connection.infraestructure;

import com.fabiansebastianj1.connection.domain.models.Connections;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository {
    void save(Connections connections);
    void update(Connections connections);
    void delete(int id);
    Optional<Connections> findById(int id);
    List<Connections> findAll();
}
