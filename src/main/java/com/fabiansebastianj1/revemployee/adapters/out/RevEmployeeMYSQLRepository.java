package com.fabiansebastianj1.revemployee.adapters.out;

import com.fabiansebastianj1.revemployee.domain.models.RevEmployee;
import com.fabiansebastianj1.revemployee.infraestructure.RevEmployeeRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RevEmployeeMYSQLRepository implements RevEmployeeRepository {
    private final String url;
    private final String user;
    private final String password;

    public RevEmployeeMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(RevEmployee revEmployee) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO rev_employee (id_employee,id_revision, description) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1,revEmployee.getId_employee());
                statement.setInt(2,revEmployee.getId_revision());
                statement.setString(3,revEmployee.getDescription());
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<RevEmployee> findRevEmployeeById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM rev_employee WHERE id_revision = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        RevEmployee revEmployee = new RevEmployee(
                            resultSet.getString("id_employee"),
                            resultSet.getInt("id_revision"),
                            resultSet.getString("description"),
                            resultSet.getInt("id_rev_employee")
                            );
                        return Optional.of(revEmployee);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(RevEmployee revEmployee) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE rev_employee SET id_employee = ?, id_revision = ?, description = ? WHERE id_rev_employee = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, revEmployee.getId_employee());
                statement.setInt(2, revEmployee.getId_revision());
                statement.setString(3, revEmployee.getDescription());
                statement.setInt(4, revEmployee.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM rev_employee WHERE id_revision = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
