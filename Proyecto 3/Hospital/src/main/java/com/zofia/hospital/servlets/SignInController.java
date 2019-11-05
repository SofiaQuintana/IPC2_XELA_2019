/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.servlets;

import com.zofia.hospital.dbmanagers.GeneralDBManager;
import com.zofia.hospital.dbmanagers.EmployeeDBManager;
import com.zofia.hospital.dummyclasses.Employee;
import com.zofia.hospital.dummyclasses.User;
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
@WebServlet("/SignInController")
public class SignInController extends HttpServlet{
    private GeneralDBManager db = new GeneralDBManager();
    private Connection connection;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = db.DataBaseConnection(); //Coneccion a la DB
        EmployeeDBManager employeeManager = new EmployeeDBManager(this.connection);

        try {
            User user = employeeManager.validateSignIn(request.getParameter("inputUsername"), request.getParameter("inputPassword"));
            Employee employee = (Employee) employeeManager.getSelectedEmployee(user.getUsername(), user.getCui());            
            request.getSession().setAttribute("username", user.getUsername());
            switch(employee.getIdArea()) { //Switch encargado de redirigir segun el area del usuario que este ingresando.
                case 1: //Administracion
                    forward(request, response, "manager-home.jsp");
                    
                break;
                case 2: //Recursos humanos
                    forward(request, response, "human-resources-home.jsp");
                break;
                case 3: //Farmaceutica
                    forward(request, response, "pharmaceutics-home.jsp");
                break;
                case 4: //Recepcion
                    forward(request, response, "receptionist-home.jsp");
                break;
    }

        } catch(Exception e) {

        }
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
