package com.fabiansebastianj1.passenger.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.passenger.domain.models.Passenger;
import com.fabiansebastianj1.passenger.infrastructure.PassengerRepository;

public class PassengerMYSQLRepository implements PassengerRepository {

    private final String url;
    private final String user;
    private final String password;

    public PassengerMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    
    @Override
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(url, user,password)) {
            String query = "DELETE FROM passenger WHERE nif = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1,id);
                    statement.executeUpdate();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Passenger> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "SELECT * FROM passenger WHERE nif = ?;";
            try (PreparedStatement statement =connection.prepareStatement(query)){
                statement.setString(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                Passenger passenger = new Passenger(
                    resultSet.getInt("id"),
                    resultSet.getString("nif"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("seat"),
                    resultSet.getInt("id_document_type"),
                    resultSet.getInt("id_trip_booking_detail")
                );
                return Optional.of(passenger);
                }
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public void save(Passenger passenger) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "INSERT INTO passenger (nif,name,age,seat,id_document_type,id_trip_booking_detail) VALUES (?,?,?,?,?,?);";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1,passenger.getNif());
                statement.setString(2,passenger.getName());
                statement.setInt(3,passenger.getAge());
                statement.setString(4,passenger.getSeat());
                statement.setInt(5,passenger.getDocumentTypeId());
                statement.setInt(6,passenger.getTripBookingDetailId());       
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Passenger passenger) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "UPDATE passenger SET nif = ?,name=?,age=?,seat=?,id_document_type=?,id_trip_booking_detail=? WHERE nif = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1,passenger.getNif());
                statement.setString(1,passenger.getName());
                statement.setInt(1,passenger.getAge());
                statement.setString(1,passenger.getSeat());
                statement.setInt(1,passenger.getDocumentTypeId());
                statement.setInt(1,passenger.getTripBookingDetailId());
                statement.setString(1,passenger.getNif());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Passenger> passengersByTripBookingId(int tripIdBookingId) {
        List<Passenger> passengers = new ArrayList<>();
        String query = "SELECT pas.* " +
                       "FROM passenger AS pas " +
                       "JOIN trip_booking_detail AS tbd ON pas.id_trip_booking_detail = tbd.id " +
                       "JOIN trip_booking AS tb ON tb.id = tbd.id_trip_booking " +
                       "WHERE tbd.id_trip_booking = ?;";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tripIdBookingId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Passenger passenger = new Passenger(
                            resultSet.getInt("id"),
                            resultSet.getString("nif"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("seat"),
                            resultSet.getInt("id_document_type"),
                            resultSet.getInt("id_trip_booking_detail")
                    );
                    passengers.add(passenger);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passengers;
    }


    @Override
    public List<String> getOccupiedSeats(int tripId) {
        List<String> occupiedSeats = new ArrayList<>();
        String query = "SELECT pas.seat AS puestos_ocupados " +
                       "FROM passenger AS pas " +
                       "JOIN trip_booking_detail AS tbd ON pas.id_trip_booking_detail = tbd.id " +
                       "JOIN trip_booking AS tb ON tb.id = tbd.id_trip_booking " +
                       "WHERE tb.id_trip = ?;";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tripId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String seat = resultSet.getString("puestos_ocupados");
                    occupiedSeats.add(seat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return occupiedSeats;
    }

    public int getTotalOccupiedSeats(int tripId){
            int occupiedSeats = 0;
            String query = "SELECT COUNT(pas.id) AS ocupados " +
                           "FROM passenger AS pas " +
                           "JOIN trip_booking_detail AS tbd ON pas.id_trip_booking_detail = tbd.id " +
                           "JOIN trip_booking AS tb ON tb.id = tbd.id_trip_booking " +
                           "WHERE tb.id_trip = ?;";
    
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tripId);
    
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        occupiedSeats = resultSet.getInt("ocupados");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    
            return occupiedSeats;
        }
}
