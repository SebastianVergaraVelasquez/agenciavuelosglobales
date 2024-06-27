package com.fabiansebastianj1.pay_type.adapters.out;

import com.fabiansebastianj1.pay_type.domain.models.PayType;
import com.fabiansebastianj1.pay_type.infraestructure.PayTypeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PayTypeMYSQLRepository implements PayTypeRepository {
    private final String url;
    private final String user;
    private final String password;

    public PayTypeMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    @Override
    public Optional<PayType> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM pay_type WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        PayType payType = new PayType(
                                resultSet.getInt("id"),
                                resultSet.getString("name")
                        );
                        return Optional.of(payType);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<PayType> findAll() {
        List<PayType> payTypes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM pay_type";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PayType payType = new PayType(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    );
                    payTypes.add(payType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payTypes;
    }
}
