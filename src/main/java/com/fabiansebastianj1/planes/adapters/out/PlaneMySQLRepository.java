package com.fabiansebastianj1.planes.adapters.out;

import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.infrastructure.PlaneRepository;
  

public class PlaneMySQLRepository implements PlaneRepository {

    private final String url;
    private final String user;
    private final String password;

    public PlaneMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM plane WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Plane> findAll() {
        List<Plane> planes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM plane";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Plane plane = new Plane(
                            resultSet.getInt("id"),
                            resultSet.getString("plates"),
                            resultSet.getInt("capacity"),
                            resultSet.getDate("fabircation_date"),
                            resultSet.getInt("id_status"),
                            resultSet.getInt("id_model")
                            );
                    planes.add(plane);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planes;
    }

    @Override
    public Optional<Plane> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM plane WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Plane plane = new Plane(
                            resultSet.getInt("id"),
                            resultSet.getString("plates"),
                            resultSet.getInt("capacity"),
                            resultSet.getDate("fabircation_date"),
                            resultSet.getInt("id_status"),
                            resultSet.getInt("id_model")
                            );
                        return Optional.of(plane);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Plane plane) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO plane (plates,capacity,fabircation_date,id_status,id_model) VALUES (?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, plane.getPlates());
                statement.setInt(2, plane.getCapacity());
                statement.setDate(3, plane.getFabrication_date());
                statement.setInt(4, plane.getId_status());
                statement.setInt(5, plane.getId_model());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Plane plane) {
        //Actualización temporal, faltan los demás campos, solo se actualiza la placa
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE plane SET plates = ?, capacity = ?, fabircation_date = ?, id_status = ?, id_model = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, plane.getPlates());
                statement.setInt(2, plane.getCapacity());
                statement.setDate(3, plane.getFabrication_date());
                statement.setInt(4, plane.getId_status());
                statement.setInt(5, plane.getId_model());
                statement.setInt(6, plane.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

