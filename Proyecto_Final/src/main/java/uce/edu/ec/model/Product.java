package uce.edu.ec.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;
    private String name;
    private String material;
    private int cantidad;

    @ManyToMany(mappedBy = "products")
    private List<Orden> ordens = new ArrayList<>();

    public Product() {
    }

    public Product(double price, String name, String material, int cantidad) {
        this.price = price;
        this.name = name;
        this.material = material;
        this.cantidad = cantidad;
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

    public List<Orden> getOrders() {
        return ordens;
    }

    public void setOrders(List<Orden> ordens) {
        this.ordens = ordens;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}