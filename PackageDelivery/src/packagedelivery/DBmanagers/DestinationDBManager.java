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
import packagedelivery.DummyClasses.Destination;

/**
 *
 * @author zofia
 */
public class DestinationDBManager {
    private Connection connection;
    private List<Destination> destinations = new ArrayList<>();
    private static final String SELECT_DESTINATION_QUERY = "SELECT * FROM Destination WHERE IdDestination = ";

    public DestinationDBManager(Connection connection) {
        this.connection = connection;
    }
    
    public List<Destination> getElements(String query) {
        destinations.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String idDestination = result.getString("IdDestination");
                double fee = result.getDouble("Fee");
                double profit = result.getDouble("Profit");
                Destination destination = new Destination(idDestination, fee, profit);
                destinations.add(destination);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return destinations;
    }
    
    public Destination getDestinationInList(String destinationId) {
        Destination destination;
        String destinationQuery = SELECT_DESTINATION_QUERY + "'"+ destinationId + "';";
        this.destinations = getElements(destinationQuery);
        if(this.destinations.isEmpty()) {
            destination = null;
            return destination;
        } else {
            destination = this.destinations.get(0);
            return destination;
        } 
    }
    
     public void addDestination(String destinationId, double fee, double profit) throws SQLException, Exception {
        Destination destination = getDestinationInList(destinationId);
        if(destination == null) {
            try {
                String query = ("INSERT INTO Destination (IdDestination, Fee, Profit) " + "VALUES (?, ?, ?)");

                PreparedStatement object = connection.prepareStatement(query);
                object.setString(1, destinationId);
                object.setDouble(2, fee);
                object.setDouble(3, profit);
                object.execute();
                
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new Exception("Este destino ya existe");
        }
    }
     
     public void updateDestination(String query) {
        try {
            PreparedStatement object = connection.prepareStatement(query);
            object.execute();    
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
