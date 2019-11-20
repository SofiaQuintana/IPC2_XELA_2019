/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dbmanagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zofia
 */
public class GeneralDBManager {
    private Connection connection = null; //Coneccion.
    
    public Connection dataBaseConnection() { //Conecta a la base de datos correspondiente.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String user = "root";
            String password = "@Dopediamond?8";
            String urlConnection = "jdbc:mysql://localhost:3306/Hospital";
            //Se abre una coneccion a la DB usando una url, el password y user.
            this.connection = DriverManager.getConnection(urlConnection, user, password);
            System.out.println("conectado:" + connection.getCatalog());
        } catch(SQLException e) {
            System.out.println("error, no se logro conectar a la DB.");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GeneralDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.connection;
    }
    
    public void disconnect() {
        try {
            if(this.connection != null || !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateDataBaseTable(String query ) { //Prepara una declaracion para actualizar cualquier elemento de la db.
        try {
            PreparedStatement object = connection.prepareStatement(query);
            object.execute();    
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
