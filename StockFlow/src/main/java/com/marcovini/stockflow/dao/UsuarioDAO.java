package com.marcovini.stockflow.dao;

import com.marcovini.stockflow.conexion.Conexion;
import com.marcovini.stockflow.modelo.Usuario;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad Usuario.
 * Gestiona todas las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * y autenticación (login) directamente con la tabla de usuarios en la base de datos.
 *
 * @author Marco Almendarez
 * @version 1.0
 */
public class UsuarioDAO {

    /**
     * Valida las credenciales de un usuario para permitir el acceso al sistema.
     * 
     * @param username Nombre de usuario ingresado.
     * @param password Contraseña ingresada.
     * @return Objeto {@link Usuario} con sus datos cargados si las credenciales son válidas; {@code null} en caso contrario.
     */
    public Usuario login(String username, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
        
        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIDUser(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setidRol(rs.getInt("id_rol"));
                }
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar iniciar sesión: " + e.getMessage());
        }
        
        return usuario;
    }
    
    /**
     * Obtiene la lista completa de usuarios registrados en la base de datos.
     * 
     * @return Objeto {@link DefaultTableModel} estructurado para poblar un JTable.
     */
    public DefaultTableModel listarUsuarios() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Usuario");
        modelo.addColumn("Password");
        modelo.addColumn("ID Rol");

        String sql = "SELECT * FROM usuarios";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("id_usuario"); 
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("username");
                fila[3] = rs.getString("password"); // Corregido a getString
                fila[4] = rs.getInt("id_rol");

                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }

        return modelo;
    }
    
    /**
     * Registra un nuevo usuario en la base de datos.
     * 
     * @param nombre   Nombre completo del usuario.
     * @param username Nombre de usuario único para acceso al sistema.
     * @param password Contraseña de acceso.
     * @param id_rol   Identificador del rol asignado (1: Admin, 2: Cajero, etc.).
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     */
    public boolean insertar(String nombre, String username, String password, int id_rol) {
        boolean exito = false;
        String sql = "INSERT INTO usuarios (nombre, username, password, id_rol) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
            pstmt.setString(1, nombre);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setInt(4, id_rol);

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true; 
                JOptionPane.showMessageDialog(null, "¡Usuario registrado con éxito!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se registró el usuario: " + e.getMessage());
        }

        return exito;
    }
        
    /**
     * Elimina un registro de usuario especificando su ID, Nombre o Username.
     * Evalúa los parámetros ingresados priorizando ID > Username > Nombre.
     * 
     * @param idStr       ID del usuario en formato String.
     * @param nombreStr   Nombre del usuario.
     * @param usernameStr Nombre de usuario.
     * @return {@code true} si el usuario fue eliminado; {@code false} si no se encontró o hubo un error.
     */
    public boolean eliminar(String idStr, String nombreStr, String usernameStr) {
        boolean exito = false;
        boolean tieneID = idStr != null && !idStr.trim().isEmpty();
        boolean tieneUsername = usernameStr != null && !usernameStr.trim().isEmpty();
        boolean tieneNombre = nombreStr != null && !nombreStr.trim().isEmpty();

        if (!tieneID && !tieneUsername && !tieneNombre) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa al menos un criterio (ID, Nombre o Username) para eliminar.");
            return false;
        }

        StringBuilder sql = new StringBuilder("DELETE FROM usuarios WHERE ");
        if (tieneID) {
            sql.append("id_usuario = ?");
        } else if (tieneUsername) {
            sql.append("username = ?");
        } else {
            sql.append("nombre = ?");
        }

        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            if (tieneID) {
                pstmt.setInt(1, Integer.parseInt(idStr.trim()));
            } else if (tieneUsername) {
                pstmt.setString(1, usernameStr.trim());
            } else {
                pstmt.setString(1, nombreStr.trim());
            }

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
                JOptionPane.showMessageDialog(null, "¡Usuario eliminado con éxito! (" + filasAfectadas + " registro(s) borrado(s))");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con los datos ingresados.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número entero válido.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + e.getMessage());
        }

        return exito;
    }
}