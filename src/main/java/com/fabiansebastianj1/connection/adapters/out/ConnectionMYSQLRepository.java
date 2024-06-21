package com.fabiansebastianj1.connection.adapters.out;

import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.connection.infraestructure.ConnectionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConnectionMYSQLRepository implements ConnectionRepository {

    private final String url;
    private final String user;
    private final String password;

    public ConnectionMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    @Override
    public void save(Connections connections) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "INSERT INTO connection (connection_number,id_trip,id_plane,id_airport,id_trip_status) VALUES (?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1,connections.getConnection_number());
                statement.setInt(2,connections.getId_trip());
                statement.setInt(3,connections.getId_plane());
                statement.setString(4,connections.getId_airport());
                statement.setInt(5,connections.getId_trip_status());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(Connections connections) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "UPDATE connection SET connection_number = ?, id_trip = ?, " +
                    "id_plane = ?, id_airport = ?, id_trip_status = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1,connections.getConnection_number());
                statement.setInt(2,connections.getId_trip());
                statement.setInt(3,connections.getId_plane());
                statement.setString(4,connections.getId_airport());
                statement.setInt(5,connections.getId_trip_status());
                statement.setInt(6,connections.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "DELETE FROM connection WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1,id);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Connections> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "SELECT * FROM connection WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1,id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                    Connections connections = new Connections(
                            resultSet.getInt("id"),
                            resultSet.getString("connection_number"),
                            resultSet.getInt("id_trip"),
                            resultSet.getInt("id_plane"),
                            resultSet.getString("id_airport"),
                            resultSet.getInt("id_trip_status"));
                    return Optional.of(connections);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Connections> findAll() {
        List<Connections> connections = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url,user,password)) {
            String query = "SELECT * FROM connection";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Connections connect = new Connections(
                            resultSet.getInt("id"),
                            resultSet.getString("connection_number"),
                            resultSet.getInt("id_trip"),
                            resultSet.getInt("id_plane"),
                            resultSet.getString("id_airport"),
                            resultSet.getInt("id_trip_status"));
                    connections.add(connect);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connections;
    }
}
