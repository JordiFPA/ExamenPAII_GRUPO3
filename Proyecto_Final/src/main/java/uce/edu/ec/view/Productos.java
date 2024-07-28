package uce.edu.ec.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uce.edu.ec.container.Container;
import uce.edu.ec.model.Product;
import uce.edu.ec.service.ProductService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Component
public class Productos extends JFrame {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private Container container;
    @Autowired
    private ProductService productService;

    private JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1, jLabel2, jLabel3;
    private JScrollPane jScrollPane2;
    private JTable jTable2;
    private JTextField jTextField1;
    private JPanel mainPanel;
    private String producto = "";
    private String material;
    private DefaultTableModel tableModel;

    public Productos() {
        initComponents();
    }

    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Productos a llevar");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        System.out.println("");

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 153));
        getContentPane().add(mainPanel);

        jButton6.setText("jButton6");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        jLabel1.setText("Productos:");


        Border buttonBorder = BorderFactory.createLineBorder(Color.WHITE, 2);
        Border buttonBorder1 = BorderFactory.createLineBorder(new Color(246, 195, 67), 2);


        Dimension buttonSize = new Dimension(200, 50);

        jButton1.setText("SILLA");
        jButton1.setPreferredSize(buttonSize);
        jButton1.setBackground(new Color(255, 255, 255));
        jButton1.setOpaque(true);
        jButton1.setBorder(buttonBorder);
        jButton1.setForeground(Color.BLACK);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                producto = "SILLA";
            }
        });

        jButton2.setText("ESCRITORIO");
        jButton2.setPreferredSize(buttonSize);
        jButton2.setBackground(new Color(255, 255, 255));
        jButton2.setOpaque(true);
        jButton2.setBorder(buttonBorder);
        jButton2.setForeground(Color.BLACK);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                producto = "ESCRITORIO";
            }
        });

        jButton3.setText("ARMARIO");
        jButton3.setPreferredSize(buttonSize);
        jButton3.setBackground(new Color(255, 255, 255));
        jButton3.setOpaque(true);
        jButton3.setBorder(buttonBorder);
        jButton3.setForeground(Color.BLACK);
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                producto = "ARMARIO";
            }
        });

        jButton4.setText("CAMA");
        jButton4.setPreferredSize(buttonSize);
        jButton4.setBackground(new Color(255, 255, 255));
        jButton4.setOpaque(true);
        jButton4.setBorder(buttonBorder);
        jButton4.setForeground(Color.BLACK);
        jButton4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                producto = "CAMA";

            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel2.setText("Seleccionar Material:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"SELECCIONE", "MADERA", "METAL", "PLASTICO"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel3.setText("Cantidad:");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"NOMBRE", "MATERIAL", "CANTIDAD"}
        );

        jTable2.setModel(tableModel);
        jTable2.setUpdateSelectionOnSort(false);
        jScrollPane2.setViewportView(jTable2);

        jButton5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton5.setText("AGREGAR PRODUCTO");
        jButton5.setPreferredSize(buttonSize);
        jButton5.setBackground(new Color(255, 255, 255));
        jButton5.setOpaque(true);
        jButton5.setBorder(buttonBorder1);
        jButton5.setForeground(Color.BLACK);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton7.setText("ELIMINAR PRODUCTO");
        jButton7.setPreferredSize(buttonSize);
        jButton7.setBackground(new Color(255, 255, 255));
        jButton7.setOpaque(true);
        jButton7.setBorder(buttonBorder1);
        jButton7.setForeground(Color.BLACK);

        jButton8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton8.setText("HACER PEDIDO");
        jButton8.setPreferredSize(buttonSize);
        jButton8.setBackground(new Color(255, 255, 255));
        jButton8.setOpaque(true);
        jButton8.setBorder(buttonBorder1);
        jButton8.setForeground(Color.BLACK);
        jButton8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              hacerPedidoActionPerformed(e);
              System.out.print(container.getCurrentOrder().getStatus() + container.getCurrentOrder().getId());
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton9.setText(" VOLVER ");
        jButton9.setPreferredSize(buttonSize);
        jButton9.setBackground(new Color(255, 255, 255));
        jButton9.setOpaque(true);
        jButton9.setBorder(buttonBorder1);
        jButton9.setForeground(Color.BLACK);


        jButton9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameCustomer frameCustomer = context.getBean(FrameCustomer.class);
                frameCustomer.updateOrderTable();
                frameCustomer.setSize(getSize());
                frameCustomer.setLocationRelativeTo(null);
                frameCustomer.setVisible(true);
                dispose();
                PlaceOrders placeOrders = context.getBean(PlaceOrders.class);
                placeOrders.updateOrdersAdmi();

            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(54, 54, 54)
                                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                                .addGap(50, 50, 50)
                                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(64, 64, 64)
                                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                                .addGap(38, 38, 38)
                                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(102, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(16, 16, 16))
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(69, 69, 69)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        material = (String) jComboBox1.getSelectedItem();
    }

    private void agregarProductoActionPerformed(java.awt.event.ActionEvent evt) {
        String cantidad = jTextField1.getText();
        if (!producto.isEmpty() && !material.equals("SELECCIONE") && !cantidad.isEmpty()) {
            tableModel.addRow(new Object[]{producto, material, cantidad});
            producto = "";
            jComboBox1.setSelectedIndex(0);
            jTextField1.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un producto, material y cantidad.");
        }
    }


    private void hacerPedidoActionPerformed(java.awt.event.ActionEvent evt) {
        int rowCount = tableModel.getRowCount();
        if (rowCount > 0) {
            List<Product> productList = new ArrayList<>();
            for (int i = 0; i < rowCount; i++) {
                String nombre = (String) tableModel.getValueAt(i, 0);
                String material = (String) tableModel.getValueAt(i, 1);
                double precio = 0.0; // Ajusta el precio según corresponda
                int cantidad = Integer.parseInt((String) tableModel.getValueAt(i, 2));
                Product product = new Product(precio, nombre, material, cantidad); // Ajusta los parámetros al constructor correcto

                // Si el producto ya existe en la base de datos, obtén su ID
                Product existingProduct = productService.getProductByNameAndMaterial(nombre, material); // Debes crear este método en ProductService y ProductRepository
                if (existingProduct != null) {
                    product.setId(existingProduct.getId());
                } else {
                    product = productService.saveProduct(product); // Guarda el producto y obtiene su ID
                }

                productList.add(product);
            }

            // Crear la orden usando el Container
            try {
                container.createOrder(container.getCustomer().getId(), productList, "Pendiente");
                JOptionPane.showMessageDialog(this, "Pedido realizado exitosamente.");
                tableModel.setRowCount(0); // Limpiar la tabla después de realizar el pedido
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al realizar el pedido: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay productos en la lista para hacer el pedido.");
        }
    }
}
