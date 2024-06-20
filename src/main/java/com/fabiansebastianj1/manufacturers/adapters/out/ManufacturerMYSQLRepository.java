package com.fabiansebastianj1.manufacturers.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.manufacturers.domain.models.Manufacturer;
import com.fabiansebastianj1.manufacturers.infrastructure.ManufacturerRepository;

public class ManufacturerMYSQLRepository implements ManufacturerRepository{
    
    private final String url;
    private final String user;
    private final String password;

    public ManufacturerMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM manufacturer WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Manufacturer> findAll() {
        List<Manufacturer> manufacturers = new ArrayList<>();           
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM manufacturer";   
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()){
                    while (resultSet.next()) {
                        Manufacturer manufacturer = new Manufacturer(
                            resultSet.getInt("id"),
                            resultSet.getString("name"));
                            manufacturers.add(manufacturer);
                    }
                }           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    @Override
    public Optional<Manufacturer> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM manufacturers WHERE id = ?";
            try( PreparedStatement statement = connection.prepareStatement(query); 
                ResultSet resultSet = statement.executeQuery()){
                    Manufacturer manufacturer = new Manufacturer(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
                    return Optional.of(manufacturer);
                }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Manufacturer manufacturer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO manufacturer (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, manufacturer.getNombre());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE manufacturer SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, manufacturer.getNombre());
                statement.setInt(2, manufacturer.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
