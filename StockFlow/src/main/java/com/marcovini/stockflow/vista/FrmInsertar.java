/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.vista;

import javax.swing.*;
import java.awt.*;
import com.marcovini.stockflow.dao.ProductoDAO;

/**
 * Ventana de interfaz gráfica para el registro e inserción de nuevos productos en el inventario de StockFlow.
 * Permite capturar los datos principales del producto (código, nombre, precio, stock y categoría),
 * validando los tipos de datos numéricos e interactuando de forma directa con la capa de datos (ProductoDAO).
 * 
 * @author Marco Almendarez
 * @version 1.0
 */
public class FrmInsertar extends JFrame {
    
    private JLabel idL, codigoL, nombreL, precioL, stockL, categoriaL, titleL, subtitleL;
    private JTextField id, producto, codigo, nombre, precio, stock, categoria;
    private JButton insertar, limpiar, regresar, listar;
    
    private JLabel cat, cat1, cat2, cat3;
    
    /**
     * Constructor de la clase FrmInsertar.
     * Inicializa y posiciona los elementos visuales de la interfaz de registro,
     * configura la guía de categorías disponibles y define las acciones de los botones 
     * para insertar, limpiar campos, listar inventario y navegar al menú principal.
     */
    public FrmInsertar(){
        setTitle("Agregar producto");
        setSize(new Dimension(700,400));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        titleL = new JLabel("StockFlow");
        titleL.setFont(new Font("Andale Mono", Font.BOLD | Font.ITALIC, 36));
        titleL.setForeground(new Color(0, 0, 255));
        titleL.setBounds(20, 10, 300, 40);
        add(titleL);
        
        subtitleL = new JLabel("Agrega un producto");
        subtitleL.setFont(new Font("Andale Mono", Font.ITALIC, 18));
        subtitleL.setBounds(20, 55, 300, 30);
        add(subtitleL);
        
        idL = new JLabel("ID:");
        idL.setBounds(20,120,50,20);
        add(idL);
        
        id = new JTextField();
        id.setBounds(20,140,50,25);
        add(id);
        
        codigoL = new JLabel("Codigo:");
        codigoL.setBounds(80,120,50,20);
        add(codigoL);
        
        codigo = new JTextField();
        codigo.setBounds(80,140,100,25);
        add(codigo);
        
        nombreL = new JLabel("Nombre:");
        nombreL.setBounds(190,120,50,20);
        add(nombreL);
        
        nombre = new JTextField();
        nombre.setBounds(190,140,120,25);
        add(nombre);
        
        precioL = new JLabel("Precio:");
        precioL.setBounds(320,120,50,20);
        add(precioL);
        
        precio = new JTextField();
        precio.setBounds(320,140,60,25);
        add(precio);
        
        stockL = new JLabel("Stock:");
        stockL.setBounds(390,120,50,20);
        add(stockL);
        
        stock = new JTextField();
        stock.setBounds(390,140,50,25);
        add(stock);
        
        categoriaL = new JLabel("ID Categoria:");
        categoriaL.setBounds(450,120,80,20);
        add(categoriaL);
        
        categoria = new JTextField();
        categoria.setBounds(450,140,80,25);
        add(categoria);
        
        cat = new JLabel("Categorias:");
        cat.setBounds(560,120,100,20);
        add(cat);
        
        cat1 = new JLabel("> 1 General");
        cat1.setBounds(560,140,100,20);
        add(cat1);
        
        cat2 = new JLabel("> 2 Electronica");
        cat2.setBounds(560,160,100,20);
        add(cat2);
        
        cat3 = new JLabel("> 3 Bebidas");
        cat3.setBounds(560,180,100,20);
        add(cat3);
        
        insertar = new JButton("Agregar");
        insertar.setToolTipText("Agregar");
        insertar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        insertar.setBackground(new Color(189, 182, 181));
        insertar.setBounds(20, 200, 100, 30);
        insertar.setFocusPainted(false);
        insertar.addActionListener(e -> {
            try {
                String codigoS = codigo.getText().trim();
                String nombreS = nombre.getText().trim();
                
                if(codigoS.isEmpty() || nombreS.isEmpty() || precio.getText().isEmpty() || stock.getText().isEmpty() || categoria.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Por favor, llena todos los campos para registrar el producto.");
                    return;
                }
                
                double precioS = Double.parseDouble(precio.getText());
                int stockS = Integer.parseInt(stock.getText());
                int idCategoriaS = Integer.parseInt(categoria.getText());
                
                ProductoDAO dao = new ProductoDAO();
                boolean exito = dao.insertar(codigoS, nombreS, precioS, stockS, idCategoriaS);
                
                if(exito){
                    id.setText(""); 
                    codigo.setText("");
                    nombre.setText("");
                    precio.setText("");
                    stock.setText("");
                    categoria.setText("");
                
                    codigo.requestFocus(); 
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Asegúrate de ingresar solo números válidos en Precio, Stock y Categoría.");
            }
        });
        add(insertar);
        
        limpiar = new JButton("Limpiar");
        limpiar.setToolTipText("Limpiar");
        limpiar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        limpiar.setBackground(new Color(189, 182, 181));
        limpiar.setBounds(130, 200, 100, 30);
        limpiar.setFocusPainted(false);
        limpiar.addActionListener(e -> {
            id.setText("");
            codigo.setText("");
            nombre.setText("");
            precio.setText("");
            stock.setText("");
            categoria.setText("");
        });
        add(limpiar);
        
        listar = new JButton();
        ImageIcon listarIcon = new ImageIcon(getClass().getResource("/images/lista.png"));
        listar.setIcon(new ImageIcon(listarIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        listar.setToolTipText("Ver inventario");
        listar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        listar.setBounds(490, 200, 40, 40);
        listar.setBackground(new Color(189, 182, 181));
        listar.setFocusPainted(false);
        listar.addActionListener(e -> {
            ProductoDAO dao = new ProductoDAO();
            FrmTablas inventario = new FrmTablas(dao.listarProductos(), "Inventario");
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
            FrmMenu menu = new FrmMenu();
            menu.setVisible(true);
        });
        add(regresar);
    }    
}