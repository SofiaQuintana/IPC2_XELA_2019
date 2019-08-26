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
import packagedelivery.DummyClasses.Checkpoint;

/**
 *
 * @author zofia
 */
public class CheckpointDBManager {
    private Connection connection;
    private List<Checkpoint> checkpoints = new ArrayList<>();
    private static final String SELECT_CHECKPOINT_QUERY = "SELECT * FROM Checkpoint WHERE IdCheckpoint = ";
    private static final String SELECT = "SELECT * FROM Checkpoint WHERE Route_Id = ";

    public CheckpointDBManager(Connection connection) {
        this.connection = connection;
    }
    
    public List<Checkpoint> getElements(String query) {
        checkpoints.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String idCheckpoint = result.getString("IdCheckpoint");
                String idRoute = result.getString("Route_Id");
                String userAssigned = result.getString("User_name");
                String rateId = result.getString("Rate_id");
                int queueSize = result.getInt("QueueSize");
                double globalFare  = result.getDouble("GlobalFare");
                double specialFare = result.getDouble("SpecialFare");
                boolean availability = result.getBoolean("Availability");
                boolean disabled = result.getBoolean("Disabled");
                Checkpoint checkpoint = new Checkpoint(idCheckpoint, idRoute, userAssigned, rateId, queueSize, 
                        globalFare, specialFare, availability,disabled);
                checkpoints.add(checkpoint);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return checkpoints;
    }
    
    public Checkpoint getCheckpointInList(String checkpointId) {
        Checkpoint checkpoint;
        String destinationQuery = SELECT_CHECKPOINT_QUERY + "'"+ checkpointId + "';";
        this.checkpoints = getElements(destinationQuery);
        if(this.checkpoints.isEmpty()) {
            checkpoint = null;
            return checkpoint;
        } else {
            checkpoint = this.checkpoints.get(0);
            return checkpoint;
        } 
    }
    
     public Checkpoint getCheckpoint(String routeId) {
        Checkpoint checkpoint;
        String destinationQuery = SELECT + "'"+ routeId + "';";
        this.checkpoints = getElements(destinationQuery);
        if(this.checkpoints.isEmpty()) {
            checkpoint = null;
            return checkpoint;
        } else {
            checkpoint = this.checkpoints.get(0);
            return checkpoint;
        } 
    }
    
     public void addCheckpoint(String idCheckpoint, String idRoute, String operator, String rate, int queueSize,
             double globalFare, double specialFare, boolean availability, boolean disabled) throws SQLException, Exception {
        Checkpoint checkpoint = getCheckpointInList(idCheckpoint);
        if(checkpoint == null) {
            try {
                String query = ("INSERT INTO Checkpoint (IdCheckpoint, Route_Id, User_name, Rate_id, QueueSize,"
                        + " GlobalFare, SpecialFare, Availability, Disabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

                PreparedStatement object = connection.prepareStatement(query);
                object.setString(1, idCheckpoint);
                object.setString(2, idRoute);
                object.setString(3, operator);
                object.setString(4, rate);
                object.setInt(5, queueSize);
                object.setDouble(6, globalFare);
                object.setDouble(7, specialFare);
                object.setBoolean(8, availability);
                object.setBoolean(9, disabled);
                object.execute();
                
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new Exception("Este punto de control, ya existe");
        }
    }
}
