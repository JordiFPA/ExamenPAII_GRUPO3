package uce.edu.ec.model;

import org.springframework.stereotype.Component;
import uce.edu.ec.interfaces.IPaint;
@Component
public class PaintProcess implements IPaint {

    @Override
    public void paint(Runnable updateProgress) {
        try {
            // Simula el tiempo de corte
            Thread.sleep(10000); // 10 segundos para el corte
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateProgress.run();
    }
}
