package com.fabiansebastianj1.country.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.country.domain.models.Countries;
import com.fabiansebastianj1.country.infraestructure.CountriesRepository;

public class CountriesMYSQLRepository implements CountriesRepository {
    private final String url;
    private final String user;
    private final String password;

    public CountriesMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM countries WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Countries countries) {
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO countries (id,name) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, countries);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }   
    }

    @Override
    public void update(Countries countries) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<Countries> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Countries> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
