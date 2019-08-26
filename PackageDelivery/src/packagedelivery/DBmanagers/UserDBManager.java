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
import packagedelivery.DummyClasses.User;

/**
 *
 * @author zofia
 */
public class UserDBManager  {
    private Connection connection;
    private List<User> users = new ArrayList<>();
    private static final String SELECT_USER_QUERY = "SELECT * FROM User WHERE Username = ";
 
    public UserDBManager(Connection connection) {
        this.connection = connection;
    }
    
    public List<User> getUsers(String query) {
        users.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String username = result.getString("Username");
                String name = result.getString("Name");
                String lastname = result.getString("LastName");
                String password = result.getString("Password");
                String role = result.getString("Role");
                boolean availability = result.getBoolean("Availability");
                User user = new User(username, name, lastname, password, role, availability);
                users.add(user);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
    
    public User getUserInList(String username) {
        User user;
        String userQuery = SELECT_USER_QUERY + "'"+ username + "';";
        this.users = getUsers(userQuery);
        if(this.users.isEmpty()) {
            user = null;
            return user;
        } else {
            user = this.users.get(0);
            return user;
        } 
    }
    
     public void addUser(String username, String name, String lastName, String password, 
                                                    String role, boolean availability) throws SQLException, Exception {
        User user = getUserInList(username);
        if(user == null) {
            try {
                String query = ("INSERT INTO User (Username, Name, LastName, Password, Role, Availability) "
                        + "VALUES (?, ?, ?, ?, ?, ?)");

                PreparedStatement object = connection.prepareStatement(query);
                object.setString(1, username);
                object.setString(2, name);
                object.setString(3, lastName);
                object.setString(4, password);
                object.setString(5, role);
                object.setBoolean(6, availability);
                object.execute();
                
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new Exception("Este usuario ya existe");
        }
    }
 
}
