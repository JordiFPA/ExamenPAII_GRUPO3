package uce.edu.ec.model;

import jakarta.persistence.*;
import uce.edu.ec.interfaces.IAssembly;
import uce.edu.ec.interfaces.ICut;
import uce.edu.ec.interfaces.IPaint;
import uce.edu.ec.interfaces.ISandable;

@Entity
public class Process implements IAssembly, ICut, IPaint , ISandable {
    private String nombre;
    private Product product;
    private Order order;


    public Process() {
    }

    public Process(String nombre, Product product,Order order) {
        this.nombre = nombre;
        this.product = product;
        this.order = order;
    }

    @Override
    public void cut() {
        System.out.println("Proceso de corte en marcha.");
    }

    @Override
    public void paint() {
        System.out.println("Proceso de pintada en marcha.");
    }

    @Override
    public void assembly() {
        System.out.println("Proceso de ensamble en marcha.");
    }

    @Override
    public void sandable() {
        System.out.println("Proceso de lijado en marcha.");
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
