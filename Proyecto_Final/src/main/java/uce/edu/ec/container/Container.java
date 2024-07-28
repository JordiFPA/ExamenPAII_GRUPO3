package uce.edu.ec.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uce.edu.ec.interfaces.Observable;
import uce.edu.ec.interfaces.Observer;
import uce.edu.ec.model.Customer;
import uce.edu.ec.model.ManufacturingProcess;
import uce.edu.ec.model.Orden;
import uce.edu.ec.model.Product;
import uce.edu.ec.service.CustomerService;
import uce.edu.ec.service.OrderService;
import uce.edu.ec.service.ProductService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class Container implements Observable {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ManufacturingProcess manufacturingProcess;

    private Customer customer;
    private Orden currentOrder;
    private List<Product> products;
    private List<Observer> observers;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public Container() {
        products = new ArrayList<>();
        observers = new ArrayList<>();
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
        notificar();
    }

    public void addProductToOrder(long productId) {
        if (currentOrder != null) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                products.add(product);
                orderService.addProductToOrder(currentOrder.getId(), productId);
                notificar();
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

    public void executeManufacturingProcess(long orderId, java.util.function.Consumer<String> statusUpdater, JProgressBar progressBar) {
        Orden order = orderService.getOrderById(orderId);
        if (order == null) {
            statusUpdater.accept("Orden no encontrada.");
            return;
        }

        // Actualizar el estado a "Fabricando"
        order.setStatus("Fabricando");
        orderService.saveOrder(order);
        notificar();

        // Configurar el progreso inicial
        progressBar.setValue(0);

        // Ejecutar el proceso de fabricación en un hilo separado
        executorService.submit(() -> {
            try {
                // Corte
                statusUpdater.accept("Corte en marcha...");
                manufacturingProcess.cut(() -> SwingUtilities.invokeLater(() -> updateProgressBar(progressBar, 25)));
                statusUpdater.accept("Corte completado.");
                notificar(); // Notificar a los observadores

                // Pintura
                statusUpdater.accept("Pintura en marcha...");
                manufacturingProcess.paint(() -> SwingUtilities.invokeLater(() -> updateProgressBar(progressBar, 50)));
                statusUpdater.accept("Pintura completada.");
                notificar(); // Notificar a los observadores

                // Pulido
                statusUpdater.accept("Pulido en marcha...");
                manufacturingProcess.polish(() -> SwingUtilities.invokeLater(() -> updateProgressBar(progressBar, 75)));
                statusUpdater.accept("Pulido completado.");
                notificar(); // Notificar a los observadores

                // Ensamblaje
                statusUpdater.accept("Ensamblaje en marcha...");
                manufacturingProcess.build(() -> SwingUtilities.invokeLater(() -> updateProgressBar(progressBar, 100)));
                statusUpdater.accept("Ensamblaje completado.");
                notificar(); // Notificar a los observadores

                // Actualizar el estado a "Listo" después de fabricar
                order.setStatus("Listo");
                orderService.saveOrder(order);
                statusUpdater.accept("Producto fabricado: " + order.getId());
                notificar(); // Notificar a los observadores
            } catch (Exception ex) {
                ex.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    statusUpdater.accept("Error en el proceso de fabricación: " + ex.getMessage());
                    notificar();
                });
            }
        });
    }

    private void updateProgressBar(JProgressBar progressBar, int value) {
        SwingUtilities.invokeLater(() -> progressBar.setValue(value));
    }

    private void sleep() {
        try {
            Thread.sleep(2000); // Simula el tiempo de procesamiento (2 segundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);

    }

    @Override
    public void notificar() {
        for (Observer observer : observers) {
            observer.update("El estado del pedido ha cambiado. Estado actual: " + (currentOrder != null ? currentOrder.getStatus() : "N/A"));
        }
    }
}
