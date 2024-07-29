package uce.edu.ec.model;

import org.springframework.stereotype.Component;
import uce.edu.ec.interfaces.IPolishable;
@Component
public class PolishProcess implements IPolishable {

    @Override
    public void polish(Runnable updateProgress) {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateProgress.run();

    }
}
