/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.vista;

import com.marcovini.stockflow.dao.ProductoDAO;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

/**
 * Ventana genérica de visualización de tablas en el sistema StockFlow.
 * Se encarga de recibir dinámicamente un modelo de datos y un título para estructurar 
 * y presentar la información tabular (como listas de inventario o de usuarios) 
 * con soporte de desplazamiento (JScrollPane).
 * 
 * @author Marco Almendarez
 * @version 1.0
 */
public class FrmTablas extends JFrame{
    
    /**
     * Constructor de la clase FrmTablas.
     * Configura las dimensiones, el título de la ventana y construye dinámicamente 
     * el componente JTable utilizando el modelo de datos proporcionado, encapsulándolo 
     * dentro de un panel de desplazamiento para su correcta visualización.
     * 
     * @param modelo El modelo de tabla (DefaultTableModel) que contiene los datos a mostrar.
     * @param titulo El título que se mostrará en la barra superior de la ventana.
     */
    public FrmTablas(DefaultTableModel modelo, String titulo){
        setTitle(titulo);
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        JTable tabla = new JTable(modelo);
        JScrollPane panelConScroll = new JScrollPane(tabla);

        add(panelConScroll);
    }
}