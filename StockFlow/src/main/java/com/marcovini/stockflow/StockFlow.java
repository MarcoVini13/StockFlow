/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.marcovini.stockflow;

import com.marcovini.stockflow.vista.FrmLogin;

/**
 * Clase principal (Main) del sistema StockFlow.
 * Punto de entrada de la aplicación encargado de inicializar el entorno gráfico
 * y lanzar la pantalla de inicio de sesión.
 *
 * @author Marco Almendarez
 * @version 1.0
 */
public class StockFlow {

    public static void main(String[] args) {
      
      FrmLogin form = new FrmLogin();
      form.setVisible(true);
  
    }
}
