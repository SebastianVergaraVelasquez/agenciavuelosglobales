package com.fabiansebastianj1.tripbookingdetails.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetails;
import com.fabiansebastianj1.tripbookingdetails.infrastructure.TripBookingDetailsRepository;

public class TripBookingDetailsMYSQLRepository implements TripBookingDetailsRepository {
    private final String url;
    private final String user;
    private final String password;

    public TripBookingDetailsMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM trip_booking_detail WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TripBookingDetails> findAll() {
        List<TripBookingDetails> tripBookingDetails = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking_detail";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TripBookingDetails tripBookingDetail = new TripBookingDetails(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_trip_booking"),
                            resultSet.getString("id_customer"),
                            resultSet.getInt("id_fare")
                            );
                            tripBookingDetails.add(tripBookingDetail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookingDetails;
    }

    @Override
    public Optional<TripBookingDetails> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking_detail WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripBookingDetails tripBookingDetail = new TripBookingDetails(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_trip_booking"),
                            resultSet.getString("id_customer"),
                            resultSet.getInt("id_fare")
                            );
                        return Optional.of(tripBookingDetail);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(TripBookingDetails tripBookingDetail) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_booking_detail (id_trip_booking, id_customer, id_fare) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tripBookingDetail.getTripBookingId());
                statement.setString(2, tripBookingDetail.getCustomerId());
                statement.setInt(3, tripBookingDetail.getFareId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TripBookingDetails tripBookingDetail) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE trip_booking_detail SET id_trip_booking = ?, id_customer = ?, id_fare = ?  WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tripBookingDetail.getTripBookingId());
                statement.setString(2, tripBookingDetail.getCustomerId());
                statement.setInt(3, tripBookingDetail.getFareId());
                statement.setInt(4, tripBookingDetail.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
