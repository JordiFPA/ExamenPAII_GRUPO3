package uce.edu.ec.model;

import org.springframework.stereotype.Component;
import uce.edu.ec.interfaces.IBuildable;
@Component
public class BuildProcess implements IBuildable {
    @Override
    public void build(Runnable updateProgress) {
        System.out.println("Iniciando corte...");
        try {
            // Simula el tiempo de corte
            Thread.sleep(20000); // 2 segundos para el corte
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateProgress.run();
        System.out.println("Corte realizado.");
    }
}
