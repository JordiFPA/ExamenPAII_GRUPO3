package uce.edu.ec.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uce.edu.ec.impl.CustomerImpl;
import uce.edu.ec.model.Customer;

@Component
public class Container {
    @Autowired
    CustomerImpl customerS;

    public void registerCustomer(String name, String email, String password) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customerS.saveCustomer(customer);
    }



}
