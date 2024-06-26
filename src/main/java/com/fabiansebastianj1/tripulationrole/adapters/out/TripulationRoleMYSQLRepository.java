package com.fabiansebastianj1.tripulationrole.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.tripulationrole.domain.models.TripulationRole;
import com.fabiansebastianj1.tripulationrole.infrastructure.TripulationRoleRepository;

public class TripulationRoleMYSQLRepository implements TripulationRoleRepository {
    private final String url;
    private final String user;
    private final String password;

    // Constructor que inicializa la URL, usuario y contraseña de la base de datos
    public TripulationRoleMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // Método para guardar un rol de tripulación en la base de datos
    @Override
    public void save(TripulationRole tripulationRole) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO tripulation_role (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripulationRole.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un rol de tripulación en la base de datos
    @Override
    public void update(TripulationRole tripulationRole) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE tripulation_role SET name = ? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripulationRole.getName());
                statement.setInt(2, tripulationRole.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un rol de tripulación de la base de datos por su ID
    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM tripulation_role WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar un rol de tripulación por su ID en la base de datos
    @Override
    public Optional<TripulationRole> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripulation_role WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripulationRole tripulationRole = new TripulationRole(
                                resultSet.getInt("id"),
                                resultSet.getString("name"));
                        return Optional.of(tripulationRole);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Método para obtener todos los roles de tripulación almacenados en la base de datos
    @Override
    public List<TripulationRole> findAll() {
        List<TripulationRole> tripulationRoles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripulation_role";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TripulationRole tripulationRole = new TripulationRole(
                            resultSet.getInt("id"),
                            resultSet.getString("name"));
                    tripulationRoles.add(tripulationRole);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripulationRoles;
    }
}
