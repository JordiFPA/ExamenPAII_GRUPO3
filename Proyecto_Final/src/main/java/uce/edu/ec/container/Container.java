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
    private CustomerService customerS;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    private Customer customer;
    private Orden currentOrden;
    private List<Product> products;

    public Container() {
        customer = new Customer();
        products = new ArrayList<>();
    }

    public void registerCustomer(String name, String email, String password) throws Exception {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customerS.saveCustomer(customer);
    }

    public void createOrder(long customerId, List<Product> productList, String status) {
        currentOrden = orderService.createOrder(customerId, productList, status);
        products.clear();
    }

    public void addProductToOrder(long productId) {
        if (currentOrden != null) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                products.add(product);
                orderService.addProductToOrder(currentOrden.getId(), productId);
            }
        }
    }

    public Orden getCurrentOrder() {
        return currentOrden;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer authenticateCustomer(String email, String password) throws Exception {
        return this.customer = customerS.findCustomerByEmailAndPassword(email, password);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
