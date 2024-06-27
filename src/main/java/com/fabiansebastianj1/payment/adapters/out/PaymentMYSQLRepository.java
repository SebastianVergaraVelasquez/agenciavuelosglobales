package com.fabiansebastianj1.payment.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.payment.domain.models.Payment;
import com.fabiansebastianj1.payment.infraestructure.PaymentRepository;

public class PaymentMYSQLRepository implements PaymentRepository {
    private final String url;
    private final String user;
    private final String password;

    public PaymentMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Payment payment) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO payment (pay_date,id_pay_type,total_pay,id_customer) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, payment.getPay_date());
                statement.setInt(2, payment.getId_pay_type());
                statement.setDouble(3, payment.getTotal_pay());
                statement.setString(4, payment.getId_customer());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Payment payment) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE payment SET pay_date = ?, id_pay_type = ?, total_pay = ?, id_customer = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, payment.getPay_date());
                statement.setInt(2, payment.getId_pay_type());
                statement.setDouble(3, payment.getTotal_pay());
                statement.setString(4, payment.getId_customer());
                statement.setInt(5, payment.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM payment WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Payment> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM payment WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Payment payment = new Payment(
                                resultSet.getInt("id"),
                                resultSet.getString("pay_date"),
                                resultSet.getInt("id_pay_type"),
                                resultSet.getDouble("total_pay"),
                                resultSet.getString("id_customer"));
                        return Optional.of(payment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM payment";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Payment payment = new Payment(
                            resultSet.getInt("id"),
                            resultSet.getString("pay_date"),
                            resultSet.getInt("id_pay_type"),
                            resultSet.getDouble("total_pay"),
                            resultSet.getString("id_customer"));
                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}
