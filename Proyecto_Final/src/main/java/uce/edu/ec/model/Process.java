package uce.edu.ec.model;

import uce.edu.ec.interfaces.IAssembly;
import uce.edu.ec.interfaces.ICut;
import uce.edu.ec.interfaces.IPaint;
import uce.edu.ec.interfaces.ISandable;

public class Process implements IAssembly, ICut, IPaint , ISandable {
    private String nombre;

    public Process(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void assembly() {

    }

    @Override
    public void sandable() {

    }

    @Override
    public void cut(Runnable updateProgress) {
        System.out.println("Proceso de corte en marcha.");
    }

    @Override
    public void paint(Runnable updateProgress) {

    }
}
