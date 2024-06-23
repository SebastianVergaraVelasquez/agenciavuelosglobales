package com.fabiansebastianj1.customer.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.customer.domain.models.CustomerDTO;

public interface CustomerRepository {
    void save (Customer customer);
    Optional<Customer> findById (String id);
    Optional<CustomerDTO> findCustomerDTOById (String id);
    List<Customer> findAll();
    void delete (String id);
    void update (Customer customer);

}
