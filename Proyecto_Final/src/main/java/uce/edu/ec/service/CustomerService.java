package uce.edu.ec.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.model.Customer;
import uce.edu.ec.repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public synchronized void saveCustomer(Customer customer) throws Exception {
        validateCustomerFields(customer);

        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new Exception("El usuario ya existe");
        }
        customerRepository.save(customer);
    }

    private void validateCustomerFields(Customer customer) throws Exception {
        if (customer.getEmail().isBlank() || customer.getPassword().isBlank() || customer.getName().isBlank()) {
            throw new Exception("Todos los campos son obligatorios");
        }

        if (!customer.getName().matches("[a-zA-Z]+")) {
            throw new Exception("El nombre solo puede contener letras");
        }

        if (!customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new Exception("El email no es válido");
        }
    }

    public Optional<Customer> findCustomer(long id) {
        return customerRepository.findById(id);
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Optional<Customer> findCustomerByEmailAndPassword(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }
}