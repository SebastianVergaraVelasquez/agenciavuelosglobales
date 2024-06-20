package com.fabiansebastianj1.countries.infraestructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.countries.domain.models.Countries;

public interface CountriesRepository {
    void save(Countries countries);
    void update(Countries countries);
    Optional<Countries> findById(String id);
    void delete(String id);
    List<Countries> findAll();
}
