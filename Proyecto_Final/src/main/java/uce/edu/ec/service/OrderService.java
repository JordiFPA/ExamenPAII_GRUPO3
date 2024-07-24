package uce.edu.ec.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.model.Customer;
import uce.edu.ec.model.Order;

import uce.edu.ec.model.Product;
import uce.edu.ec.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;


    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(long customerId, long productId, String status) {
        Customer customer = customerService.getCustomerById(customerId);
        Product product = productService.getProductById(productId);

        if (customer != null && product != null) {
            Order order = new Order(customer, product, status);
            return saveOrder(order);
        }
        return null;
    }
}
