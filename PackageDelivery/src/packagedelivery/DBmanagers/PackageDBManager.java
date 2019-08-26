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
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import packagedelivery.DummyClasses.Package;
/**
 *
 * @author zofia
 */
public class PackageDBManager {
    private Connection connection;
    private List<Package> packages = new ArrayList<>();
    private static final String SELECT_PACKAGE_QUERY = "SELECT * FROM Package WHERE IdPackage = ";

    public PackageDBManager(Connection connection) {
        this.connection = connection;
    }

    //Genera un codigo unico mediante un UUID, debido a que es de 128 bits, se divide
    //y se toma solo la primera parte.
    public String generateCode() {
        String generatedID = UUID.randomUUID().toString();
        String[] parts = generatedID.split("-");
        String uniqueID = parts[1];
        return uniqueID;
    }
    
    public List<Package> getElements(String query) {
        packages.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String packageId = result.getString("IdPackage");
                String clientNit = result.getString("Client_Nit");
                String destinationId = result.getString("Destination_Id");
                String billId = result.getString("Bill_id");
                String rateId = result.getString("Rate_Id");
                boolean priorized = result.getBoolean("Priorized");
                double weight = result.getDouble("Weight");
                double globalFare = result.getDouble("GlobalFare");
                double priorizationFare = result.getDouble("PriorizationFare");
                double weightFare = result.getDouble("WeightFare");
                double price = result.getDouble("Price");
                boolean availability = result.getBoolean("Availability");
                Date date = result.getDate("Date");
                LocalDate localDate = date.toLocalDate();
                Package pckage = new Package(packageId, clientNit, destinationId, billId, rateId, weight, 
                        globalFare, priorizationFare, weightFare, price, availability, priorized, localDate);
                packages.add(pckage);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return packages;
    }
    
    public Package getPackageInList(String packageId) {
        Package pckge; //Perdon por esta declaracion, pero claramente no me reconoce "package"
        String destinationQuery = SELECT_PACKAGE_QUERY + "'"+ packageId + "';";
        this.packages = getElements(destinationQuery);
        if(this.packages.isEmpty()) {
            pckge = null;
            return pckge;
        } else {
            pckge = this.packages.get(0);
            return pckge;
        } 
    }
    
     public void addPackage(String packageId, String clientNit, String destinationId, String billId, String rateId,
             boolean priorized, double weight, double globalFare, double priorizationFare, double weightFare,
             double price, boolean availability, Date date) throws Exception{
        Package pckge = getPackageInList(packageId);
        if(pckge == null) {
            try {
                String query = ("INSERT INTO Package (IdPackage, Client_Nit, Destination_Id, Bill_id, "
                        + "Rate_Id, Priorized, Weight, GlobalFare, PriorizationFare, WeightFare, Price, Availability, Date) " 
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                PreparedStatement object = connection.prepareStatement(query);
                object.setString(1, packageId);
                object.setString(2, clientNit);
                object.setString(3, destinationId);
                object.setString(4, billId);
                object.setString(5, rateId);
                object.setBoolean(6, priorized);
                object.setDouble(7, weight);
                object.setDouble(8, globalFare);
                object.setDouble(9, priorizationFare);
                object.setDouble(10, weightFare);
                object.setDouble(11, price);
                object.setBoolean(12, availability);
                object.setDate(13, date);
                object.execute();
                
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new Exception("Este paquete ya existe, verifique ID");
        }
    }
}
