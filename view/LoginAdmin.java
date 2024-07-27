package uce.edu.ec.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;
import uce.edu.ec.service.AdministratorService;
import uce.edu.ec.model.Administrator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class LoginAdmin extends JFrame {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AdministratorService administratorService;

    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPasswordField jPasswordField1;
    private JTextField jTextField1;
    private JButton jButton3;

    public LoginAdmin() throws HeadlessException {
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jButton2 = new JButton();
        jTextField1 = new JTextField();
        jLabel4 = new JLabel();
        jPasswordField1 = new JPasswordField();
        jButton3 = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(255, 255, 153));

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("BIENVENIDO");

        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 18)); // NOI18N
        jLabel2.setText("CONTRASEÑA:");

        // Crear bordes de colores
        Border buttonBorder1 = BorderFactory.createLineBorder(new Color(246, 195, 67), 2);

        jButton2.setFont(new Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        jButton2.setText("Ingresar");
        jButton2.setPreferredSize(new Dimension(150, 60));
        jButton2.setBackground(new Color(255, 255, 255));
        jButton2.setOpaque(true); // Asegura que el fondo sea visible
        jButton2.setBorder(buttonBorder1);
        jButton2.setForeground(Color.BLACK);
        jButton2.addActionListener(evt -> {
            String name = jTextField1.getText();
            String password = new String(jPasswordField1.getPassword());

            // Verificar si los campos están llenos
            if (name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los datos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Administrator admin = administratorService.getAdministratorByNameAndPassword(name, password);
                if (admin != null) {
                    FrameAdmin frameAdmin = context.getBean(FrameAdmin.class);
                    frameAdmin.setSize(getSize());
                    frameAdmin.setLocationRelativeTo(null);
                    frameAdmin.setVisible(true);
                    // Limpiar los campos de entrada
                    jTextField1.setText("");
                    jPasswordField1.setText("");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Credenciales incorrectas.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de autenticación", JOptionPane.ERROR_MESSAGE);
            }
        });

        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 18)); // NOI18N
        jLabel4.setText("USUARIO:");

        jButton3.setFont(new Font("Segoe UI", Font.BOLD, 18)); // NOI18N
        jButton3.setText("Volver");
        jButton3.setPreferredSize(new Dimension(150, 60));
        jButton3.setBackground(new Color(255, 255, 255));
        jButton3.setOpaque(true); // Asegura que el fondo sea visible
        jButton3.setBorder(buttonBorder1);
        jButton3.setForeground(Color.BLACK);
        jButton3.addActionListener(evt -> {
            Principal principal = context.getBean(Principal.class);
            principal.setSize(getSize());
            principal.setLocationRelativeTo(null);
            principal.setVisible(true);
            dispose();
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(167, 167, 167)
                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(113, 113, 113)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel2))
                                                .addGap(32, 32, 32)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                                                        .addComponent(jPasswordField1)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(164, 164, 164)
                                                .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(51, 51, 51)
                                                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel1)
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(83, 83, 83))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null); // Centra la ventana
    }
}

