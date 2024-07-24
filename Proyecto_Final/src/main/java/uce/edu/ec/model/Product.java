package uce.edu.ec.model;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    private int material;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    public Product() {
    }

    public Product(double price, String name, int material) {
        this.price = price;
        this.name = name;
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

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}