package uce.edu.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.model.Customer;
import uce.edu.ec.model.Orden;
import uce.edu.ec.model.Product;
import uce.edu.ec.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    public Orden saveOrder(Orden orden) {
        return orderRepository.save(orden);
    }

    @Transactional(readOnly = true)
    public List<Orden> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Orden> getOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    public Orden getOrderById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Orden> getOrdersByCustomer(long customerId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getCustomer().getId() == customerId)
                .collect(Collectors.toList());
    }


    public Orden createOrder(long customerId, List<Product> productList, String status) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        Orden orden = new Orden();
        orden.setCustomer(customer);
        orden.setStatus(status);
        for (Product product : productList) {
            Product existingProduct = productService.getProductById(product.getId());
            if (existingProduct == null) {
                existingProduct = productService.saveProduct(product); // Save new product and get its ID
            }
            orden.addProduct(existingProduct);
        }

        return saveOrder(orden);
    }

    public Orden addProductToOrder(long orderId, long productId) {
        Orden orden = getOrderById(orderId);
        Product product = productService.getProductById(productId);

        if (orden != null && product != null) {
            orden.addProduct(product);
            return saveOrder(orden);
        }

        return null;
    }

    public void deleteOrder(long orderId) {
        orderRepository.deleteById(orderId);
    }

}
