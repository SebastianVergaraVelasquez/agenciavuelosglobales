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
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM employee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<EmployeeDTO> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT "+
            "em.id AS id, "+
            "em.name AS nombre, "+
            "em.id_rol AS id_rol, "+
            "tr.name AS role_name, "+
            "em.ingress_date AS ingress_date, " +
            "em.id_airline AS id_airline, "+
            "airl.name AS name_airline, "+
            "airp.id AS id_airport, "+
            "c.id AS id_city, "+
            "c.name AS name_city "+
            "FROM employee AS em "+
            "JOIN tripulation_role AS tr ON tr.id = em.id_rol "+
            "JOIN airline AS airl ON airl.id = em.id_airline "+
            "JOIN airport AS airp ON airp.id = em.id_airport "+
            "JOIN city AS c ON c.id = airp.id_city "+
            "ORDER BY airl.name;";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    EmployeeDTO employee = new EmployeeDTO(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("id_rol"),
                            resultSet.getString("role_name"),
                            resultSet.getString("ingress_date"),
                            resultSet.getInt("id_airline"),
                            resultSet.getString("name_airline"),
                            resultSet.getString("id_airport"),
                            resultSet.getString("id_city"),
                            resultSet.getString("name_city")
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
    public List<EmployeeDTO> findAllTechniciansByAirline(int airlineId) {
        List<EmployeeDTO> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT "+
            "em.id AS id, "+
            "em.name AS nombre, "+
            "em.id_rol AS id_rol, "+
            "tr.name AS role_name, "+
            "em.ingress_date AS ingress_date, " +
            "em.id_airline AS id_airline, "+
            "airl.name AS name_airline, "+
            "airp.id AS id_airport, "+
            "c.id AS id_city, "+
            "c.name AS name_city "+
            "FROM employee AS em "+
            "JOIN tripulation_role AS tr ON tr.id = em.id_rol "+
            "JOIN airline AS airl ON airl.id = em.id_airline "+
            "JOIN airport AS airp ON airp.id = em.id_airport "+
            "JOIN city AS c ON c.id = airp.id_city "+
            "WHERE em.id_airline = ? AND em.id_rol = 4;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, airlineId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        EmployeeDTO employee = new EmployeeDTO(
                            resultSet.getString("id"),
                            resultSet.getString("nombre"),
                            resultSet.getInt("id_rol"),
                            resultSet.getString("role_name"),
                            resultSet.getString("ingress_date"),
                            resultSet.getInt("id_airline"),
                            resultSet.getString("name_airline"),
                            resultSet.getString("id_airport"),
                            resultSet.getString("id_city"),
                            resultSet.getString("name_city")
                        );
                        employees.add(employee);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<EmployeeDTO> findAllTripulation(int airlineId) {
        List<EmployeeDTO> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT "+
            "em.id AS id, "+
            "em.name AS nombre, "+
            "em.id_rol AS id_rol, "+
            "tr.name AS role_name, "+
            "em.ingress_date AS ingress_date, " +
            "em.id_airline AS id_airline, "+
            "airl.name AS name_airline, "+
            "airp.id AS id_airport, "+
            "c.id AS id_city, "+
            "c.name AS name_city "+
            "FROM employee AS em "+
            "JOIN tripulation_role AS tr ON tr.id = em.id_rol "+
            "JOIN airline AS airl ON airl.id = em.id_airline "+
            "JOIN airport AS airp ON airp.id = em.id_airport "+
            "JOIN city AS c ON c.id = airp.id_city "+
            "WHERE em.id_airline = ? AND em.id_rol != 4;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, airlineId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        EmployeeDTO employee = new EmployeeDTO(
                            resultSet.getString("id"),
                            resultSet.getString("nombre"),
                            resultSet.getInt("id_rol"),
                            resultSet.getString("role_name"),
                            resultSet.getString("ingress_date"),
                            resultSet.getInt("id_airline"),
                            resultSet.getString("name_airline"),
                            resultSet.getString("id_airport"),
                            resultSet.getString("id_city"),
                            resultSet.getString("name_city")
                        );
                        employees.add(employee);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


    @Override
    public Optional<Employee> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM employee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Employee employee = new Employee(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("id_rol"),
                            resultSet.getString("ingress_date"),
                            resultSet.getInt("id_airline"),
                            resultSet.getString("id_airport")
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
                statement.setString(4, employee.getIngressDate());
                statement.setInt(5, employee.getAirlineId());
                statement.setString(6, employee.getAirportId());
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
                statement.setString(3, employee.getIngressDate());
                statement.setInt(4, employee.getAirlineId());
                statement.setString(5, employee.getAirportId());
                statement.setString(1, employee.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
