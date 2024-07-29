package uce.edu.ec.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

@Component
public class Principal extends JFrame {

    @Autowired
    private ApplicationContext context;

    public Principal() {
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jPanel2 = new JPanel();
        jButton1 = new JButton();
        jButton2 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Logoicon.png"))).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Establecer colores
        Color primaryColor = new Color(246, 195, 67); // Miel
        Color secondaryColor = new Color(255, 255, 153); // Fondo

        jPanel1.setForeground(primaryColor);

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/logoProgra.jpg"))); // NOI18N

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
        );

        jPanel2.setBackground(secondaryColor);

        jButton1.setIcon(new ImageIcon(getClass().getResource("/cliente.png"))); // NOI18N
        jButton1.setText("");
        jButton1.setBorder(BorderFactory.createLineBorder(primaryColor, 4)); // Borde marrón con grosor de 3 píxeles
        jButton1.setBackground(primaryColor);
        jButton1.setForeground(secondaryColor);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LoginCustomer loginCustomer = context.getBean(LoginCustomer.class);
                loginCustomer.setSize(getSize());
                loginCustomer.setLocationRelativeTo(null);
                loginCustomer.setVisible(true);
                // No llamar a dispose() para mantener la ventana principal abierta
            }
        });

        jButton2.setIcon(new ImageIcon(getClass().getResource("/admin.jpg"))); // NOI18N
        jButton2.setText("");
        jButton2.setBorder(BorderFactory.createLineBorder(primaryColor, 4)); // Borde marrón con grosor de 3 píxeles
        jButton2.setBackground(primaryColor);
        jButton2.setForeground(secondaryColor);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginAdmin loginAdmin = context.getBean(LoginAdmin.class);
                loginAdmin.setSize(getSize());
                loginAdmin.setLocationRelativeTo(null);
                loginAdmin.setVisible(true);
                // No llamar a dispose() para mantener la ventana principal abierta
            }
        });

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(200, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null); // Centra la ventana
    }

    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
}
