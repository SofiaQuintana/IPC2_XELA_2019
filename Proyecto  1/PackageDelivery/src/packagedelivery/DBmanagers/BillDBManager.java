/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.DBmanagers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import packagedelivery.DummyClasses.Bill;

/**
 *
 * @author zofia
 */
public class BillDBManager {
    private Connection connection;
    private List<Bill> bills = new ArrayList<>();
    private static final String SELECT_BILL_QUERY = "SELECT * FROM Bill WHERE idBill = ";

    public BillDBManager(Connection connection) {
        this.connection = connection;
    }

    //Genera un codigo unico mediante un UUID, debido a que es de 128 bits, se divide
    //y se toma solo la segunda parte.
    public String generateCode() {
        String generatedID = UUID.randomUUID().toString();
        String[] parts = generatedID.split("-");
        String uniqueID = parts[1];
        return uniqueID;
    }
    
    public List<Bill> getElements(String query) {
        bills.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String idBill = result.getString("idBill");
                String clientNit = result.getString("Client_Nit");
                String name = result.getString("Name");
                String address = result.getString("Address");
                Date date = result.getDate("BillDate");
                LocalDate localDate = date.toLocalDate();
                Bill bill = new Bill(idBill, clientNit, name, address, localDate);
                bills.add(bill);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bills;
    }
    
    public Bill getBillInList(String IdBill) {
        Bill bill;
        String destinationQuery = SELECT_BILL_QUERY + "'"+ IdBill + "';";
        this.bills = getElements(destinationQuery);
        if(this.bills.isEmpty()) {
            bill = null;
            return bill;
        } else {
            bill = this.bills.get(0);
            return bill;
        } 
    }
    
     public void addBill(String IdBill, String clientNit, String name, String address, Date date) throws Exception{
        Bill bill = getBillInList(IdBill);
        if(bill == null) {
            try {
                String query = ("INSERT INTO Bill (idBill, Client_Nit, Name, Address, BillDate) VALUES (?, ?, ?, ?, ?)");

                PreparedStatement object = connection.prepareStatement(query);
                object.setString(1, IdBill);
                object.setString(2, clientNit);
                object.setString(3, name);
                object.setString(4, address);
                object.setDate(5, date);
                object.execute();
                
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new Exception("Esta factura ya existe, verifique ID");
        }
    }
     
    
}
