/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de configuración del sistema StockFlow.
 * Permite acceder a los módulos de gestión como agregar usuarios, 
 * eliminar usuarios y eliminar productos, además de gestionar la navegación de retorno al menú principal.
 * 
 * @author Marco Almendarez
 * @version 1.0
 */
public class FrmConfig extends JFrame{
    
    private JLabel newUserL, delUserL, delPrL, titleL, subtitleL;
    private JButton newUser, delUser, delPr, regresar;

    /**
     * Constructor de la clase FrmConfig.
     * Inicializa los componentes gráficos, define las propiedades de la ventana 
     * y configura los eventos de acción y efectos visuales para los botones de configuración.
     */
    public FrmConfig(){
        setTitle("Configuracion");
        setSize(new Dimension(700,400));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        titleL = new JLabel("StockFlow", SwingConstants.CENTER);
        titleL.setFont(new Font("Andale Mono", Font.BOLD | Font.ITALIC, 36));
        titleL.setForeground(new Color(0, 0, 255));
        titleL.setBounds(200, 25, 300, 40);
        add(titleL);

        subtitleL = new JLabel("Configuracion", SwingConstants.CENTER);
        subtitleL.setFont(new Font("Andale Mono", Font.ITALIC, 18));
        subtitleL.setBounds(200, 65, 300, 30);
        add(subtitleL);

        // Seccion Agregar Usuario
        newUser = new JButton();
        ImageIcon newUserIcon = new ImageIcon(getClass().getResource("/images/newUser.png"));
        newUser.setIcon(new ImageIcon(newUserIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        newUser.setToolTipText("Agregar Usuario");
        newUser.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        newUser.setBounds(125, 120, 100, 100);
        newUser.setBackground(new Color(189, 182, 181));
        newUser.setFocusPainted(false);
        newUser.addActionListener(e -> {
            FrmAUsuario newUser = new FrmAUsuario();
            newUser.setVisible(true);
            this.dispose();
        });
        add(newUser);
        
        newUserL = new JLabel("Agregar Usuario", SwingConstants.CENTER);
        newUserL.setBounds(110, 220, 130, 30);
        add(newUserL);

        // Seccion Eliminar Usuario
        delUser = new JButton();
        ImageIcon delUserIcon = new ImageIcon(getClass().getResource("/images/delUser.png"));
        delUser.setIcon(new ImageIcon(delUserIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        delUser.setToolTipText("Eliminar Usuario");
        delUser.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        delUser.setBounds(300, 120, 100, 100);
        delUser.setBackground(new Color(189, 182, 181));
        delUser.setFocusPainted(false);
        delUser.addActionListener(e -> {
            FrmDUsuario delUser = new FrmDUsuario();
            delUser.setVisible(true);
            this.dispose();
        });
        add(delUser);
        
        delUserL = new JLabel("Eliminar Usuario", SwingConstants.CENTER);
        delUserL.setBounds(285, 220, 130, 30);
        add(delUserL);

        // Seccion Eliminar Producto
        delPr = new JButton();
        ImageIcon delPrIcon = new ImageIcon(getClass().getResource("/images/delProducto.png"));
        delPr.setIcon(new ImageIcon(delPrIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        delPr.setToolTipText("Eliminar Producto");
        delPr.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        delPr.setBounds(475, 120, 100, 100);
        delPr.setBackground(new Color(189, 182, 181));
        delPr.setFocusPainted(false);
        delPr.addActionListener(e -> {
            this.dispose();
            FrmDProducto frmDel = new FrmDProducto();
            frmDel.setVisible(true);
        });
        add(delPr);

        delPrL = new JLabel("Eliminar producto", SwingConstants.CENTER);
        delPrL.setBounds(460, 220, 130, 30);
        add(delPrL);

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
        
        newUser.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newUser.setBackground(new Color(160, 155, 154)); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newUser.setBackground(new Color(189, 182, 181)); 
            }
        });
        
        delUser.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                delUser.setBackground(new Color(160, 155, 154)); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                delUser.setBackground(new Color(189, 182, 181)); 
            }
        });
        
        delPr.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                delPr.setBackground(new Color(160, 155, 154)); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                delPr.setBackground(new Color(189, 182, 181)); 
            }
        });
    }    
}