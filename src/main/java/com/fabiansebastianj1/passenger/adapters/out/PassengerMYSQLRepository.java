package com.fabiansebastianj1.passenger.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> getOccupiedSeats(int tripId) {
        List<String> occupiedSeats = new ArrayList<>();
        String query = "SELECT pas.seat AS puestos_ocupados " +
                       "FROM passenger AS pas " +
                       "JOIN trip_booking_detail AS tbd ON pas.id_trip_booking_detail = tbd.id " +
                       "JOIN trip_booking AS tb ON tb.id = tbd.id_trip_booking " +
                       "WHERE tb.id_trip = ?";

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
                           "WHERE tb.id_trip = ?";
    
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
