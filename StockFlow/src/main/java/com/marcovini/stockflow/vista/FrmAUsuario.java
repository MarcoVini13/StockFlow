package com.marcovini.stockflow.vista;

import javax.swing.*;
import java.awt.*;
import com.marcovini.stockflow.dao.UsuarioDAO;

/**
 * Interfaz gráfica de usuario (GUI) para el alta y registro de nuevos usuarios en el sistema.
 * Permite capturar los datos de nombre, username, contraseña y rol, validando las entradas
 * antes de enviarlas a la capa DAO para su persistencia en la base de datos.
 *
 * @author Marco Almendarez
 * @version 1.0
 */
public class FrmAUsuario extends JFrame {
    
    private JLabel nombreL, userL, passwordL, rolL, titleL, subtitleL;
    private JTextField nombre, username, password, rol;
    private JButton agregar, limpiar, regresar, listar;
    
    private JLabel roles, rol1, rol2;
    
    /**
     * Constructor de la clase FrmAUsuario.
     * Configura el diseño de la ventana (dimensiones, posición, operaciones de cierre)
     * e inicializa todos los componentes de la interfaz de usuario con sus respectivos eventos.
     */
    public FrmAUsuario(){
        setTitle("Agrega un usuario");
        setSize(new Dimension(550, 400));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        titleL = new JLabel("StockFlow");
        titleL.setFont(new Font("Andale Mono", Font.BOLD | Font.ITALIC, 36));
        titleL.setForeground(new Color(0, 0, 255));
        titleL.setBounds(20, 10, 300, 40);
        add(titleL);
        
        subtitleL = new JLabel("Agrega un usuario");
        subtitleL.setFont(new Font("Andale Mono", Font.ITALIC, 18));
        subtitleL.setBounds(20, 55, 300, 30);
        add(subtitleL);
        
        nombreL = new JLabel("Nombre:");
        nombreL.setBounds(10, 120, 50, 20);
        add(nombreL);
        
        nombre = new JTextField();
        nombre.setBounds(10, 140, 100, 25);
        add(nombre);
        
        userL = new JLabel("Username:");
        userL.setBounds(120, 120, 80, 20);
        add(userL);
        
        username = new JTextField();
        username.setBounds(120, 140, 120, 25);
        add(username);
        
        passwordL = new JLabel("Contraseña:");
        passwordL.setBounds(250, 120, 100, 20);
        add(passwordL);
        
        password = new JTextField();
        password.setBounds(250, 140, 100, 25);
        add(password);
        
        rolL = new JLabel("Rol:");
        rolL.setBounds(360, 120, 50, 20);
        add(rolL);
        
        rol = new JTextField();
        rol.setBounds(360, 140, 50, 25);
        add(rol);
        
        roles = new JLabel("Roles:");
        roles.setBounds(420, 120, 100, 20);
        add(roles);
        
        rol1 = new JLabel("> 1 Administrador");
        rol1.setBounds(420, 140, 120, 20);
        add(rol1);
        
        rol2 = new JLabel("> 2 Cajero");
        rol2.setBounds(420, 160, 100, 20);
        add(rol2);
        
        agregar = new JButton("Agregar");
        agregar.setToolTipText("Agregar");
        agregar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        agregar.setBackground(new Color(189, 182, 181));
        agregar.setBounds(20, 200, 100, 30);
        agregar.setFocusPainted(false);
        agregar.addActionListener(e -> {
            try {
                String nombreS = nombre.getText().trim();
                String userS = username.getText().trim();
                String passwS = password.getText().trim();
                String rolS = rol.getText().trim();

                if(nombreS.isEmpty() || userS.isEmpty() || passwS.isEmpty() || rolS.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Por favor, llena todos los campos para registrar al usuario.");
                    return;
                }

                int idRS = Integer.parseInt(rolS);

                UsuarioDAO dao = new UsuarioDAO();
                boolean exito = dao.insertar(nombreS, userS, passwS, idRS); 

                if(exito){
                    username.setText("");
                    nombre.setText("");
                    password.setText("");
                    rol.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Asegúrate de ingresar solo números válidos en el Rol.");
            }
        });
        add(agregar);
        
        limpiar = new JButton("Limpiar");
        limpiar.setToolTipText("Limpiar");
        limpiar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        limpiar.setBackground(new Color(189, 182, 181));
        limpiar.setBounds(130, 200, 100, 30);
        limpiar.setFocusPainted(false);
        limpiar.addActionListener(e -> {
            nombre.setText("");
            username.setText("");
            password.setText("");
            rol.setText("");
        });
        add(limpiar);
        
        listar = new JButton();
        ImageIcon listarIcon = new ImageIcon(getClass().getResource("/images/lista.png"));
        listar.setIcon(new ImageIcon(listarIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        listar.setToolTipText("Ver Usuarios");
        listar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        listar.setBounds(440, 200, 40, 40);
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