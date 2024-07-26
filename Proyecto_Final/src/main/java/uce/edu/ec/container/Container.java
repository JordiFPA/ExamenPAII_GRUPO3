package uce.edu.ec.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uce.edu.ec.model.Customer;
import uce.edu.ec.model.Orden;
import uce.edu.ec.model.Product;
import uce.edu.ec.service.CustomerService;
import uce.edu.ec.service.OrderService;
import uce.edu.ec.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Container {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    private Customer customer;
    private Orden currentOrder;
    private List<Product> products;

    public Container() {
        products = new ArrayList<>();
    }

    public void registerCustomer(String name, String email, String password) throws Exception {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customerService.saveCustomer(customer);
    }

    public void createOrder(long customerId, List<Product> productList, String status) {
        currentOrder = orderService.createOrder(customerId, productList, status);
        products.clear();
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

    public Orden getCurrentOrder() {
        return currentOrder;
    }

    public List<Orden> getOrdersByCustomer(long customerId) {
        return orderService.getOrdersByCustomer(customerId);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer authenticateCustomer(String email, String password) throws Exception {
        Customer authenticatedCustomer = customerService.findCustomerByEmailAndPassword(email, password);
        this.customer = authenticatedCustomer;
        System.out.println("Cliente autenticado: " + (authenticatedCustomer != null ? authenticatedCustomer.getName() : "Nulo"));
        return authenticatedCustomer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Orden> getOrders() {
        return orderService.getAllOrders();
    }
}
