package uce.edu.ec.model;

import org.springframework.stereotype.Component;
import uce.edu.ec.interfaces.IPolishable;
@Component
public class PolishProcess implements IPolishable {

    @Override
    public void polish(Runnable updateProgress) {
        System.out.println("Iniciando Pulida...");
        try {
            Thread.sleep(20000);// 10 segundos para el corte
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateProgress.run();
        System.out.println("Pulido realizado.");
    }
}
