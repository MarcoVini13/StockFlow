/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.vista;

import com.marcovini.stockflow.dao.UsuarioDAO;
import com.marcovini.stockflow.controlador.LoginControlador;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

/**
 * Ventana de inicio de sesión del sistema StockFlow.
 * Se encarga de gestionar la autenticación de los usuarios mediante credenciales (usuario y contraseña),
 * validando los campos de entrada contra la base de datos a través de la capa UsuarioDAO 
 * y permitiendo el acceso al menú principal del sistema.
 * 
 * @author Marco Almendarez
 * @version 1.0
 */
public class FrmLogin extends JFrame{
    
    private JLabel title;
    private JLabel desc;
    private JLabel welc;
    private JLabel us;
    private JLabel pass;
    private JTextField usertxt;
    private JPasswordField passtxt;
    private JButton logBtn;
    
    /**
     * Constructor de la clase FrmLogin.
     * Configura las dimensiones, diseño y propiedades de la ventana de login, 
     * e inicializa todos los componentes gráficos (etiquetas, campos de texto y botón de acceso), 
     * así como la lógica de control para validar el acceso al sistema.
     */
    public FrmLogin(){
        setTitle("Iniciar sesion");
        setSize(new Dimension(350,450));
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        welc = new JLabel("Bienvenido");
        welc.setBounds(125,90,100,20);
        
        title = new JLabel("StockFlow");
        title.setFont(new Font("Andale Mono", 1, 36));
        title.setBounds(75,100,200,40);
        title.setForeground(new Color(0,0,255));
        
        desc = new JLabel("Sistema de gestion de inventario");
        desc.setFont(new Font("Andale Mono", 1, 12));
        desc.setBounds(75,150,190,20);

        us = new JLabel("Usuario:");
        us.setBounds(70,180,50,20);
        add(us);
        
        pass = new JLabel("Contraseña:");
        pass.setBounds(70,225,70,20);
        add(pass);
      
        usertxt = new JTextField();
        usertxt.setBounds(70,200,200,25);
        
        passtxt = new JPasswordField();
        passtxt.setBounds(70,245,200,25);
        
        logBtn = new JButton("Ingresar");
        logBtn.setBounds(120,285,100,25);
        logBtn.setBackground(new Color(130, 141, 158));
        logBtn.setForeground(new Color(255,255,255));
        logBtn.setFocusPainted(false);
        logBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); 
       
        // Accion iniciar sesion
        UsuarioDAO dao = new UsuarioDAO();
        LoginControlador ctrl = new LoginControlador(this, dao);       
        
        add(welc);
        add(title);
        add(desc);
        add(usertxt);
        add(passtxt);
        add(logBtn);
    }
    
    /**
     * Obtiene el botón de inicio de sesión de la vista.
     * 
     * @return Objeto JButton correspondiente al botón de login.
     */
    public javax.swing.JButton getLogBtn() {
        return logBtn;
    }

    /**
     * Obtiene el campo de texto del nombre de usuario.
     * 
     * @return Objeto JTextField correspondiente al usuario.
     */
    public javax.swing.JTextField getUsertxt() {
        return usertxt;
    }

    /**
     * Obtiene el campo de texto de la contraseña protegida.
     * 
     * @return Objeto JPasswordField correspondiente a la contraseña.
     */
    public javax.swing.JPasswordField getPasstxt() {
        return passtxt;
    }
}