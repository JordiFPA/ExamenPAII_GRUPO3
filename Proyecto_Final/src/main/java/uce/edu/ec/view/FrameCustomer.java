package uce.edu.ec.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uce.edu.ec.model.Customer;
import uce.edu.ec.model.Orden;
import uce.edu.ec.model.Product;
import uce.edu.ec.service.OrderService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class FrameCustomer extends JFrame {

    private final OrderService orderService;
    private final ApplicationContext context;

    private JButton btnRealizarPedido, btnSalir;
    private JTable tableOrders;
    private DefaultTableModel tableModel;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;

    private Customer currentCustomer; // Campo para almacenar el cliente actual

    @Autowired
    public FrameCustomer(OrderService orderService, ApplicationContext context) {
        this.orderService = orderService;
        this.context = context;
        initComponents();
    }

    private void initComponents() {
        // Inicialización de botones
        btnRealizarPedido = new JButton("Realizar Pedido");
        btnSalir = new JButton("Salir");

        // Inicialización de etiquetas
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();

        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Cliente", "Productos", "Estado"}
        );
        tableOrders = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(tableOrders);

        // Establecer tamaño preferido para el JScrollPane con altura menor
        tableScrollPane.setPreferredSize(new Dimension(500, 200)); // Ajustar tamaño aquí

        // Configuración de jLabel1 con la imagen
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/logoProgra.jpg"))); // Asegúrate de que esta ruta sea correcta

        // Configuración de jLabel2 y jLabel3
        jLabel2.setText("Hola");
        jLabel2.setBorder(BorderFactory.createEtchedBorder());

        jLabel3.setText("Aquí puedes ver tu historial de pedidos o realizar un nuevo pedido");

        // Configuración de jLabel4 y jLabel5
        jLabel4.setText(" ");
        jLabel5.setText(" ");

        // Hacer que los botones sean un poco más grandes
        Dimension buttonSize = new Dimension(220, 60); // Ajustar tamaño aquí
        btnRealizarPedido.setMinimumSize(buttonSize);
        btnRealizarPedido.setMaximumSize(buttonSize);
        btnRealizarPedido.setPreferredSize(buttonSize);

        btnSalir.setMinimumSize(buttonSize);
        btnSalir.setMaximumSize(buttonSize);
        btnSalir.setPreferredSize(buttonSize);

        Border buttonBorder1 = BorderFactory.createLineBorder(new Color(246, 195, 67), 2);

        btnRealizarPedido.setBackground(Color.WHITE);
        btnRealizarPedido.setOpaque(true);
        btnRealizarPedido.setBorder(buttonBorder1);
        btnRealizarPedido.setForeground(Color.BLACK);
        btnRealizarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productos productos = context.getBean(Productos.class);
                productos.setSize(getSize());
                productos.setLocationRelativeTo(null);
                productos.setVisible(true);
                dispose();
            }
        });

        btnSalir.setBackground(Color.WHITE);
        btnSalir.setOpaque(true);
        btnSalir.setBorder(buttonBorder1);
        btnSalir.setForeground(Color.BLACK);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginCustomer loginCustomer = context.getBean(LoginCustomer.class);
                loginCustomer.setSize(getSize());
                loginCustomer.setLocationRelativeTo(null);
                loginCustomer.setVisible(true);
                dispose();
            }
        });

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 153));
        mainPanel.setLayout(new BorderLayout());

        // Panel para la tabla
        tablePanel = new JPanel();
        tablePanel.setBackground(new Color(255, 255, 153)); // Coincidir con el color de mainPanel
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        // Panel para los botones
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 153)); // Coincidir con el color de mainPanel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalGlue()); // Añadir espacio arriba
        buttonPanel.add(btnRealizarPedido);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnSalir);
        buttonPanel.add(Box.createVerticalGlue()); // Añadir espacio abajo

        // Agregar jLabels a un panel adicional
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(new Color(255, 255, 153)); // Coincidir con el color de mainPanel
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(jLabel1);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre imagen y texto
        labelPanel.add(jLabel2);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre etiquetas
        labelPanel.add(jLabel4);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre etiquetas
        labelPanel.add(jLabel3);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre etiquetas
        labelPanel.add(jLabel5);

        // Agregar los paneles a mainPanel
        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        getContentPane().add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    // Método para actualizar la información del cliente
    public void updateCustomerInfo(Customer customer) {
        this.currentCustomer = customer; // Establecer el cliente actual
        jLabel2.setText("Hola " + customer.getName());
        loadOrders(); // Cargar las órdenes del cliente
    }

    private void loadOrders() {
        if (currentCustomer == null) {
            JOptionPane.showMessageDialog(this, "No hay cliente autenticado.");
            return;
        }

        List<Orden> orders = orderService.getOrdersByCustomer(currentCustomer.getId());
        tableModel.setRowCount(0); // Limpiar tabla

        for (Orden order : orders) {
            String productNames = order.getProducts().stream()
                    .map(Product::getName)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Ninguno");
            tableModel.addRow(new Object[]{
                    order.getId(),
                    currentCustomer.getName(),
                    productNames,
                    order.getStatus()
            });
        }
    }

    private void refreshOrders() {
        if (currentCustomer == null) {
            JOptionPane.showMessageDialog(this, "No hay cliente autenticado.");
            return;
        }

        // Obtener las órdenes del cliente actual
        List<Orden> orders = orderService.getOrdersByCustomer(currentCustomer.getId());

        // Limpiar la tabla para actualizar los datos
        tableModel.setRowCount(0);

        // Añadir las órdenes actualizadas a la tabla
        for (Orden order : orders) {
            String productNames = order.getProducts().stream()
                    .map(Product::getName)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Ninguno");
            tableModel.addRow(new Object[]{
                    order.getId(),
                    currentCustomer.getName(),
                    productNames,
                    order.getStatus()
            });
        }
    }

}
