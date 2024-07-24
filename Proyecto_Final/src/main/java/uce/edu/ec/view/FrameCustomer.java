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
public class FrameCustomer extends javax.swing.JFrame {
    @Autowired
    private ApplicationContext context;

    public FrameCustomer() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Border buttonBorder = BorderFactory.createLineBorder(Color.WHITE, 2);
        Border buttonBorder1 = BorderFactory.createLineBorder(new Color(246, 195, 67), 2);

        // Cambiar el color de fondo de la ventana
        Color fondoColor = new Color(255, 255, 153); // Color de fondo deseado
        getContentPane().setBackground(fondoColor);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoProgra.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setText("Hola");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Aqui puedes ver tu Historial de pedidos o realizar un Nuevo Pedido");

        Dimension buttonSize = new Dimension(300, 60); // Aumentar el tamaño preferido de los botones

        jButton1.setText("Historial de Pedidos");
        jButton1.setPreferredSize(buttonSize);
        jButton1.setMinimumSize(buttonSize);
        jButton1.setBackground(new Color(255, 255, 255));
        jButton1.setOpaque(true);
        jButton1.setBorder(buttonBorder);
        jButton1.setForeground(Color.BLACK);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Realizar Pedido");
        jButton2.setPreferredSize(buttonSize);
        jButton2.setMinimumSize(buttonSize);
        jButton2.setBackground(new Color(255, 255, 255));
        jButton2.setOpaque(true);
        jButton2.setBorder(buttonBorder1);
        jButton2.setForeground(Color.BLACK);
        jButton2.addActionListener(evt -> {
            Productos productos = context.getBean(Productos.class);
            productos.setSize(getSize());
            productos.setLocationRelativeTo(null);
            productos.setVisible(true);
            dispose();
        });

        jButton3.setText("SALIR");
        jButton3.setPreferredSize(buttonSize);
        jButton3.setMinimumSize(buttonSize);
        jButton3.setBackground(new Color(255, 255, 255));
        jButton3.setOpaque(true);
        jButton3.setBorder(buttonBorder1);
        jButton3.setForeground(Color.BLACK);
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginCustomer loginCustomer = context.getBean(LoginCustomer.class);
                loginCustomer.setSize(getSize());
                loginCustomer.setLocationRelativeTo(null);
                loginCustomer.setVisible(true);
                dispose();
            }
        });

        // Configurar el diseño usando el panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(fondoColor);
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        getContentPane().add(mainPanel);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(20)
                        .addComponent(jLabel2)
                        .addGap(20)
                        .addComponent(jLabel3)
                        .addGap(40)
                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameCustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration
}
