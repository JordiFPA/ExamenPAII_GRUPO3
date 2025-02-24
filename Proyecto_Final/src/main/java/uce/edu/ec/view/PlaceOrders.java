package uce.edu.ec.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uce.edu.ec.container.Container;
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
import java.util.Objects;

@Component
public class PlaceOrders extends JFrame {

    private final OrderService orderService;
    private final ApplicationContext context;
    @Autowired
    private Container container;

    private JButton btnFabricarPedido, btnEliminarPedido, btnSalir, btnRegresar;
    private JTable tableOrders;
    private DefaultTableModel tableModel;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    private JTextArea textArea;
    private Timer timer;

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
        btnRegresar = new JButton("Regresar");
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Logoicon.png"))).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
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
        jLabel2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        jLabel3.setText(" ");
        jLabel4.setText(" ");
        jLabel5.setText(" ");
        Dimension buttonSize = new Dimension(220, 60);
        btnFabricarPedido.setMinimumSize(buttonSize);
        btnFabricarPedido.setMaximumSize(buttonSize);
        btnFabricarPedido.setPreferredSize(buttonSize);

        btnEliminarPedido.setMinimumSize(buttonSize);
        btnEliminarPedido.setMaximumSize(buttonSize);
        btnEliminarPedido.setPreferredSize(buttonSize);

        btnSalir.setMinimumSize(buttonSize);
        btnSalir.setMaximumSize(buttonSize);
        btnSalir.setPreferredSize(buttonSize);

        btnRegresar.setMinimumSize(buttonSize);
        btnRegresar.setMaximumSize(buttonSize);
        btnRegresar.setPreferredSize(buttonSize);

        Border buttonBorder1 = BorderFactory.createLineBorder(new Color(246, 195, 67), 2);

        btnFabricarPedido.setBackground(Color.WHITE);
        btnFabricarPedido.setOpaque(true);
        btnFabricarPedido.setBorder(buttonBorder1);
        btnFabricarPedido.setForeground(Color.BLACK);
        btnFabricarPedido.addActionListener(e -> {

            String input = JOptionPane.showInputDialog(PlaceOrders.this, "Ingrese el ID de la orden a fabricar:");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    long orderId = Long.parseLong(input.trim());

                    Orden order = orderService.getOrderById(orderId);
                    if (order != null) {

                        JDialog progressDialog = new JDialog(PlaceOrders.this, "Fabricando...", true);
                        progressDialog.setLayout(new BorderLayout());

                        JProgressBar progressBar = new JProgressBar(0, 100);
                        progressBar.setValue(0);
                        progressBar.setStringPainted(true);
                        progressDialog.add(progressBar, BorderLayout.NORTH);

                        JTextArea textArea = new JTextArea();
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        progressDialog.add(scrollPane, BorderLayout.CENTER);

                        progressDialog.setSize(400, 300);
                        progressDialog.setLocationRelativeTo(PlaceOrders.this);

                        SwingUtilities.invokeLater(() -> progressDialog.setVisible(true));

                        new Thread(() -> {
                            try {
                                container.executeManufacturingProcess(orderId,
                                        status -> SwingUtilities.invokeLater(() -> {
                                            textArea.append(status + "\n");
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
        btnEliminarPedido.addActionListener(e -> {
            int selectedRow = tableOrders.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(
                        PlaceOrders.this,
                        "¿Está seguro de que desea eliminar el pedido seleccionado?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    long orderId = (Long) tableModel.getValueAt(selectedRow, 0);
                    orderService.deleteOrder(orderId);
                    loadOrders();
                    JOptionPane.showMessageDialog(PlaceOrders.this, "Pedido eliminado exitosamente.");
                }
            } else {
                JOptionPane.showMessageDialog(PlaceOrders.this, "Por favor, seleccione un pedido para eliminar.");
            }
        });

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

        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setOpaque(true);
        btnRegresar.setBorder(buttonBorder1);
        btnRegresar.setForeground(Color.BLACK);
        btnRegresar.addActionListener(e -> {
            FrameAdmin frameAdmin = context.getBean(FrameAdmin.class);
            frameAdmin.setSize(getSize());
            frameAdmin.setLocationRelativeTo(null);
            frameAdmin.setVisible(true);
            dispose();
        });

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 153));
        mainPanel.setLayout(new BorderLayout());

        tablePanel = new JPanel();
        tablePanel.setBackground(new Color(255, 255, 153));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 153));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(btnFabricarPedido);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnEliminarPedido);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnRegresar);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnSalir);
        buttonPanel.add(Box.createVerticalGlue());

        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(new Color(255, 255, 153));
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(jLabel1);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(jLabel4);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(jLabel3);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(jLabel5);

        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);
        add(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Place Orders");
        setSize(800, 600);
        setLocationRelativeTo(null);

        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadOrders();
            }
        });
        timer.start();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (timer != null) {
                    timer.stop();
                }
            }
        });

        loadOrders();
    }

    private void loadOrders() {
        List<Orden> orders = orderService.getAllOrders();
        tableModel.setRowCount(0);
        for (Orden order : orders) {
            StringBuilder productsBuilder = new StringBuilder();
            for (Product product : order.getProducts()) {
                productsBuilder.append(product.getName()).append(", ");
            }
            String products = productsBuilder.toString();
            if (products.length() > 0) {
                products = products.substring(0, products.length() - 2);
            }
            tableModel.addRow(new Object[]{order.getId(), order.getCustomer().getId(), products, order.getStatus()});
        }
    }
}
