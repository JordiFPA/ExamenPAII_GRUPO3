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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

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

    public void executeManufacturingProcess(long orderId, Consumer<String> statusUpdater, JProgressBar progressBar) {
        Orden order = orderService.getOrderById(orderId);
        if (order == null) {
            statusUpdater.accept("Orden no encontrada.");
            return;
        }
        currentOrder = order; // Actualizar currentOrder con la orden obtenida
        // Actualizar el estado a "Fabricando"
        order.setStatus("Fabricando");
        orderService.saveOrder(order);  // Asegurarse de guardar el estado actualizado en la base de datos
        notificar();

        // Configurar el progreso inicial
        progressBar.setValue(0);

        // Crear un contador atómico para las etapas completadas
        AtomicInteger stepsCompleted = new AtomicInteger(0);

        // Ejecutar el proceso de fabricación en un hilo separado
        executorService.submit(() -> {
            try {
                List<Product> productList = order.getProducts();
                int totalProducts = productList.size();
                int totalSteps = totalProducts * 4; // Total de etapas (4 etapas por producto)
                int progressStep = 100 / totalSteps; // Dividir el progreso total entre el número de etapas

                for (Product product : productList) {
                    // Corte
                    statusUpdater.accept("Corte en marcha para producto: " + product.getName());
                    manufacturingProcess.cut(() -> SwingUtilities.invokeLater(() -> {
                        stepsCompleted.incrementAndGet();
                        updateProgressBar(progressBar, stepsCompleted.get(), totalSteps);
                    }));
                    statusUpdater.accept("Corte completado para producto: " + product.getName());
                    notificar(); // Notificar a los observadores

                    // Pintura
                    statusUpdater.accept("Pintura en marcha para producto: " + product.getName());
                    manufacturingProcess.paint(() -> SwingUtilities.invokeLater(() -> {
                        stepsCompleted.incrementAndGet();
                        updateProgressBar(progressBar, stepsCompleted.get(), totalSteps);
                    }));
                    statusUpdater.accept("Pintura completada para producto: " + product.getName());
                    notificar(); // Notificar a los observadores

                    // Pulido
                    statusUpdater.accept("Pulido en marcha para producto: " + product.getName());
                    manufacturingProcess.polish(() -> SwingUtilities.invokeLater(() -> {
                        stepsCompleted.incrementAndGet();
                        updateProgressBar(progressBar, stepsCompleted.get(), totalSteps);
                    }));
                    statusUpdater.accept("Pulido completado para producto: " + product.getName());
                    notificar(); // Notificar a los observadores

                    // Ensamblaje
                    statusUpdater.accept("Ensamblaje en marcha para producto: " + product.getName());
                    manufacturingProcess.build(() -> SwingUtilities.invokeLater(() -> {
                        stepsCompleted.incrementAndGet();
                        updateProgressBar(progressBar, stepsCompleted.get(), totalSteps);
                    }));
                    statusUpdater.accept("Ensamblaje completado para producto: " + product.getName());
                    notificar(); // Notificar a los observadores
                }

                // Actualizar el estado a "Listo" después de fabricar todos los productos
                order.setStatus("Listo");
                orderService.saveOrder(order);  // Asegurarse de guardar el estado actualizado en la base de datos
                statusUpdater.accept("Orden fabricada: " + order.getId());
                SwingUtilities.invokeLater(() -> progressBar.setValue(100)); // Asegurarse de que la barra de progreso esté al 100%
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

    private void updateProgressBar(JProgressBar progressBar, int stepsCompleted, int totalSteps) {
        SwingUtilities.invokeLater(() -> {
            int progressValue = (stepsCompleted * 100) / totalSteps;
            progressBar.setValue(Math.min(progressValue, 100));
        });
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
            observer.update("Estado actual: " + (currentOrder != null ? currentOrder.getStatus() : "N/A"));
        }
    }
}
