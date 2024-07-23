package uce.edu.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import uce.edu.ec.model.Customer;
import uce.edu.ec.model.Order;
import uce.edu.ec.model.Process;
import uce.edu.ec.model.Product;
import uce.edu.ec.view.Principal;

import java.util.ArrayList;
import java.util.Arrays;


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
