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

    public Order createOrder(long customerId, List<Long> productIds, String status) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return null;
        }

        // Crear la orden con el cliente y estado
        Order order = new Order(customer, status);

        // Obtener los productos por sus IDs y añadirlos a la orden
        for (Long productId : productIds) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                order.addProduct(product);
            }
        }

        return saveOrder(order);
    }

    public Order addProductToOrder(long orderId, long productId) {
        Order order = getOrderById(orderId);
        Product product = productService.getProductById(productId);

        if (order != null && product != null) {
            order.addProduct(product);
            return saveOrder(order);
        }

        return null;
    }
}
