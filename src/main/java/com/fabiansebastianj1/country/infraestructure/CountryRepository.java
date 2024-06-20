package com.fabiansebastianj1.country.infraestructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.country.domain.models.Country;

public interface CountryRepository {
    void save(Country country);
    void update(Country country);
    Optional<Country> findById(String id);
    void delete(String id);
    List<Country> findAll();
}
