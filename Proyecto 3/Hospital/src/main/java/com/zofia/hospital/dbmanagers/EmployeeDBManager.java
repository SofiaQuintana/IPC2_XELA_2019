/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dbmanagers;

import com.zofia.hospital.dummyclasses.Area;
import com.zofia.hospital.dummyclasses.Employee;
import com.zofia.hospital.dummyclasses.User;
import com.zofia.hospital.dummyclasses.WorkingRecord;
import com.zofia.hospital.exceptions.BlankSpaceException;
import com.zofia.hospital.exceptions.DuplicatedArea;
import com.zofia.hospital.exceptions.EmployeeWithRegisteredUser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zofia
 */
public class EmployeeDBManager {
    private Connection connection; //Coneccion a la DB.
    private List<User> users; //Lista de usuarios vacia.
    private List<Employee> employees; //Lista de empleados vacia.
    private List<Area> areas; //Lista de areas vacia.
    private List<WorkingRecord> records; //Lista de records vacia.
    private List<String> areaNames; //Lista de nombres de areas.
    private static final String SELECT_USER_QUERY = "SELECT * FROM User WHERE Username = '";
    private static final String SELECT_ALL_USERS = "SELECT * FROM User;";
    private static final String SELECT_EMPLOYEE_QUERY = "SELECT * FROM Employee WHERE CUI = '";
    private static final String SELECT_AREAS_QUERY = "SELECT * FROM Area WHERE Name = '";
    private static final String SELECT_AREA_DESC = "SELECT * FROM Area ORDER BY IdArea DESC;";
    private static final String SELECT_ALL_RECORDS = "SELECT * FROM WorkingRecord WHERE IdRecord LIKE '%Despido%' OR IdRecord LIKE '%Renuncia%';";
    
    public EmployeeDBManager(Connection connection) { //Constructor
        this.connection = connection; //Recibe una coneccion valida.
        this.users = new ArrayList<>(); 
        this.employees = new ArrayList<>();
        this.areas = new ArrayList<>();
        this.records = new ArrayList<>();
        this.areaNames = new ArrayList<>();
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
                double salary = result.getDouble("Salary");
                double discount = result.getDouble("Discount");
                Employee employee = new Employee(cui, idArea, name,lastName, salary, discount);
                employees.add(employee);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return employees;
    }
    
    public List<Area> getAreas(String query) { //Obtiene las areas seleccionados de la DB.
        areas.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                int idArea = result.getInt("IdArea");
                String name = result.getString("Name");
                Area area = new Area(idArea, name);
                areas.add(area);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return areas;
    }
    
    public List<WorkingRecord> getWorkingRecords(String query) { //Obtiene los registros seleccionados de la DB.
        records.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
                while(result.next()) {
                String idRecord = result.getString("IdRecord");
                String cui = result.getString("CUI");
                String description = result.getString("Description");
                Date date = result.getDate("Date");
                LocalDate parsedDate = date.toLocalDate();
                WorkingRecord record = new WorkingRecord(idRecord, cui, description, parsedDate);
                records.add(record);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return records;
    }
    
    public List<String> getAreaName(String query) { //Obtiene los nombres de areas de la DB.
        areaNames.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String name = result.getString("Name");
                areaNames.add(name);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return areaNames;
    }
    
    public Object getSelectedEmployee(String username, String cui) { //Obtiene el usuario seleccionado de la lista.
        if(cui.equals("")) { //Retorna un usuario
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
        } else if(username.equals("")) { //Retorna un empleado
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
    
    public void addEmployee(Employee employee) throws Exception { //Agrega un empleado a la DB.
        try {
            String query = ("INSERT INTO Employee (CUI, IdArea, Name, LastName, Salary, Discount) VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, employee.getCui());
            object.setInt(2, employee.getIdArea());
            object.setString(3, employee.getName());
            object.setString(4, employee.getLastName());
            object.setDouble(5, employee.getSalary());
            object.setDouble(6, employee.getDiscount());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Este empleado ya existe, verifique el cui.");
        }
    }
    
    public void addRecord(WorkingRecord record, Date date) throws Exception { //Agrega un record al historial de trabajo de un usuario en la DB.
        try {
            String query = ("INSERT INTO WorkingRecord (IdRecord, CUI, Description, Date) VALUES (?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, record.getIdRecord());
            object.setString(2, record.getCui());
            object.setString(3, record.getDescription());
            object.setDate(4, date);
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Este registro ya existe, debe elegir uno nuevo.");
        }
    }
    
    public void addArea(Area area) throws Exception { //Agrega un area nueva a la DB.
        try {
            String query = ("INSERT INTO Area (IdArea, Name) VALUES (?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setInt(1, area.getIdArea());
            object.setString(2, area.getName());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Este usuario ya existe, debe elegir uno nuevo.");
        }
    }
        
    //Valida la informacion ingresada en el form, lanza excepciones o agrega el nuevo empleado a la DB.
    public void validateEmployeeInformation(Employee employee) throws BlankSpaceException, Exception  {
        if(employee.getName().equals("") || employee.getName().equals(" ") || 
                employee.getLastName().equals("") || employee.getLastName().equals(" ")
                || employee.getCui().equals("") || employee.getCui().equals(" ")) {
            throw new BlankSpaceException("Ingrese todos los campos correctamente");
        } else {
            addEmployee(employee);
        }
    }
    
    //Obtiene el ID del area que ha sido seleccionada en el form.
    public int getSelectedArea(String name) {
        Area area;
        int areaId;
        String query = SELECT_AREAS_QUERY + name + "';";
        this.areas = getAreas(query);
        area = this.areas.get(0);
        areaId = area.getIdArea();
        return areaId;
    }
    
    //Obtiene el ultimo numero de ID creado en la DB.
    public int getLatestIdArea() {
        Area area;
        int areaId;
        this.areas = getAreas(SELECT_AREA_DESC);
        area = this.areas.get(0);
        areaId = area.getIdArea();
        return areaId;
    }
    
    //Verifica que el nombre del area, que esta siendo ingresado, no se encuentre en la DB.
    public boolean verifyAreaName(String name) throws BlankSpaceException, DuplicatedArea {
        boolean approved;
        if(name.equals("") || name.equals(" ")) {
            throw new BlankSpaceException("Ingrese un nombre."); //Lanza excepcion en caso de que no se ingrese algun input.
        } else {
            this.areas = getAreas(SELECT_AREA_DESC);
            for(Area area : areas) {
                if(area.getName().equalsIgnoreCase(name)) {
                    throw new DuplicatedArea("Esta area se encuentra registrada, ingrese un nombre valido."); 
                    //Lanza excepcion en caso de que el nombre se duplique en la db.
                }
            }
            approved = true;
            return approved; //Aprobado.
        }
    }
    
    //Valida la informacion ingresada por el usuario.
    public void validateUserInformation(User user) throws BlankSpaceException, EmployeeWithRegisteredUser, Exception {       
        if(user.getUsername().equals("") || user.getUsername().equals(" ") || 
                user.getPassword().equals("") || user.getPassword().equals(" ")) {
            throw new BlankSpaceException("Ingrese todos los campos correctamente.");
        } 
        this.users = getUsers(SELECT_ALL_USERS);
        for(User temporal : users) {
            if(user.getCui().equals(temporal.getCui())) {
                throw new EmployeeWithRegisteredUser("Este empleado ya tiene un usuario registrado.");
            }
        }
    }
    
    //Filtra a los empleados que pueden tener un usuario en el sistema.
    public List<Employee> filterEmployees(String query) {
        this.employees = getEmployees(query);
        this.records = getWorkingRecords(SELECT_ALL_RECORDS);       
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            for(WorkingRecord record : records) {
                if(record.getCui().equals(employee.getCui())) {
                    employees.remove(i);
                }
            }
        }       
        return this.employees;
    }
        
    //Verifica que el aumento ingresado sea mayor al sueldo anterior del empleado. 
    public void validateSalaryRise(double salary, Employee employee) throws Exception {
        if(salary < employee.getSalary()) {
            throw new Exception("El nuevo sueldo es menor al anterior, ingrese un aumento valido.");
        } else if(salary == employee.getSalary()) {
            throw new Exception("El nuevo sueldo ingresado es igual al anterior, verifique e ingrese un aumento valido");
        }
    }
}
