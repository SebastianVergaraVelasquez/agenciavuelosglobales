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
import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetailsDTO;
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
    public Optional<TripBookingDetailsDTO> findByTripBookingIdAsDTO(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT tbd.id AS id, tbd.id_trip_booking AS id_trip_booking, "+
            "tbd.id_customer AS id_customer, flight_fare.description AS fare, tp.name AS booking_condition, tbd.id_trip_condition AS id_booking_condition "+
            "FROM trip_booking_detail AS tbd "+
            "JOIN flight_fare ON flight_fare.id = tbd.id_fare "+
            "JOIN trip_condition tp ON tp.id = tbd.id_trip_condition "+
            "WHERE tbd.id_trip_booking = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripBookingDetailsDTO tripBookingDetail = new TripBookingDetailsDTO(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_trip_booking"),
                            resultSet.getString("id_customer"),
                            resultSet.getString("fare"),
                            resultSet.getString("booking_condition"),
                            resultSet.getInt("id_booking_condition")
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
    public Optional<TripBookingDetails> findByTripBookingId(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking_detail WHERE id_trip_booking;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripBookingDetails tripBookingDetail = new TripBookingDetails(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_trip_booking"),
                            resultSet.getString("id_customer"),
                            resultSet.getInt("id_fare"),
                            resultSet.getInt("id_trip_condition")
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

    @Override
    public List<TripBookingDetailsDTO> findTripBookingByCustomerId(String id) {
        List<TripBookingDetailsDTO> tripBookingDetails = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT "+
            "tbd.id AS booking_detail_id, "+
            "tbd.id_trip_booking AS booking_id, "+
            "tb.id_trip AS trip_id, "+
            "tbd.id_customer AS customer_id, "+
            "cus.name AS customer_name, "+
            "f.description AS fare_description "+
            "FROM trip_booking_detail AS tbd"+
            "JOIN customer cus ON cus.id = tbd.id_customer "+
            "JOIN flight_fare f ON f.id = tbd.id_fare "+
            "JOIN trip_bookig tb ON tb.id = tbd.id_trip_booking "+
            "WHERE tbd.id_customer = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        TripBookingDetailsDTO tripBookingDetailsDTO = new TripBookingDetailsDTO(
                            resultSet.getInt("booking_detail_id"),
                            resultSet.getInt("booking_id"),
                            resultSet.getInt("trip_id"),
                            resultSet.getString("customer_id"),
                            resultSet.getString("customer_name"),
                            resultSet.getString("fare_description")
                            );
                        tripBookingDetails.add(tripBookingDetailsDTO);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookingDetails;
    }
    
    @Override
    public List<TripBookingDetailsDTO> findTripBookingByTripId(int id) {
        List<TripBookingDetailsDTO> tripBookingDetails = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT "+
            "tbd.id AS booking_detail_id, "+
            "tbd.id_trip_booking AS booking_id, "+
            "tb.id_trip AS trip_id, "+
            "tbd.id_customer AS customer_id, "+
            "cus.name AS customer_name, "+
            "f.description AS fare_description "+
            "FROM trip_booking_detail AS tbd"+
            "JOIN customer cus ON cus.id = tbd.id_customer "+
            "JOIN flight_fare f ON f.id = tbd.id_fare "+
            "JOIN trip_bookig tb ON tb.id = tbd.id_trip_booking "+
            "WHERE tbd.id_customer = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        TripBookingDetailsDTO tripBookingDetailsDTO = new TripBookingDetailsDTO(
                            resultSet.getInt("booking_detail_id"),
                            resultSet.getInt("booking_id"),
                            resultSet.getInt("trip_id"),
                            resultSet.getString("customer_id"),
                            resultSet.getString("customer_name"),
                            resultSet.getString("fare_description")
                            );
                        tripBookingDetails.add(tripBookingDetailsDTO);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookingDetails;
    }
}
