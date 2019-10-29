/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.Run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import packagedelivery.FrontEnd.LogInDialog;

/**
 *
 * @author zofia
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LogInDialog login;
        Connection connection = null;
        try {
                String user = "root";
                String password = "@Dopediamond?8";
                String urlConnection = "jdbc:mysql://localhost:3306/PackageDelivery";

                //abrimos una coneccion a la DB usando una url, el usuario y password 
                connection = 
                           DriverManager.getConnection(urlConnection, user, password);

                System.out.println("conectado:" + connection.getCatalog());
                login = new LogInDialog(true, connection);
                login.setVisible(true);
        } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("error");
        }
    }
    
}
