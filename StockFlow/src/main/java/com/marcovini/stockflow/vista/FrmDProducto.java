/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to edit this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.vista;

import javax.swing.*;
import java.awt.*;
import com.marcovini.stockflow.dao.ProductoDAO;

/**
 * Ventana de interfaz gráfica para la eliminación de productos en el sistema StockFlow.
 * Permite buscar y dar de baja productos del inventario utilizando criterios flexibles 
 * como ID, Código o Nombre.
 * 
 * @author Marco Almendarez
 * @version 1.0
 */
public class FrmDProducto extends JFrame {
    
    private JLabel idL, codigoL, nombreL, titleL, subtitleL;
    private JTextField id, codigo, nombre;
    private JButton eliminar, limpiar, regresar, listar;
    
    public FrmDProducto(){
        setTitle("Eliminar producto");
        setSize(new Dimension(345, 400));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        titleL = new JLabel("StockFlow");
        titleL.setFont(new Font("Andale Mono", Font.BOLD | Font.ITALIC, 36));
        titleL.setForeground(new Color(0, 0, 255));
        titleL.setBounds(20, 10, 300, 40);
        add(titleL);
        
        subtitleL = new JLabel("Eliminar producto");
        subtitleL.setFont(new Font("Andale Mono", Font.ITALIC, 18));
        subtitleL.setBounds(20, 55, 300, 30);
        add(subtitleL);
        
        idL = new JLabel("ID:");
        idL.setBounds(20, 120, 50, 20);
        add(idL);
        
        id = new JTextField();
        id.setBounds(20, 140, 50, 25);
        add(id);
        
        codigoL = new JLabel("Código:");
        codigoL.setBounds(80, 120, 60, 20);
        add(codigoL);
        
        codigo = new JTextField();
        codigo.setBounds(80, 140, 90, 25);
        add(codigo);
        
        nombreL = new JLabel("Nombre:");
        nombreL.setBounds(185, 120, 80, 20);
        add(nombreL);
        
        nombre = new JTextField();
        nombre.setBounds(185, 140, 125, 25);
        add(nombre);
        
        eliminar = new JButton("Eliminar");
        eliminar.setToolTipText("Eliminar producto");
        eliminar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        eliminar.setBackground(new Color(189, 182, 181));
        eliminar.setBounds(20, 200, 100, 30);
        eliminar.setFocusPainted(false);
        eliminar.addActionListener(e -> {
            String txtId = id.getText().trim();
            String txtCodigo = codigo.getText().trim();
            String txtNombre = nombre.getText().trim();

            if (txtId.isEmpty() && txtCodigo.isEmpty() && txtNombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa al menos el ID, Código o Nombre para eliminar.");
                return;
            }
            
            String criterio = !txtId.isEmpty() ? "ID: " + txtId :
                              !txtCodigo.isEmpty() ? "Código: '" + txtCodigo + "'" :
                              "Nombre: '" + txtNombre + "'";

            int confirmacion = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de eliminar el producto con " + criterio + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                ProductoDAO dao = new ProductoDAO();
                boolean borrado = dao.eliminarProducto(txtId, txtCodigo, txtNombre);

                if (borrado) {
                    limpiar.doClick();
                }
            }
        });
        add(eliminar);
        
        limpiar = new JButton("Limpiar");
        limpiar.setToolTipText("Limpiar campos");
        limpiar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        limpiar.setBackground(new Color(189, 182, 181));
        limpiar.setBounds(130, 200, 100, 30);
        limpiar.setFocusPainted(false);
        limpiar.addActionListener(e -> {
            id.setText("");
            codigo.setText("");
            nombre.setText("");
        });
        add(limpiar);
        
        listar = new JButton();
        ImageIcon listarIcon = new ImageIcon(getClass().getResource("/images/lista.png"));
        listar.setIcon(new ImageIcon(listarIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        listar.setToolTipText("Ver Inventario");
        listar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        listar.setBounds(270, 200, 40, 40);
        listar.setBackground(new Color(189, 182, 181));
        listar.setFocusPainted(false);
        listar.addActionListener(e -> {
            ProductoDAO dao = new ProductoDAO();
            FrmTablas inventario = new FrmTablas(dao.listarProductos(), "Inventario Actual");
            inventario.setVisible(true);
        });
        add(listar);
        
        regresar = new JButton("Regresar");
        ImageIcon regresarIcon = new ImageIcon(getClass().getResource("/images/back.png"));
        regresar.setIcon(new ImageIcon(regresarIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        regresar.setToolTipText("Regresar");
        regresar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        regresar.setBackground(new Color(189, 182, 181));
        regresar.setBounds(20, 310, 120, 40);
        regresar.setFocusPainted(false);
        regresar.addActionListener(e -> {
            this.dispose();
            FrmConfig con = new FrmConfig();
            con.setVisible(true);
        });
        add(regresar);
    }
}