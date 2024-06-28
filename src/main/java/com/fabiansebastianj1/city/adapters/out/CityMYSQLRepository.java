package com.fabiansebastianj1.city.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.city.domain.models.City;
import com.fabiansebastianj1.city.infrastructure.CityRepository;

public class CityMYSQLRepository implements CityRepository {
    private final String url;
    private final String user;
    private final String password;

    public CityMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM city WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM city";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    City city = new City(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("id_country"));
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findAllByCityId(String countryId) {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM city WHERE id_country = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, countryId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        City city = new City(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("id_country"));
                        cities.add(city);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public Optional<City> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM city WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        City city = new City(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("id_country"));
                        return Optional.of(city);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(City city) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO city (id,name,id_country) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, city.getId());
                statement.setString(1, city.getName());
                statement.setString(1, city.getCountryId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(City city) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE city SET name = ?, id_country = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, city.getName());
                statement.setString(2, city.getCountryId());
                statement.setString(3, city.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
