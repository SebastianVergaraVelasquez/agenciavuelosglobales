package com.fabiansebastianj1.employee.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.employee.domain.models.*;
import com.fabiansebastianj1.employee.infrastructure.EmployeeRepository;

public class EmployeeMYSQLRepository implements EmployeeRepository {
    private final String url;
    private final String user;
    private final String password;

    public EmployeeMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM employee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM employee";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("id_rol"),
                            resultSet.getDate("ingress_date"),
                            resultSet.getInt("id_airline"),
                            resultSet.getInt("id_airport")
                            );
                        employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Optional<Employee> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM employee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Employee employee = new Employee(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("id_rol"),
                            resultSet.getDate("ingress_date"),
                            resultSet.getInt("id_airline"),
                            resultSet.getInt("id_airport")
                            );
                        return Optional.of(employee);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO employee (id,name,id_rol,ingress_date,id_airline,id_airport) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, employee.getId());
                statement.setString(2, employee.getName());
                statement.setInt(3, employee.getRolId());
                statement.setDate(4, employee.getIngressDate());
                statement.setInt(5, employee.getAirlineId());
                statement.setInt(6, employee.getAirportId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE employee SET name = ?, id_rol = ?, ingress_date = ?, id_airline = ?, id_airport = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, employee.getName());
                statement.setInt(2, employee.getRolId());
                statement.setDate(3, employee.getIngressDate());
                statement.setInt(4, employee.getAirlineId());
                statement.setInt(5, employee.getAirportId());
                statement.setString(1, employee.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
