package com.fabiansebastianj1.customer.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.customer.infrastructure.CustomerRepository;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerById (String id){
        return customerRepository.findById(id);
    }

    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(String id){
        customerRepository.delete(id);
    }

    public void updateCustomer(Customer customer){
        customerRepository.update(customer);
    }
}
