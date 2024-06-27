package com.fabiansebastianj1.tripbooking.adapters.out;

import com.fabiansebastianj1.tripbooking.domain.models.TripBooking;
import com.fabiansebastianj1.tripbooking.infraestructure.TripBookingRepository;
import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripBookingMYSQLRepository implements TripBookingRepository {

    private final String url;
    private final String user;
    private final String password;

    public TripBookingMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(TripBooking tripBooking) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_booking (date,id_trip) VALUES (?,?);";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripBooking.getDate());
                statement.setInt(2, tripBooking.getId_trip());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TripBooking tripBooking) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE trip_booking SET date=?, id_trip=? WHERE id=?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripBooking.getDate());
                statement.setInt(2, tripBooking.getId_trip());
                statement.setInt(3, tripBooking.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM trip_booking WHERE id=?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<TripBooking> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripBooking tripBooking = new TripBooking(
                                resultSet.getInt("id"),
                                resultSet.getString("date"),
                                resultSet.getInt("id_trip"));
                        return Optional.of(tripBooking);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<TripBooking> findAll() {
        List<TripBooking> tripBookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TripBooking tripBooking = new TripBooking(
                            resultSet.getInt("id"),
                            resultSet.getString("date"),
                            resultSet.getInt("id_trip"));
                    tripBookings.add(tripBooking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookings;
    }

    @Override
    public Optional<TripBooking> findLast() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking ORDER BY id DESC LIMIT 1";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    TripBooking tripBooking = new TripBooking(
                            resultSet.getInt("id"),
                            resultSet.getString("date"),
                            resultSet.getInt("id_trip"));
                    return Optional.of(tripBooking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void deleteTripBookingDetailForId(int id) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "DELETE FROM trip_booking_detail WHERE id_trip_booking = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1,id);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
