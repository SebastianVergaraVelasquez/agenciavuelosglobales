package com.fabiansebastianj1.tripstatus.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.tripstatus.domain.models.TripStatus;
import com.fabiansebastianj1.tripstatus.infrastructure.TripStatusRepository;

public class TripStatusMYSQLRepository implements TripStatusRepository{
     private final String url;
    private final String user;
    private final String password;

    public TripStatusMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM trip_status WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(TripStatus tripStatus) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_status (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripStatus.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TripStatus tripStatus) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE country SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripStatus.getName());
                statement.setInt(2, tripStatus.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<TripStatus> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_status WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripStatus tripStatus = new TripStatus(
                                resultSet.getInt("id"),
                                resultSet.getString("name"));
                        return Optional.of(tripStatus);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<TripStatus> findAll() {
        List<TripStatus> tripStatuses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_status";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TripStatus tripStatus = new TripStatus(
                            resultSet.getInt("id"),
                            resultSet.getString("name"));
                            tripStatuses.add(tripStatus);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripStatuses;
    }
}
