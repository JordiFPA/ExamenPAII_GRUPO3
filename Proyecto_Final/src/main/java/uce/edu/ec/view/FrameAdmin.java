package uce.edu.ec.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class FrameAdmin extends javax.swing.JFrame {

    @Autowired
    private ApplicationContext context;

    public FrameAdmin() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Border buttonBorder1 = BorderFactory.createLineBorder(new Color(246, 195, 67), 2);

        // Cambiar el color de fondo de la ventana
        Color fondoColor = new Color(255, 255, 153); // Color de fondo deseado
        getContentPane().setBackground(fondoColor);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoProgra.jpg"))); // NOI18N

        // Configurar los botones con el mismo tamaño y apariencia
        Dimension buttonSize = new Dimension(200, 50);

        jButton1.setText("VENTAS");
        jButton1.setPreferredSize(buttonSize);
        jButton1.setBackground(Color.WHITE);
        jButton1.setOpaque(true);
        jButton1.setBorder(buttonBorder1);
        jButton1.setForeground(Color.BLACK);
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewSales viewSales = context.getBean(ViewSales.class);
                viewSales.setSize(getSize());
                viewSales.setLocationRelativeTo(null);
                viewSales.setVisible(true);
                dispose();
            }
        });

        jButton2.setText("PEDIDOS");
        jButton2.setPreferredSize(buttonSize);
        jButton2.setBackground(Color.WHITE);
        jButton2.setOpaque(true);
        jButton2.setBorder(buttonBorder1);
        jButton2.setForeground(Color.BLACK);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlaceOrders placeOrders = context.getBean(PlaceOrders.class);
                placeOrders.setSize(getSize());
                placeOrders.setLocationRelativeTo(null);
                placeOrders.setVisible(true);
                dispose();
            }
        });

        jButton3.setText("SALIR");
        jButton3.setPreferredSize(buttonSize);
        jButton3.setBackground(Color.WHITE);
        jButton3.setOpaque(true);
        jButton3.setBorder(buttonBorder1);
        jButton3.setForeground(Color.BLACK);
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginAdmin loginAdmin = context.getBean(LoginAdmin.class);
                loginAdmin.setSize(getSize());
                loginAdmin.setLocationRelativeTo(null);
                loginAdmin.setVisible(true);
                dispose();
            }
        });

        // Configurar el diseño usando GroupLayout para centrar los botones
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                                .addGap(20, 20, 20))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(300, 300, 300)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
}