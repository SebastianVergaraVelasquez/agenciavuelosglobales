package com.fabiansebastianj1.customer.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.customer.domain.models.Customer;

public interface CustomerRepository {
    void save (Customer customer);
    Optional<Customer> findById (String id);
    List<Customer> findAll();
    void delete (String id);
    void update (Customer customer);
}
