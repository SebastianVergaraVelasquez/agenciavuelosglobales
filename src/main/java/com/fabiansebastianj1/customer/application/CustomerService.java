package com.fabiansebastianj1.customer.application;

import java.util.List;
import java.util.Optional;

// import com.fabiansebastianj1.country.domain.models.Country;
// import com.fabiansebastianj1.country.infraestructure.CountryRepository;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.customer.domain.models.CustomerDTO;
import com.fabiansebastianj1.customer.infrastructure.CustomerRepository;

public class CustomerService {
    private final CustomerRepository customerRepository;
//    private final CountryRepository countryRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
//
//    public CustomerService(CustomerRepository customerRepository, CountryRepository countryRepository) {
//        this.customerRepository = customerRepository;
//        this.countryRepository = countryRepository;
//    }

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

    public Optional<CustomerDTO> findCustomerDTO(String id){
        return customerRepository.findCustomerDTOById(id);
    }

//    public List<Country> getAllCountry(){
//        return countryRepository.findAll();
//    }
}
