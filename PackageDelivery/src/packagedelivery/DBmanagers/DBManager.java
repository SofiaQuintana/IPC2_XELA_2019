/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.DBmanagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import packagedelivery.DummyClasses.Rate;

/**
 *
 * @author zofia
 */
public class DBManager {
    private Connection connection;

    public DBManager(Connection connection) {
        this.connection = connection;
    }
    
    public void updateElement(String query) {
        try {
            PreparedStatement object = connection.prepareStatement(query);
            object.execute();    
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Rate> getRate(String query) {
        List<Rate> rates = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String idRate = result.getString("idRate");
                double globalFare = result.getDouble("GlobalFare");
                double weightFare = result.getDouble("WeightFare");
                double priorizationFare = result.getDouble("PriorizationFare");
                
                Rate rate = new Rate(idRate, globalFare, weightFare, priorizationFare);
                rates.add(rate);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rates;
    }
}
