package com.fabiansebastianj1.customer.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.customer.infrastructure.CustomerRepository;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.customer.domain.models.CustomerDTO;

public class CustomerMYSQLRepository implements CustomerRepository {
    private final String url;
    private final String user;
    private final String password;

    public CustomerMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM customer WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM customer";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Customer customer = new Customer(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getInt("id_document")
                            );
                            customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Optional<Customer> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM customer WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer customer = new Customer(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getInt("id_document")
                            );
                        return Optional.of(customer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO customer (id,name,age, id_document) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customer.getId());
                statement.setString(2, customer.getName());
                statement.setInt(3, customer.getAge());
                statement.setInt(4, customer.getDocumentTypeId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE customer SET name = ?, age = ?, id_document = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customer.getName());
                statement.setInt(2, customer.getAge());
                statement.setInt(3, customer.getDocumentTypeId());
                statement.setString(4, customer.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CustomerDTO> findCustomerDTOById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT "+
            "cus.id AS customer_id, "+
            "cus.name AS customer_name, "+
            "cus.age AS customer_age, "+
            "dt.name AS document_type "+
            "FROM customer AS cus "+
            "JOIN document_type dt ON dt.id = cus.id_document "+
            "WHERE cus.id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        CustomerDTO customer = new CustomerDTO(
                            resultSet.getString("customer_id"),
                            resultSet.getString("customer_name"),
                            resultSet.getInt("customer_age"),
                            resultSet.getString("document_type")
                            );
                        return Optional.of(customer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
