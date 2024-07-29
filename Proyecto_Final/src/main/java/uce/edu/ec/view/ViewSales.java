package uce.edu.ec.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uce.edu.ec.model.Orden;
import uce.edu.ec.service.OrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ViewSales extends JFrame {

    private final OrderService orderService;
    private final ApplicationContext context;
    private JTable tableSales;
    private DefaultTableModel tableModel;

    @Autowired
    public ViewSales(OrderService orderService, ApplicationContext context) {
        this.orderService = orderService;
        this.context = context;
        initComponents();
        startPeriodicUpdate();
    }

    private void initComponents() {
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID_Orden", "ID_Cliente", "Productos", "Estado"}
        );
        tableSales = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(tableSales);
        tableScrollPane.setPreferredSize(new Dimension(500, 200));
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Logoicon.png"))).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JButton btnClose = new JButton("Cerrar");
        btnClose.addActionListener(e -> {
            FrameAdmin frameAdmin = context.getBean(FrameAdmin.class);
            frameAdmin.setSize(getSize());
            frameAdmin.setLocationRelativeTo(null);
            frameAdmin.setVisible(true);
            dispose();
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(btnClose, BorderLayout.SOUTH);

        add(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventas");
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void loadSales() {
        List<Orden> sales = orderService.getAllOrders()
                .stream()
                .filter(sale -> "Listo".equals(sale.getStatus()))
                .collect(Collectors.toList());

        tableModel.setRowCount(0);
        for (Orden sale : sales) {
            StringBuilder productsBuilder = new StringBuilder();
            sale.getProducts().forEach(product -> productsBuilder.append(product.getName()).append(", "));
            String products = productsBuilder.length() > 0 ? productsBuilder.substring(0, productsBuilder.length() - 2) : "";
            tableModel.addRow(new Object[]{sale.getId(), sale.getCustomer().getId(), products, sale.getStatus()});
        }
    }

    private void startPeriodicUpdate() {
        Timer timer = new Timer(5000, e -> loadSales());
        timer.start();
    }
}
