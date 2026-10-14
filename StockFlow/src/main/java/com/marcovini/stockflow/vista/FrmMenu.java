/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.vista;

import javax.swing.*;
import java.awt.*;
import com.marcovini.stockflow.modelo.Usuario;

/**
 * Ventana del menú principal de gestión del sistema StockFlow.
 * Actúa como panel central de navegación, permitiendo al usuario acceder a las opciones 
 * de búsqueda de productos, inserción de nuevos registros, configuración del sistema 
 * y visualización de créditos de autoría.
 * 
 * @author Marco Almendarez
 * @version 1.0
 */
public class FrmMenu extends JFrame{
    
    private JLabel buscarL, insertL, confL, titleL, subtitleL;
    private JButton buscar, insertar, conf, regresar, creador;

    /**
     * Constructor por defecto por compatibilidad.
     * Permite instanciar el menú sin parámetros si es necesario (ej. desde ventanas de retorno).
     */
    public FrmMenu() {
        this(null);
    }

    /**
     * Constructor principal de la clase FrmMenu.
     * Configura los parámetros de la ventana, establece los eventos de navegación 
     * y aplica restricciones de seguridad (control de roles) según el usuario logueado.
     * 
     * @param usuario Objeto Usuario con los datos de la sesión actual.
     */
    public FrmMenu(Usuario usuario){
        setTitle("Gestion de Inventario");
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

        subtitleL = new JLabel("Gestión de inventario", SwingConstants.CENTER);
        subtitleL.setFont(new Font("Andale Mono", Font.ITALIC, 18));
        subtitleL.setBounds(200, 65, 300, 30);
        add(subtitleL);

        buscar = new JButton();
        ImageIcon buscarIcon = new ImageIcon(getClass().getResource("/images/buscar.png"));
        buscar.setIcon(new ImageIcon(buscarIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        buscar.setToolTipText("Buscar producto");
        buscar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        buscar.setBounds(125, 120, 100, 100);
        buscar.setBackground(new Color(189, 182, 181));
        buscar.setFocusPainted(false);
        buscar.addActionListener(e -> {
            FrmBuscar buscar = new FrmBuscar();
            buscar.setVisible(true);
            this.dispose();
        });
        add(buscar);

        insertar = new JButton();
        ImageIcon insertarIcon = new ImageIcon(getClass().getResource("/images/insertar.png"));
        insertar.setIcon(new ImageIcon(insertarIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        insertar.setToolTipText("Agregar producto");
        insertar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        insertar.setBounds(300, 120, 100, 100);
        insertar.setFocusPainted(false);
        insertar.setBackground(new Color(189, 182, 181));
        insertar.addActionListener(e -> {
            FrmInsertar ins = new FrmInsertar();
            ins.setVisible(true);
            this.dispose();
        });
        add(insertar);

        conf = new JButton();
        ImageIcon confIcon = new ImageIcon(getClass().getResource("/images/conf.png"));
        conf.setIcon(new ImageIcon(confIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        conf.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        conf.setBounds(475, 120, 100, 100);
        conf.setFocusPainted(false);
        
        // Validación de roles: Si es cajero, se bloquea el acceso a configuración.
        if (usuario != null && usuario.getidRol() == 2) {
            conf.setEnabled(false);
            conf.setToolTipText("Acceso restringido solo a Administradores");
            conf.setBackground(new Color(120, 120, 120));
        } else {
            conf.setToolTipText("Configuracion");
            conf.setBackground(new Color(189, 182, 181));
            conf.addActionListener(e -> {
                FrmConfig config = new FrmConfig();
                config.setVisible(true);
                this.dispose();
            });
            
            conf.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    conf.setBackground(new Color(160, 155, 154)); 
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    conf.setBackground(new Color(189, 182, 181)); 
                }
            });
        }
        add(conf);

        buscarL = new JLabel("Buscar producto", SwingConstants.CENTER);
        buscarL.setBounds(110, 220, 130, 30);
        add(buscarL);

        insertL = new JLabel("Agregar producto", SwingConstants.CENTER);
        insertL.setBounds(285, 220, 130, 30);
        add(insertL);

        confL = new JLabel("Configuracion", SwingConstants.CENTER);
        confL.setBounds(460, 220, 130, 30);
        add(confL);

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
            FrmLogin log = new FrmLogin();
            log.setVisible(true);
        });
        add(regresar);
        
        creador = new JButton("?");
        creador.setToolTipText("Acerca de");
        creador.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        creador.setBackground(new Color(189, 182, 181));
        creador.setBounds(630, 310, 40, 40);
        creador.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Creado por: Marco Vinicio Almendarez Briones");
        });
        add(creador);
        
        buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buscar.setBackground(new Color(160, 155, 154)); 
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buscar.setBackground(new Color(189, 182, 181)); 
            }
        });
        
        insertar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                insertar.setBackground(new Color(160, 155, 154)); 
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                insertar.setBackground(new Color(189, 182, 181)); 
            }
        });
    }    
}