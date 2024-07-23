package uce.edu.ec.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.model.Customer;
import uce.edu.ec.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service

public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    public Optional<Customer> findCustomer(long id) {
      return customerRepository.findById(id);
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElse(null);
    }

}
