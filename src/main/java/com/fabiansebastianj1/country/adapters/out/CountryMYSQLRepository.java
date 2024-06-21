package com.fabiansebastianj1.country.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.country.domain.models.Country;
import com.fabiansebastianj1.country.infraestructure.CountryRepository;

public class CountryMYSQLRepository implements CountryRepository {
    private final String url;
    private final String user;
    private final String password;

    public CountryMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM country WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Country country) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO country (id,name) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, country.getId());
                statement.setString(2, country.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Country country) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE country SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, country.getName());
                statement.setString(2, country.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Country> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM country WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                    Country country = new Country(
                            resultSet.getString("id"),
                            resultSet.getString("name"));
                    return Optional.of(country);
                }
            }
        }
    }catch(SQLException e)
    {e.printStackTrace();
        }return Optional.empty();
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM country";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Country country = new Country(
                        resultSet.getString("id"),
                        resultSet.getString("name")
                    );
                    countries.add(country);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return countries;
    }

}
