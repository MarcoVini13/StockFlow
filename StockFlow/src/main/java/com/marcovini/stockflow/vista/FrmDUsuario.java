/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.vista;

import javax.swing.*;
import java.awt.*;
import com.marcovini.stockflow.dao.UsuarioDAO;

/**
 * Ventana de interfaz gráfica para la eliminación de usuarios en el sistema StockFlow.
 * Permite buscar y eliminar registros de la base de datos utilizando criterios flexibles 
 * como ID, Nombre o Username, integrando además la vista de listados y control de navegación.
 * 
 * @author Marco Almendarez
 * @version 1.0
 */
public class FrmDUsuario extends JFrame {
    
    private JLabel idL, nombreL, userL, titleL, subtitleL;
    private JTextField id, nombre, username;
    private JButton eliminar, limpiar, regresar, listar;
    
    /**
     * Constructor de la clase FrmDUsuario.
     * Inicializa los componentes de la interfaz de usuario, define las dimensiones, 
     * organiza los campos de texto e implementa la lógica de eventos para la validación, 
     * confirmación y ejecución de la eliminación a través de UsuarioDAO.
     */
    public FrmDUsuario(){
        setTitle("Elimina un usuario");
        setSize(new Dimension(345,400));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        titleL = new JLabel("StockFlow");
        titleL.setFont(new Font("Andale Mono", Font.BOLD | Font.ITALIC, 36));
        titleL.setForeground(new Color(0, 0, 255));
        titleL.setBounds(20, 10, 300, 40);
        add(titleL);
        
        subtitleL = new JLabel("Elimina un usuario");
        subtitleL.setFont(new Font("Andale Mono", Font.ITALIC, 18));
        subtitleL.setBounds(20, 55, 300, 30);
        add(subtitleL);
        
        idL = new JLabel("ID:");
        idL.setBounds(20,120,50,20);
        add(idL);
        
        id = new JTextField();
        id.setBounds(20,140,50,25);
        add(id);
        
        nombreL = new JLabel("Nombre:");
        nombreL.setBounds(80,120,50,20);
        add(nombreL);
        
        nombre = new JTextField();
        nombre.setBounds(80,140,100,25);
        add(nombre);
        
        userL = new JLabel("Username:");
        userL.setBounds(190,120,80,20);
        add(userL);
        
        username = new JTextField();
        username.setBounds(190,140,120,25);
        add(username);
        
        eliminar = new JButton("Eliminar");
        eliminar.setToolTipText("Eliminar");
        eliminar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        eliminar.setBackground(new Color(189, 182, 181));
        eliminar.setBounds(20, 200, 100, 30);
        eliminar.setFocusPainted(false);
        eliminar.addActionListener(e -> {
            String txtId = id.getText().trim();
            String txtNombre = nombre.getText().trim();
            String txtUser = username.getText().trim();

            if (txtId.isEmpty() && txtNombre.isEmpty() && txtUser.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa al menos el ID, Nombre o Username para eliminar.");
                return;
            }
            
            String criterio = !txtId.isEmpty() ? "ID: " + txtId :
                              !txtUser.isEmpty() ? "Username: '" + txtUser + "'" :
                              "Nombre: '" + txtNombre + "'";

            int confirmacion = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de eliminar al usuario con " + criterio + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                UsuarioDAO dao = new UsuarioDAO();
                boolean borrado = dao.eliminar(txtId, txtNombre, txtUser);

                if (borrado) {
                    limpiar.doClick(); // Limpia todas las cajas de texto si se borró con éxito
                }
            }
        });
        add(eliminar);
        
        limpiar = new JButton("Limpiar");
        limpiar.setToolTipText("Limpiar");
        limpiar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        limpiar.setBackground(new Color(189, 182, 181));
        limpiar.setBounds(130, 200, 100, 30);
        limpiar.setFocusPainted(false);
        limpiar.addActionListener(e -> {
            id.setText("");
            nombre.setText("");
            username.setText("");
        });
        add(limpiar);
        
        listar = new JButton();
        ImageIcon listarIcon = new ImageIcon(getClass().getResource("/images/lista.png"));
        listar.setIcon(new ImageIcon(listarIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        listar.setToolTipText("Ver Usuarios");
        listar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        listar.setBounds(270, 200, 40, 40);
        listar.setBackground(new Color(189, 182, 181));
        listar.setFocusPainted(false);
        listar.addActionListener(e -> {
            UsuarioDAO dao = new UsuarioDAO();
            FrmTablas usuarios = new FrmTablas(dao.listarUsuarios(), "Usuarios");
            usuarios.setVisible(true);
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