package uce.edu.ec.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uce.edu.ec.model.Customer;
import uce.edu.ec.model.Order;
import uce.edu.ec.model.Product;
import uce.edu.ec.service.CustomerService;
import uce.edu.ec.service.OrderService;
import uce.edu.ec.service.ProductService;

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
    private Order currentOrder;
    private List<Product> products;

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

    public void createOrder(long customerId, List<Long> productIds, String status) {
        currentOrder = orderService.createOrder(customerId, productIds, status);
        products.clear(); // Limpiar la lista de productos al crear una nueva orden
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


}

