package com.fabiansebastianj1.manufacturers.infrastructure;

import com.fabiansebastianj1.manufacturers.domain.models.Manufacturer;
import java.util.Optional;
import java.util.List;

public interface ManufacturerRepository {
    void save(Manufacturer manufacturer);
    boolean update(Manufacturer manufacturer);
    boolean delete(int id);
    Optional<Manufacturer> findById(int id);
    List<Manufacturer> findAll();
}
