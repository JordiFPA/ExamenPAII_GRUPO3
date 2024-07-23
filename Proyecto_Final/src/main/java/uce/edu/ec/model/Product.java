package uce.edu.ec.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private double price;
    @Column
    private String material;
    @OneToMany(mappedBy = "product")
    private List<Order> order;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Process> procesos;

    public Product() {
    }

    public Product(double price, String name, String material) {
        this.price = price;
        this.name = name;
        this.id = id;
        this.material = material;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public List<Process> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<Process> procesos) {
        this.procesos = procesos;
    }
}
