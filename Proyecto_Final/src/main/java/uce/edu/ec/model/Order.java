package uce.edu.ec.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Persistent;

import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    @Column
    private String status;
    @Transient
    private List<Process> procesos;

    public Order() {
    }

    public Order(Customer customer, Product product, String status) {
        this.customer = customer;
        this.product = product;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Process> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<Process> procesos) {
        this.procesos = procesos;
    }

    public void procesarOrden() {
        System.out.println("Procesando orden para el producto: " + product.getName());
        for (Process proceso : procesos) {
            switch (proceso.getNombre()) {
                case "Cortar":
                    proceso.cut();
                    break;
                case "Ensamblar":
                    proceso.assembly();
                    break;
                case "Pintar":
                    proceso.paint();
                    break;
                case "Pulir":
                    proceso.sandable();
                    break;
                default:
                    System.out.println("Proceso desconocido: " + proceso.getNombre());
            }
        }
    }
}
