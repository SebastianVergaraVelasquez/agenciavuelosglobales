package com.fabiansebastianj1.city.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.city.domain.models.*;

public interface CityRepository {
    void save (City city);
    void update (City city);
    void delete (String id);
    Optional <City> findById(String id);
    List<City> findAll();
    List<City> findAllByCityId(String countryId);
}
