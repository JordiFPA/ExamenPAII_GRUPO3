package uce.edu.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import uce.edu.ec.view.Principal;

@SpringBootApplication
public class ProyectoFinalApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        ApplicationContext context = SpringApplication.run(ProyectoFinalApplication.class, args);
        java.awt.EventQueue.invokeLater(() -> {
            Principal frame = context.getBean(Principal.class);
            frame.setVisible(true);
        });
    }

}
