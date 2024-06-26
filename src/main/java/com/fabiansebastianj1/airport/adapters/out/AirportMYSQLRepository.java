package com.fabiansebastianj1.airport.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.airport.domain.models.AirportDTO;
import com.fabiansebastianj1.airport.infrastructure.AirportRepository;

public class AirportMYSQLRepository implements AirportRepository {
    private final String url;
    private final String user;
    private final String password;

    public AirportMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM airport WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airport> findAll() {
        List<Airport> airports = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airport";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Airport airport = new Airport(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("id_city"));
                    airports.add(airport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airports;
    }

    @Override
    public List<Airport> findAllAirportsByCityId(String cityId) {
        List<Airport> airports = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airport WHERE id_city = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, cityId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Airport airport = new Airport(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("id_city"));
                        airports.add(airport);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airports;
    }

    @Override
    public Optional<Airport> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airport WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Airport airport = new Airport(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("id_city"));
                        return Optional.of(airport);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Airport airport) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO airport (id,name,id_city) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airport.getId());
                statement.setString(2, airport.getName());
                statement.setString(3, airport.getCityId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Airport airport) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE airport SET name = ?, id_city = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airport.getName());
                statement.setString(2, airport.getCityId());
                statement.setString(3, airport.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<AirportDTO> findAirportCityById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT ai.id AS airport_id, ai.name AS airport, ci.name AS city FROM airport AS ai " +
                    "JOIN city ci ON ci.id = ai.id_city " +
                    "WHERE ai.id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        AirportDTO airport = new AirportDTO(
                                resultSet.getString("airport_id"),
                                resultSet.getString("airport"),
                                resultSet.getString("city"));
                        return Optional.of(airport);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
