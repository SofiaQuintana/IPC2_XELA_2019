/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dbmanagers;

import com.zofia.hospital.dummyclasses.Room;
import com.zofia.hospital.dummyclasses.Specialist;
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
public class DoctorDBManager {
    private Connection connection;  //Coneccion a la DB.
    private List<Specialist> specialists; //Lista de especialistas vacia.
    private List<Room> rooms; //Lista de habitaciones vacia.
    private static final String SELECT_ALL_ROOMS_DESC = "SELECT * FROM Room ORDER BY IdRoom DESC;";

    
    public DoctorDBManager(Connection connection) {
        this.connection = connection;
        this.specialists = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }
    
    public List<Specialist> getSpecialists(String query) { //Obtiene los especialistas seleccionados de la DB.
        specialists.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String cui = result.getString("CUI");
                String name = result.getString("Name");
                String lastName = result.getString("LastName");
                boolean active = result.getBoolean("Active");
                Specialist specialist = new Specialist(cui, name,lastName, active);
                specialists.add(specialist);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return specialists;
    }
    
    public List<Room> getRooms(String query) { //Obtiene las habitaciones seleccionadas de la DB.
        rooms.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                int idRoom = result.getInt("IdRoom");
                Double maintenancePrice = result.getDouble("MaintenancePrice");
                boolean available = result.getBoolean("Available");
                boolean active = result.getBoolean("Active");
                Room room = new Room(idRoom, maintenancePrice, available, active);
                rooms.add(room);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rooms;
    }
    
    public void addRoom(Room room) throws Exception { //Agrega una habitacion a la DB.
        try {
            String query = ("INSERT INTO Room (IdRoom, MaintenancePrice, Available, Active) VALUES (?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setInt(1, room.getIdRoom());
            object.setDouble(2, room.getMaintenancePrice());
            object.setBoolean(3, room.isAvailable());
            object.setBoolean(4, room.isActive());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Esta habitacion ya ha sido ingresada, verifique el id.");
        }
    }
    
    public void addSpecialist(Specialist specialist) throws Exception { //Agrega un especialista a la DB.
        try {
            String query = ("INSERT INTO Specialist (CUI, Name, LastName, Active) VALUES (?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, specialist.getCui());
            object.setString(2, specialist.getName());
            object.setString(3, specialist.getLastName());
            object.setBoolean(4, specialist.isActive());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Este especialista ya ha sido ingresado, verifique el cui.");
        }
    }
    
    //Obtiene el ultimo valor del identificador de habitaciones registrado.
    public int getSelectedRoom() {
        Room room;
        int idRoom;
        this.rooms = getRooms(SELECT_ALL_ROOMS_DESC);
        room = this.rooms.get(0);
        idRoom = room.getIdRoom();
        return idRoom;
    }

}
