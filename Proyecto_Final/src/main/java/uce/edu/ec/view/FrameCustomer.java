package uce.edu.ec.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uce.edu.ec.container.Container;
import uce.edu.ec.interfaces.Observer;
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
public class FrameCustomer extends JFrame implements Observer {

    private final OrderService orderService;
    private final ApplicationContext context;
    private final Container container;

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

    private Customer currentCustomer;
    private JTextArea statusTextArea;
    private Timer statusUpdateTimer;

    @Autowired
    public FrameCustomer(OrderService orderService, ApplicationContext context, Container container) {
        this.orderService = orderService;
        this.container = container;
        container.addObserver(this);
        this.context = context;

        initComponents();
        startStatusUpdateTimer();
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
        tableScrollPane.setPreferredSize(new Dimension(500, 200)); // Ajustar tamaño aquí
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/logoProgra.jpg"))); // Asegúrate de que esta ruta sea correcta
        jLabel2.setText("Hola");
        jLabel2.setBorder(BorderFactory.createEtchedBorder());
        jLabel2.setFont(new Font("Times New Roman", Font.BOLD, 18));

        jLabel3.setText("Aquí puedes ver tu historial de pedidos o realizar un nuevo pedido");
        jLabel3.setFont(new Font("Times New Roman", Font.BOLD, 18));
        jLabel5.setText(" ");
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

        // Inicializar JTextArea y JScrollPane para los mensajes de estado
        statusTextArea = new JTextArea(10, 40);
        statusTextArea.setEditable(false);
        JScrollPane statusScrollPane = new JScrollPane(statusTextArea);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 153));
        mainPanel.setLayout(new BorderLayout());


        tablePanel = new JPanel();
        tablePanel.setBackground(new Color(255, 255, 153));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        tablePanel.add(statusScrollPane, BorderLayout.SOUTH);
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 153));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalGlue()); // Añadir espacio arriba
        buttonPanel.add(btnRealizarPedido);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnSalir);
        buttonPanel.add(Box.createVerticalGlue());

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

        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        getContentPane().add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }


    private void startStatusUpdateTimer() {
        statusUpdateTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrderTable();
                updateOrderStatusMessages();
            }
        });
        statusUpdateTimer.start();
    }

    // Método para actualizar la información del cliente
    public void updateCustomerInfo(Customer customer) {
        this.currentCustomer = customer; // Establecer el cliente actual
        jLabel2.setText("Hola " + customer.getName());
        loadOrders(); //
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

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void updateOrderTable() {
        if (currentCustomer != null) {
            // Obtener los pedidos del cliente actual
            List<Orden> orders = orderService.getOrdersByCustomer(currentCustomer.getId());

            // Limpiar la tabla
            tableModel.setRowCount(0);

            for (Orden order : orders) {
                String productNames = order.getProducts().stream()
                        .map(Product::getName)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("Ninguno");
                tableModel.addRow(new Object[]{
                        order.getId(),
                        currentCustomer.getName(), // Nombre del cliente actual
                        productNames,
                        order.getStatus()
                });
            }
        } else {
            // Manejo del caso donde el cliente actual es null
            tableModel.setRowCount(0);
            tableModel.addRow(new Object[]{
                    "Error", "No hay cliente actual", "", ""
            });
        }
    }

    @Override
    public void update(String message) {
        updateOrderStatusMessages();
        statusTextArea.append(message + "\n");
    }

    private void updateOrderStatusMessages() {
        if (currentCustomer != null) {
            List<Orden> orders = orderService.getOrdersByCustomer(currentCustomer.getId());
            StringBuilder messageBuilder = new StringBuilder();
            for (Orden order : orders) {
                switch (order.getStatus()) {
                    case "Pendiente":
                        messageBuilder.append("Pedido ID: ").append(order.getId())
                                .append(" - Su pedido está a la espera de ser fabricado.\n");
                        break;
                    case "Fabricando":
                        messageBuilder.append("Pedido ID: ").append(order.getId())
                                .append(" - Su pedido ya empezó a fabricarse...\n");
                        break;
                    case "Listo":
                        messageBuilder.append("Pedido ID: ").append(order.getId())
                                .append(" - Su pedido se fabricó correctamente, muchas gracias.\n");
                        break;
                    default:
                        messageBuilder.append("Pedido ID: ").append(order.getId())
                                .append(" - Estado desconocido: ").append(order.getStatus()).append("\n");
                        break;
                }
            }
            statusTextArea.setText(messageBuilder.toString());
        } else {
            statusTextArea.setText("No hay cliente autenticado.");
        }
    }
}
