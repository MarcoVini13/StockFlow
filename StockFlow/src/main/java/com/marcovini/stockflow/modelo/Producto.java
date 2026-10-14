/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.modelo;

/**
 * Clase modelo que representa la entidad Producto dentro del inventario.
 * Encapsula la información técnica, precios, stock e identificadores
 * correspondientes a cada artículo registrado.
 *
 * @author Marco
 * @version 1.0
 */
public class Producto {
    
    private int idProd;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int idCategoria;
    
 
    public Producto(){
        
    }
    
    //NO AUTOINCREMENT
    public Producto(int idProd, String codigo,String nombre, String descripcion, double precio, int stock, int idCategoria){
        this.idProd = idProd;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
               
    }
    
    //AUTOINCREMENT
    public Producto(String codigo, String nombre, String descripcion, double precio, int stock, int idCategoria){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }
    
    //getters
    public int getidProd(){
        return idProd;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public String getNombre(){
        return nombre;
    }
    public String getDesc(){
        return descripcion;
    }
    public double getPrecio(){
        return precio;
    }
    public int getStock(){
        return stock;
    }
    
    public int getidCategoria(){
        return idCategoria;
    }
    
    //setters
    public void setidProd(int idProd){
        this.idProd = idProd;
    }
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setNombre(String nombre){
        this.nombre =nombre;
    }
    public void setDesc(String descripcion){
        this.descripcion = descripcion;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
    
   public void setidCategoria(int idCategoria){
        this.idCategoria = idCategoria;
    }
    
}
