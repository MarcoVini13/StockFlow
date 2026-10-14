/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.controlador;

import com.marcovini.stockflow.dao.UsuarioDAO;
import com.marcovini.stockflow.modelo.Usuario;
import com.marcovini.stockflow.vista.FrmLogin;
import com.marcovini.stockflow.vista.FrmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Controlador para la vista de inicio de sesión (Login).
 * Intercepta los eventos de la interfaz de usuario y coordina la validación 
 * de credenciales con la base de datos a través del DAO, aplicando el patrón MVC.
 *
 * @author Marco Almendarez
 * @version 1.0
 */
public class LoginControlador implements ActionListener {
    
    private FrmLogin vista;
    private UsuarioDAO modelo;

    /**
     * Constructor del controlador de login.
     * 
     * @param vista  Instancia de la ventana de login.
     * @param modelo Instancia del DAO de usuario para acceso a datos.
     */
    public LoginControlador(FrmLogin vista, UsuarioDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.getLogBtn().addActionListener(this);
    }

    /**
     * Captura y gestiona los eventos de acción generados en la vista.
     * 
     * @param e Evento de acción capturado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getLogBtn()) {
            String username = vista.getUsertxt().getText();
            String password = new String(vista.getPasstxt().getPassword()); 

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor llene todos los campos.");
                return;
            }

            Usuario usuario = modelo.login(username, password);

            if (usuario != null) {
                JOptionPane.showMessageDialog(vista, "¡Bienvenido al sistema, " + usuario.getNombre() + "!", "Acceso Concedido", JOptionPane.INFORMATION_MESSAGE);
                vista.dispose(); 
                
                FrmMenu menu = new FrmMenu(usuario); 
                menu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                vista.getPasstxt().setText("");
            }
        }
    }
}