package uce.edu.ec.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uce.edu.ec.container.Container;
import uce.edu.ec.model.Customer;
import uce.edu.ec.model.ManufacturingProcess;
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
public class PlaceOrders extends JFrame {

    private final OrderService orderService;
    private final ApplicationContext context;
    @Autowired
    private Container container;

    private JButton btnFabricarPedido, btnEliminarPedido, btnSalir;
    private JTable tableOrders;
    private DefaultTableModel tableModel;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5;
    private JTextArea textArea;

    @Autowired
    public PlaceOrders(OrderService orderService, ApplicationContext context) {
        this.orderService = orderService;
        this.context = context;
        initComponents();
    }

    private void initComponents() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        btnFabricarPedido = new JButton("Mandar a Fabricar");
        btnEliminarPedido = new JButton("Eliminar Pedido");
        btnSalir = new JButton("Salir");
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();

        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID_Orden", "ID_Cliente", "Productos", "Estado"}
        );
        tableOrders = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(tableOrders);
        tableScrollPane.setPreferredSize(new Dimension(500, 200));
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/logoProgra.jpg")));
        jLabel2.setText("LISTA DE PEDIDOS");
        jLabel2.setBorder(BorderFactory.createEtchedBorder());

        jLabel3.setText(" ");
        jLabel4.setText(" ");
        jLabel5.setText(" ");

        Dimension buttonSize = new Dimension(220, 60); // Ajustar tamaño aquí
        btnFabricarPedido.setMinimumSize(buttonSize);
        btnFabricarPedido.setMaximumSize(buttonSize);
        btnFabricarPedido.setPreferredSize(buttonSize);

        btnEliminarPedido.setMinimumSize(buttonSize);
        btnEliminarPedido.setMaximumSize(buttonSize);
        btnEliminarPedido.setPreferredSize(buttonSize);

        btnSalir.setMinimumSize(buttonSize);
        btnSalir.setMaximumSize(buttonSize);
        btnSalir.setPreferredSize(buttonSize);

        Border buttonBorder1 = BorderFactory.createLineBorder(new Color(246, 195, 67), 2);

        btnFabricarPedido.setBackground(Color.WHITE);
        btnFabricarPedido.setOpaque(true);
        btnFabricarPedido.setBorder(buttonBorder1);
        btnFabricarPedido.setForeground(Color.BLACK);
        btnFabricarPedido.addActionListener(e -> {
            // Pedir el ID de la orden
            String input = JOptionPane.showInputDialog(PlaceOrders.this, "Ingrese el ID de la orden a fabricar:");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    long orderId = Long.parseLong(input.trim());
                    // Obtener la orden
                    Orden order = orderService.getOrderById(orderId);
                    if (order != null) {
                        // Crear y mostrar el cuadro de diálogo de progreso en el hilo de eventos de Swing
                        JDialog progressDialog = new JDialog(PlaceOrders.this, "Fabricando...", true);
                        progressDialog.setLayout(new BorderLayout());

                        // Crear y configurar la barra de progreso
                        JProgressBar progressBar = new JProgressBar(0, 100);
                        progressBar.setValue(0);
                        progressBar.setStringPainted(true);
                        progressDialog.add(progressBar, BorderLayout.NORTH);

                        // Configurar el JTextArea
                        textArea = new JTextArea();
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        progressDialog.add(scrollPane, BorderLayout.CENTER);

                        progressDialog.setSize(400, 300);
                        progressDialog.setLocationRelativeTo(PlaceOrders.this);

                        // Mostrar el cuadro de diálogo de progreso
                        SwingUtilities.invokeLater(() -> progressDialog.setVisible(true));

                        // Ejecutar el proceso de fabricación en un hilo separado
                        new Thread(() -> {
                            try {
                                container.executeManufacturingProcess(orderId,
                                        status -> SwingUtilities.invokeLater(() -> {
                                            textArea.append(status + "\n");
                                            // Actualizar la barra de progreso aquí si es necesario
                                            int progressValue = progressBar.getValue() + 10; // Ejemplo de incremento
                                            progressBar.setValue(Math.min(progressValue, 100));
                                        }),
                                        progressBar);


                            } catch (Exception ex) {
                                SwingUtilities.invokeLater(() -> {
                                    textArea.append("Error en el proceso de fabricación: " + ex.getMessage() + "\n");
                                    progressDialog.dispose();
                                });
                            }
                        }).start();
                    } else {
                        JOptionPane.showMessageDialog(PlaceOrders.this, "Orden no encontrada.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PlaceOrders.this, "ID de orden inválido. Por favor ingrese un número válido.");
                }
            }
        });

        btnEliminarPedido.setBackground(Color.WHITE);
        btnEliminarPedido.setOpaque(true);
        btnEliminarPedido.setBorder(buttonBorder1);
        btnEliminarPedido.setForeground(Color.BLACK);

        btnSalir.setBackground(Color.WHITE);
        btnSalir.setOpaque(true);
        btnSalir.setBorder(buttonBorder1);
        btnSalir.setForeground(Color.BLACK);
        btnSalir.addActionListener(e -> {
            LoginAdmin loginAdmin = context.getBean(LoginAdmin.class);
            loginAdmin.setSize(getSize());
            loginAdmin.setLocationRelativeTo(null);
            loginAdmin.setVisible(true);
            dispose();
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
        buttonPanel.add(btnFabricarPedido);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnEliminarPedido);
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

        loadOrders(); // Cargar todos los pedidos al iniciar
    }

    private void loadOrders() {
        List<Orden> orders = orderService.getAllOrders();
        tableModel.setRowCount(0); // Limpiar tabla

        for (Orden order : orders) {
            String productNames = order.getProducts().stream()
                    .map(Product::getName)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Ninguno");
            tableModel.addRow(new Object[]{
                    order.getId(),
                    order.getCustomer().getId(), // Asumiendo que `Orden` tiene un método `getCustomer` para obtener el cliente
                    productNames,
                    order.getStatus()
            });
        }
    }
}
