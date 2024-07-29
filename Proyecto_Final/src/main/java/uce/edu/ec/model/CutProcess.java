package uce.edu.ec.model;

import org.springframework.stereotype.Component;
import uce.edu.ec.interfaces.ICut;

@Component
public class CutProcess implements ICut {

    @Override
    public void cut(Runnable updateProgress) {
        try {
            Thread.sleep(20000); // 2 segundos para el corte
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateProgress.run();
    }
}
