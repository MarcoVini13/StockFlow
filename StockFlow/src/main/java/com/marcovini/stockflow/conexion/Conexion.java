/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase encargada de gestionar la conexión con la base de datos MySQL.
 * Utiliza el controlador JDBC para establecer y mantener el enlace
 * de comunicación con el servidor de la base de datos.
 *
 * @author Marco Almendarez
 * @version 1.0
 */
public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/stockflow_database?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection conectar(){
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexion a la Base de Datos: " + e.getMessage());
        }
        return conn;
    }
    
    
}
