/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcovini.stockflow.modelo;

/**
 * Clase modelo que representa la entidad Usuario dentro del sistema.
 * Contiene los atributos, constructores, getters y setters requeridos
 * para manipular los datos de un usuario de forma encapsulada.
 *
 * @author Marco
 * @version 1.0
 */
public class Usuario {
 
    private int IDUser;
    private String nombre;
    private String username;
    private String password;
    private int idRol;
    
    
    public  Usuario(){
        
    }
    
    //NO AUTOINCREMENT
    public Usuario(int IDUser, String nombre, String username, String password,int idRol){
        this.IDUser = IDUser;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.idRol = idRol;
    }
    
    //AUTOINCREMENT
    public Usuario(String nombre, String username, String password,int idRol){
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.idRol = idRol;
    }
    
    //Getters
    public int getIDUser(){
        return this.IDUser;
    }
    
    public String getNombre(){
        return this.nombre;
    
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public int getidRol(){
        return this.idRol;
    }
    
    //Setters
    public void setIDUser(int IDUser){
        this.IDUser = IDUser;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setidRol(int idRol){
        this.idRol = idRol;
    }
    
}
