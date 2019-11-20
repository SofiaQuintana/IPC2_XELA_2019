/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.servlets;

import com.zofia.hospital.dbmanagers.EmployeeDBManager;
import com.zofia.hospital.dbmanagers.GeneralDBManager;
import com.zofia.hospital.dummyclasses.Area;
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
@WebServlet("/jsp/AreaController")
public class AreaController extends HttpServlet {
    private GeneralDBManager dataBase = new GeneralDBManager();
    private Connection connection;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.dataBaseConnection();
        EmployeeDBManager employeeManager = new EmployeeDBManager(this.connection);
        try {
            int areaId = Integer.valueOf(request.getSession().getAttribute("idArea").toString());
            Area area = new Area(areaId, request.getParameter("areaName"));
            boolean approved = employeeManager.verifyAreaName(request.getParameter("areaName")); //Verifica si un nombre de area es aprobado.
            if(approved) {
                employeeManager.addArea(area); //Si es aprobada, agrega el area a la db.
                areaId++;
                request.getSession().setAttribute("idArea", areaId); //Reenvia el nuevo valor del id a la sesion.
                request.getSession().setAttribute("areas", employeeManager.getAreaName("SELECT Name FROM Area;")); 
                //Manda a la sesion la lista de areas del sistema.
            }            
            forward(request, response, "area-registration.jsp");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "area-registration.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
