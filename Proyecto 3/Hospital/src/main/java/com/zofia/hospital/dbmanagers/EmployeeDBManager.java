/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dbmanagers;

import com.zofia.hospital.dummyclasses.Employee;
import com.zofia.hospital.dummyclasses.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zofia
 */
public class EmployeeDBManager {
    private Connection connection;
    private List<User> users;
    private List<Employee> employees;
    private static final String SELECT_USER_QUERY = "SELECT * FROM User WHERE Username = '";
    private static final String SELECT_EMPLOYEE_QUERY = "SELECT * FROM Employee WHERE CUI = '";
    
    public EmployeeDBManager(Connection connection) { //Constructor
        this.connection = connection; //Recibe una coneccion valida.
        this.users = new ArrayList<>(); 
    }
    
    public List<User> getUsers(String query) { //Obtiene los usuarios seleccionados de la DB.
        users.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String username = result.getString("Username");
                String cui = result.getString("CUI");
                String password = result.getString("Password");
                User user = new User(username, cui, password);
                users.add(user);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
    
    public List<Employee> getEmployees(String query) { //Obtiene los empleados seleccionados de la DB.
        employees.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String cui = result.getString("CUI");
                int idArea = result.getInt("IdArea");
                String name = result.getString("Name");
                String lastName = result.getString("LastName");
                double salary = result.getDouble("salary");
                Employee employee = new Employee(cui, idArea, name,lastName, salary);
                employees.add(employee);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return employees;
    }
    
    public Object getSelectedEmployee(String username, String cui) { //Obtiene el usuario seleccionado de la lista.
        if(cui.equals("")) {
            User user;
            String userQuery = SELECT_USER_QUERY + username + "';";
            this.users = getUsers(userQuery);
            if(this.users.isEmpty()) {
                user = null;
                return user;
            } else {
                user = this.users.get(0);
                return user;
            } 
        } else if(username.equals("")) {
            Employee employee;
            String userQuery = SELECT_EMPLOYEE_QUERY + cui + "';";
            this.employees = getEmployees(userQuery);
            if(this.employees.isEmpty()) {
                employee = null;
                return employee;
            } else {
                employee = this.employees.get(0);
                return employee;
            } 
        } else {
            return null;
        }       
    }
    
    //Valida que la contrasena corresponda al username que se esta ingresando.
    public User validateSignIn(String username, String password) throws Exception{
        User user = (User) getSelectedEmployee(username, "");
        if(user.getPassword().equals(password)) {
            return user;
        } else {
            throw new Exception("Password Incorrecto, intentelo de nuevo");
        }
    }
    
    public void addUser(User user) throws Exception { //Agrega un usuario a la DB.
        try {
            String query = ("INSERT INTO User (Username, CUI, Password) VALUES (?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, user.getUsername());
            object.setString(2, user.getCui());
            object.setString(3, user.getPassword());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Este usuario ya existe, debe elegir uno nuevo.");
        }
    }
}
