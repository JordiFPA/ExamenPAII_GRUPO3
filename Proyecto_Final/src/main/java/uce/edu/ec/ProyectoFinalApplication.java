package uce.edu.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import uce.edu.ec.view.Principal;


@SpringBootApplication
@ComponentScan({"uce.edu.ec", "uce.edu.ec.view", "uce.edu.ec.container"})
public class ProyectoFinalApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        ApplicationContext context = SpringApplication.run(ProyectoFinalApplication.class, args);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Principal frame = context.getBean(Principal.class);
                frame.setVisible(true);
            }
        });

    }
}
