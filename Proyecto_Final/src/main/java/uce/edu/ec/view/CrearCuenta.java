package uce.edu.ec.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uce.edu.ec.container.Container;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uce.edu.ec.container.Container;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CrearCuenta extends JFrame {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private Container container;
    private JLabel jLabel1,jLabel2,jLabel3,jLabel4;
    private JTextField jTextField1,jTextField3;
    private JPasswordField jPasswordField;
    private JButton jButton1;
    private JButton jButton2;
    private JPanel jPanel1;
    private JCheckBox showPasswordCheckBox;

    public CrearCuenta() {
        initComponents();
        setSize(855, 800);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jTextField1 = new JTextField();
        jPasswordField = new JPasswordField();
        jTextField3 = new JTextField();
        jButton1 = new JButton();
        jButton2 = new JButton();
        showPasswordCheckBox = new JCheckBox("Mostrar Contraseña");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(255, 255, 153));

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("CREAR CUENTA");

        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel3.setText("Email:");

        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel4.setText("Contraseña:");

        jTextField1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jTextField3.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        PlainDocument doc = (PlainDocument) jTextField1.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("[a-zA-Z]*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        Dimension textFieldSize = new Dimension(367, 38);
        jTextField1.setPreferredSize(textFieldSize);
        jPasswordField.setPreferredSize(textFieldSize);
        jTextField3.setPreferredSize(textFieldSize);

        // Crear bordes de colores
        Border buttonBorder1 = BorderFactory.createLineBorder(new Color(246, 195, 67), 2);

        jButton1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jButton1.setText("Volver");
        jButton1.setPreferredSize(new Dimension(150, 60));
        jButton1.setBackground(new Color(255, 255, 255));
        jButton1.setOpaque(true);
        jButton1.setBorder(buttonBorder1);
        jButton1.setForeground(Color.BLACK);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                clearFields();
                LoginCustomer loginCustomer = context.getBean(LoginCustomer.class);
                loginCustomer.setVisible(true);
                dispose();
            }
        });

        jButton2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jButton2.setText("Crear");
        jButton2.setPreferredSize(new Dimension(150, 60));
        jButton2.setBackground(new Color(255, 255, 255));
        jButton2.setOpaque(true);
        jButton2.setBorder(buttonBorder1);
        jButton2.setForeground(Color.BLACK);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    validateFields();
                    container.registerCustomer(jTextField1.getText(), jTextField3.getText(), new String(jPasswordField.getPassword()));
                    JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente");

                    // Limpiar los campos de texto después de crear la cuenta
                    clearFields();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    jPasswordField.setEchoChar((char) 0);
                } else {
                    jPasswordField.setEchoChar('*');
                }
            }
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
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel2))
                                                .addGap(32, 32, 32)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jPasswordField, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                                                        .addComponent(jTextField3)
                                                        .addComponent(jTextField1)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(164, 164, 164)
                                                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(74, 74, 74)
                                                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(313, 313, 313)
                                                .addComponent(showPasswordCheckBox)))
                                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel1)
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jPasswordField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                                .addGap(67, 67, 67)
                                .addComponent(showPasswordCheckBox)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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

    private void validateFields() throws Exception {
        if (jTextField1.getText().isBlank() || jPasswordField.getPassword().length == 0 || jTextField3.getText().isBlank()) {
            throw new Exception("Todos los campos son obligatorios");
        }

        if (!jTextField1.getText().matches("[a-zA-Z]+")) {
            throw new Exception("El nombre solo puede contener letras");
        }

        if (!jTextField3.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new Exception("El email no es válido");
        }
    }

    private void clearFields() {
        jTextField1.setText("");
        jPasswordField.setText("");
        jTextField3.setText("");
    }
}