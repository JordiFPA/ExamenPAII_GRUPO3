package uce.edu.ec.model;

import org.springframework.stereotype.Component;
import uce.edu.ec.interfaces.IPaint;
@Component
public class PaintProcess implements IPaint {

    @Override
    public void paint(Runnable updateProgress) {
        try {

            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateProgress.run();
    }
}
