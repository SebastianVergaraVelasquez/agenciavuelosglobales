package com.fabiansebastianj1.planes.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.domain.models.PlaneDTO;

public interface PlaneRepository {
    void save(Plane plane);
    void update(Plane plane);
    void delete(int id);
    Optional<Plane> findById(int id);
    Optional<Plane> findByPlate(String plate);
    List<Plane> findAll();
    Optional<PlaneDTO> findPlaneInfoAdditional(String plates);
}
