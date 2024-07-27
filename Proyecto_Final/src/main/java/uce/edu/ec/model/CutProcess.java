package uce.edu.ec.model;

import org.springframework.stereotype.Component;
import uce.edu.ec.interfaces.ICut;

@Component
public class CutProcess implements ICut {

    @Override
    public void cut(Runnable updateProgress) {
        System.out.println("Iniciando corte...");
        try {
            // Simula el tiempo de corte
            Thread.sleep(2000); // 2 segundos para el corte
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateProgress.run();
        System.out.println("Corte realizado.");
    }
}
