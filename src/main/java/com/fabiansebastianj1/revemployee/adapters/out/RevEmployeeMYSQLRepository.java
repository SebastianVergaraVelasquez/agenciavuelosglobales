package com.fabiansebastianj1.revemployee.adapters.out;

import com.fabiansebastianj1.revemployee.domain.models.RevEmployee;
import com.fabiansebastianj1.revemployee.infraestructure.RevEmployeeRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
