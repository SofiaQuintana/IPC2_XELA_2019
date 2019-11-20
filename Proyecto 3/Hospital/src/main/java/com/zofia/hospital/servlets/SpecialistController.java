/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.servlets;

import com.zofia.hospital.dbmanagers.DoctorDBManager;
import com.zofia.hospital.dbmanagers.GeneralDBManager;
import com.zofia.hospital.dummyclasses.Specialist;
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
@WebServlet("/jsp/SpecialistController")
public class SpecialistController extends HttpServlet {
    private GeneralDBManager manager = new GeneralDBManager();
    private Connection connection;
    private static final String SELECT_ALL_SPECIALISTS = "SELECT * FROM Specialist;";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = manager.dataBaseConnection();
        int action = Integer.valueOf(request.getParameter("action"));
        try {
            switch(action) {
                case 1:
                    showRegisters(request, response);
                break;
                case 2:
                    forward(request, response, "specialist-registration.jsp");
                break;
                case 3:
                    registerSpecialist(request, response);
                break;
                case 4:
                break;
            }
        } catch(Exception e) {
            
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    //Metodo encargado de agregar el registro de un nuevo especialista en la db.
    protected void registerSpecialist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoctorDBManager specialistManager = new DoctorDBManager(this.connection);
        try {
            Specialist specialist = new Specialist(request);
            specialistManager.addSpecialist(specialist);
            showRegisters(request, response);
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "specialist-registration.jsp");
        }
    }
    
    //Metodo encargado de obtener todos los registros de especialistas y redirigir a la pagina correspondiente.
    protected void showRegisters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoctorDBManager specialistManager = new DoctorDBManager(this.connection);
        request.setAttribute("specialists", specialistManager.getSpecialists(SELECT_ALL_SPECIALISTS));
        manager.disconnect();
        forward(request, response, "specialist-crud.jsp");
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
