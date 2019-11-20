/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.servlets;

import com.zofia.hospital.dbmanagers.DoctorDBManager;
import com.zofia.hospital.dbmanagers.GeneralDBManager;
import com.zofia.hospital.dummyclasses.Room;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zofia
 */
@WebServlet("/jsp/RoomController")
public class RoomController extends HttpServlet {
    private GeneralDBManager manager = new GeneralDBManager();
    private Connection connection;
    private static final String SELECT_ALL_ROOMS = "SELECT * FROM Room";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = manager.dataBaseConnection();
        int action = Integer.valueOf(request.getParameter("action"));
        try {
            switch(action) {
                case 1:
                    showRegisters(request, response); //Muestra los registros
                break;
                case 2:
                    sendLastId(request, response); //Manda el ultimo id registrado
                    forward(request, response, "room-registration.jsp");
                break;
                case 3:
                    addRegister(request, response); //Agrega un nuevo registro
                break;
                case 4:
                    request.getSession().setAttribute("id", request.getParameter("room"));
                    forward(request, response, "room-editing.jsp");
                break;
                case 5:
                    disableRegister(request, response); //Deshabilita un registro
                break;
                case 6:
                    enableRegister(request, response); //Habilita un registro
                break;
                case 7:
                    updateRegister(request, response); //Edita el registro enviado.
                break;
            }
        } catch(IOException | ServletException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void sendLastId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoctorDBManager roomManager = new DoctorDBManager(this.connection);
        int id = roomManager.getSelectedRoom();
        id++;
        request.getSession().setAttribute("id", id);
    }
    
    protected void updateRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        double price = Double.valueOf(request.getParameter("price"));
        try {
            String query = "UPDATE Room SET MaintenancePrice = " + price + " WHERE IdRoom = " + id + ";";
            manager.updateDataBaseTable(query);
            manager.disconnect();
            response.sendRedirect("/Hospital/jsp/RoomController?action=1");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "room-registration.jsp");
        }
    }
    
    protected void enableRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("room"));
        try {
            String query = "UPDATE Room SET Active = " + true + " WHERE IdRoom = " + id + ";";
            manager.updateDataBaseTable(query);
            manager.disconnect();
            response.sendRedirect("/Hospital/jsp/RoomController?action=1");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "room-crud.jsp");
        }
    }
    
    protected void disableRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("room"));
        try {
            String query = "UPDATE Room SET Active = " + false + " WHERE IdRoom = " + id + ";"; 
            manager.updateDataBaseTable(query);
            manager.disconnect();
            response.sendRedirect("/Hospital/jsp/RoomController?action=1");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "room-crud.jsp");
        }
    }
    
    //Metodo encargado de agregar un nuevo registro de habitacion a la db.
    protected void addRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoctorDBManager roomManager = new DoctorDBManager(this.connection);
        try {
            Room room = new Room(request);
            roomManager.addRoom(room);
            manager.disconnect();
            response.sendRedirect("/Hospital/jsp/RoomController?action=1");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "room-registration.jsp");
        }
    }
    
    //Metodo encargado de obtener todos los registros relacionados a habitaciones y a mandarlos como atributo en el request
    protected void showRegisters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoctorDBManager roomManager = new DoctorDBManager(this.connection);
        request.setAttribute("rooms", roomManager.getRooms(SELECT_ALL_ROOMS));
        manager.disconnect();
        forward(request, response, "room-crud.jsp");
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
