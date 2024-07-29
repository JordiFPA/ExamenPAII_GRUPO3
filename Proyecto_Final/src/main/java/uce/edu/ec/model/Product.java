package uce.edu.ec.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String material;
    @ManyToMany(mappedBy = "products")
    private List<Orden> ordens = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String material) {
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

}