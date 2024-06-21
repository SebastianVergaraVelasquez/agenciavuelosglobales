package com.fabiansebastianj1.model.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.model.domain.models.Model;
import com.fabiansebastianj1.model.infrastructure.ModelRepository;

public class ModelMYSQLRepository implements ModelRepository {
    private final String url;
    private final String user;
    private final String password;

    public ModelMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM model WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Model> findAll() {
        List<Model> manufacturers = new ArrayList<>();           
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM model";   
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()){
                    while (resultSet.next()) {
                        Model manufacturer = new Model(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("id_manufacturer"));
                            manufacturers.add(manufacturer);
                    }
                }           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    @Override
    public Optional<Model> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM model WHERE id = ?";
            try( PreparedStatement statement = connection.prepareStatement(query)){ 
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    Model model = new Model(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("id_manufacturer"))
                    ;
                    return Optional.of(model);
                }
            }    
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Model model) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO model (name,id_manufacturer) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, model.getName());
                statement.setInt(2, model.getManufacturerId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model model) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE model SET name = ? , id_manufacturer = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, model.getName());
                statement.setInt(2, model.getManufacturerId());
                statement.setInt(3, model.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
