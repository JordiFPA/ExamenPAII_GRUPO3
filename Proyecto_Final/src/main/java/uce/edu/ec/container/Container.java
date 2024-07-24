package uce.edu.ec.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uce.edu.ec.model.Administrator;
import uce.edu.ec.model.Customer;
import uce.edu.ec.model.Order;
import uce.edu.ec.model.Product;
import uce.edu.ec.service.AdministratorService;
import uce.edu.ec.service.CustomerService;
import uce.edu.ec.service.OrderService;
import uce.edu.ec.service.ProductService;

import java.util.List;
import java.util.Optional;

@Component
public class Container {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AdministratorService administratorService;

    private Customer customer;
    private Order currentOrder;
    private List<Product> products;
    private Administrator administrator;

    public Container() {
        customer = new Customer();
    }

    public void registerCustomer(String name, String email, String password) throws Exception {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customerService.saveCustomer(customer);
    }

    public void registerAdministrator(String name, String password) throws Exception {
        Administrator administrator = new Administrator();
        administrator.setName(name);
        administrator.setPassword(password);
        administratorService.saveAdministrator(administrator);
    }

    public void createOrder(long customerId, List<Long> productIds, String status) {
        currentOrder = orderService.createOrder(customerId, productIds, status);
        products.clear(); //
    }

    public void addProductToOrder(long productId) {
        if (currentOrder != null) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                products.add(product);
                orderService.addProductToOrder(currentOrder.getId(), productId);
            }
        }
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer authenticateCustomer(String email, String password) throws Exception {
        return this.customer = customerService.findCustomerByEmailAndPassword(email, password);
    }

    public Administrator authenticateAdministrator(String name, String password) throws Exception {
        return this.administrator = administratorService.findAdministratorByNameAndPassword(name, password);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
