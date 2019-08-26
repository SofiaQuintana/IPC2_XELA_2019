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
import packagedelivery.DummyClasses.Client;

/**
 *
 * @author zofia
 */
public class ClientDBManager {
     private Connection connection;
    private List<Client> clients = new ArrayList<>();
    private static final String SELECT_CLIENT_QUERY = "SELECT * FROM Client WHERE Nit = ";

    public ClientDBManager(Connection connection) {
        this.connection = connection;
    }
    
    public List<Client> getElements(String query) {
        clients.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String nit = result.getString("Nit");
                String name = result.getString("Name");
                String lastName = result.getString("LastName");
                String address = result.getString("Address");
                Client client = new Client(nit, name, lastName, address);
                clients.add(client);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clients;
    }
    
    public Client getClienInList(String nit) {
        Client client;
        String destinationQuery = SELECT_CLIENT_QUERY + "'"+ nit + "';";
        this.clients = getElements(destinationQuery);
        if(this.clients.isEmpty()) {
            client = null;
            return client;
        } else {
            client = this.clients.get(0);
            return client;
        } 
    }
    
     public void addClient(String nit, String name, String lastName, String address) throws Exception{
        Client client = getClienInList(nit);
        if(client == null) {
            try {
                String query = ("INSERT INTO Client (Nit, Name, LastName, Address) VALUES (?, ?, ?, ?)");

                PreparedStatement object = connection.prepareStatement(query);
                object.setString(1, nit);
                object.setString(2, name);
                object.setString(3, lastName);
                object.setString(4, address);
                object.execute();
                
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new Exception("Este cliente ya existe.");
        }
    }
}
