/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dbmanagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author zofia
 */
public class GeneralDBManager {
    private Connection connection = null; //Coneccion.
    
    public Connection DataBaseConnection() { //Conecta a la base de datos correspondiente.
        try {
            String user = "root";
            String password = "@Dopediamond?8";
            String urlConnection = "jdbc:mysql://localhost:3306/Hospital";
            //Se abre una coneccion a la DB usando una url, el password y user.
            this.connection = DriverManager.getConnection(urlConnection, user, password);
            System.out.println("conectado:" + connection.getCatalog());
        } catch(SQLException e) {
            System.out.println("error, no se logro conectar a la DB.");
        }
        return this.connection;
    }
}
