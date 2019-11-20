/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.servlets;

import com.zofia.hospital.dbmanagers.EmployeeDBManager;
import com.zofia.hospital.dbmanagers.GeneralDBManager;
import com.zofia.hospital.dummyclasses.WorkingRecord;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
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
@WebServlet("/jsp/EmployeeRecordController")
public class EmployeeRecordController extends HttpServlet {
    private GeneralDBManager connectionManager = new GeneralDBManager();
    private Connection connection;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = connectionManager.dataBaseConnection(); //Coneccion a la db.
        EmployeeDBManager employeeManager = new EmployeeDBManager(this.connection);
        try {
            LocalDate date = LocalDate.parse(request.getParameter("date")); 
            WorkingRecord record = new WorkingRecord(request, date);
            String query = "DELETE FROM User WHERE CUI = '" + record.getCui() + "';"; //Query para eliminar el usuario de la persona despedida/retirada.
            employeeManager.addRecord(record, Date.valueOf(date)); //Agrega el registro a la DB.
            connectionManager.updateDataBaseTable(query); //Actualiza la db.
            request.getSession().setAttribute("employees", employeeManager.filterEmployees()); //Manda de nuevo la lista de empleados filtrados.
            forward(request, response, "quit-fired-registration.jsp");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "quit-fired-registration.jsp");
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RESERVADO PARA VACACIONES DE USUARIO.
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
