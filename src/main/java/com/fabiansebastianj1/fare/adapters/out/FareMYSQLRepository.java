package com.fabiansebastianj1.fare.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.fare.domain.models.Fare;
import com.fabiansebastianj1.fare.infrastructure.FareRepository;

public class FareMYSQLRepository implements FareRepository {
    private final String url;
    private final String user;
    private final String password;

    public FareMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM flight_fare WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Fare> findAll() {
        List<Fare> fares = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM flight_fare";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Fare fare = new Fare(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getString("detail"),
                            resultSet.getDouble("value")
                            );
                            fares.add(fare);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fares;
    }

    @Override
    public Optional<Fare> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM flight_fare WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Fare fare = new Fare(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getString("detail"),
                            resultSet.getDouble("value")
                            );
                        return Optional.of(fare);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Fare fare) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO flight_fare (description,detail,value) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, fare.getDescription());
                statement.setString(2, fare.getDetail());
                statement.setDouble(3, fare.getValue());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Fare fare) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE flight_fare SET description = ?, detail = ?, value = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, fare.getDescription());
                statement.setString(2, fare.getDetail());
                statement.setDouble(3, fare.getValue());
                statement.setInt(4, fare.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
