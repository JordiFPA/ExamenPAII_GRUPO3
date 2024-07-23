package uce.edu.ec.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uce.edu.ec.model.Customer;
import uce.edu.ec.service.CustomerService;

@Component
public class Container {
    @Autowired
    private CustomerService customerS;
    private Customer customer;

    public Container() {
        customer = new Customer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void registerCustomer(String name, String email, String password) throws Exception {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customerS.saveCustomer(customer);
    }
}

