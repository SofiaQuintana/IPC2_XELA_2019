/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.DBmanagers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import packagedelivery.DummyClasses.Storage;

/**
 *
 * @author zofia
 */
public class StorageDBManager {
    private Connection connection;
    private List<Storage> storages = new ArrayList<>();
    private static final String SELECT_STORAGE_QUERY = "SELECT * FROM Storage ORDER BY priorized DESC, Date ASC;";

    public StorageDBManager(Connection connection) {
        this.connection = connection;
    }

    public List<Storage> getElements(String query) {
        storages.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String packageId = result.getString("Package_Id");
                Date date = result.getDate("Date");
                boolean priorized = result.getBoolean("priorized");
                
                LocalDate localDate = date.toLocalDate();
                Storage storage = new Storage(packageId, localDate, priorized);
                storages.add(storage);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return storages;
    }
    
    public List<Storage> getPackagesInQueue() throws Exception {
        String destinationQuery = SELECT_STORAGE_QUERY;
        this.storages = getElements(destinationQuery);
        if(this.storages.isEmpty()) {
            throw new Exception("Bodega vacia!!!!!");
        } else {
            return this.storages;
        } 
    }
    
     public void addPackageToQueue(String packageId, Date date, boolean priorized) {       
            try {
                String query = ("INSERT INTO Storage (Package_Id, Date, priorized) VALUES (?, ?, ?)");

                PreparedStatement object = connection.prepareStatement(query);
                object.setString(1, packageId);
                object.setDate(2, date);
                object.setBoolean(3, priorized);
                object.execute();
                
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }        
    }
     
}
