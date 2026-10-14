package com.marcovini.stockflow.dao;

import com.marcovini.stockflow.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad Producto.
 * Proporciona los métodos para consultar, registrar, modificar y eliminar
 * artículos del inventario mediante consultas SQL preparadas.
 *
 * @author Marco Almendarez
 * @version 1.0
 */
public class ProductoDAO {
        
    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param codigo      Código de barras o clave única del producto.
     * @param nombre      Nombre descriptivo del producto.
     * @param precio      Precio unitario del producto.
     * @param stock       Cantidad disponible en inventario.
     * @param idCategoria Identificador de la categoría asociada al producto.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso de error.
     */
    public boolean insertar(String codigo, String nombre, double precio, int stock, int idCategoria) {
        boolean exito = false;
        String sql = "INSERT INTO productos (codigo, nombre, precio, stock, id_categoria) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, codigo);  
            pstmt.setString(2, nombre);
            pstmt.setDouble(3, precio);
            pstmt.setInt(4, stock);
            pstmt.setInt(5, idCategoria);
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                exito = true; 
                JOptionPane.showMessageDialog(null, "¡Producto registrado con éxito!");
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se insertó el producto: " + e.getMessage());
        }
        
        return exito;
    }

    /**
     * Elimina un producto de la base de datos utilizando el ID, Código o Nombre.
     * Se evalúa la presencia de datos priorizando ID > Código > Nombre.
     *
     * @param idProdStr Criterio de ID en formato de cadena de texto.
     * @param codigoStr Criterio de código de producto.
     * @param nombreStr Criterio de nombre de producto.
     * @return {@code true} si se eliminó el registro; {@code false} si no se encontró o hubo error.
     */
    public boolean eliminar(String idProdStr, String codigoStr, String nombreStr) {
        boolean exito = false;

        boolean tieneID = idProdStr != null && !idProdStr.trim().isEmpty();
        boolean tieneCodigo = codigoStr != null && !codigoStr.trim().isEmpty();
        boolean tieneNombre = nombreStr != null && !nombreStr.trim().isEmpty();

        if (!tieneID && !tieneCodigo && !tieneNombre) {
            JOptionPane.showMessageDialog(null, "Ingresa al menos un criterio (ID, Código o Nombre) para eliminar.");
            return false;
        }

        StringBuilder sql = new StringBuilder("DELETE FROM productos WHERE ");
        if (tieneID) {
            sql.append("id_producto = ?");
        } else if (tieneCodigo) {
            sql.append("codigo = ?");
        } else {
            sql.append("nombre = ?");
        }

        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            if (tieneID) {
                pstmt.setInt(1, Integer.parseInt(idProdStr.trim()));
            } else if (tieneCodigo) {
                pstmt.setString(1, codigoStr.trim());
            } else {
                pstmt.setString(1, nombreStr.trim());
            }

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
                JOptionPane.showMessageDialog(null, "¡Producto eliminado con éxito!");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún producto con los datos ingresados.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número entero válido.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage());
        }

        return exito;
    }
        
    /**
     * Realiza una búsqueda filtrada de productos en base a múltiples criterios opcionales.
     *
     * @param idProd      ID único del producto (usar 0 para ignorar este filtro).
     * @param codigo      Código de barras (usar cadena vacía para ignorar).
     * @param nombre      Nombre del producto para coincidencia parcial (LIKE).
     * @param precio      Precio del producto (usar 0.0 para ignorar).
     * @param stock       Stock disponible (usar 0 para ignorar).
     * @param idCategoria ID de la categoría (usar 0 para ignorar).
     * @return Objeto {@link DefaultTableModel} con las filas y columnas resultantes.
     */
    public DefaultTableModel buscarProducto(int idProd, String codigo, String nombre, double precio, int stock, int idCategoria){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Producto");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("ID Categoria");

        String sql = "SELECT * FROM productos WHERE 1=1 ";

        if (idProd != 0) { sql += " AND id_producto = " + idProd; }
        if (!codigo.isEmpty()) { sql += " AND codigo = '" + codigo + "'"; }
        if (!nombre.isEmpty()) { sql += " AND nombre LIKE '%" + nombre + "%'"; } 
        if (precio != 0.0) { sql += " AND precio = " + precio; }
        if (stock != 0) { sql += " AND stock = " + stock; }
        if (idCategoria != 0) { sql += " AND id_categoria = " + idCategoria; }

        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_producto"); 
                fila[1] = rs.getString("codigo");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getDouble("precio");
                fila[4] = rs.getInt("stock");
                fila[5] = rs.getInt("id_categoria");

                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar productos filtrados: " + e.getMessage());
        }
        
        return modelo;
    }
        
    /**
     * Obtiene la totalidad de los productos registrados en la base de datos.
     *
     * @return Objeto {@link DefaultTableModel} cargado con la lista general de productos.
     */
    public DefaultTableModel listarProductos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Producto");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("ID Categoria");

        String sql = "SELECT * FROM productos";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_producto"); 
                fila[1] = rs.getString("codigo");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getDouble("precio");
                fila[4] = rs.getInt("stock");
                fila[5] = rs.getInt("id_categoria");

                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    
        return modelo;
    }
    
    public boolean eliminarProducto(String id, String codigo, String nombre) {
    boolean eliminado = false;
    String sql = "";
    
    if (id != null && !id.isEmpty()) {
        sql = "DELETE FROM productos WHERE id_producto = " + id;
    } else if (codigo != null && !codigo.isEmpty()) {
        sql = "DELETE FROM productos WHERE codigo = '" + codigo + "'";
    } else if (nombre != null && !nombre.isEmpty()) {
        sql = "DELETE FROM productos WHERE nombre = '" + nombre + "'";
    } else {
        return false;
    }

    try (Connection con = Conexion.conectar();
         Statement st = con.createStatement()) {
        int filasAfectadas = st.executeUpdate(sql);
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente.");
            eliminado = true;
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún producto con ese criterio.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage());
    }
    return eliminado;
}
}