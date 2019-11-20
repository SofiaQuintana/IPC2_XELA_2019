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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
    private List<String> areas = new ArrayList<>();
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Formato de fecha
    private LocalDate date = LocalDate.now(); //Fecha del dia
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = db.dataBaseConnection(); //Coneccion a la DB
        EmployeeDBManager employeeManager = new EmployeeDBManager(this.connection);
        String dateString = String.valueOf(format.format(date));
        request.getSession().setAttribute("date", dateString);
        System.out.println(format.format(date));
        try {
            User user = employeeManager.validateSignIn(request.getParameter("inputUsername"), request.getParameter("inputPassword"));
            Employee employee = (Employee) employeeManager.getSelectedEmployee("", user.getCui());            
            switch(employee.getIdArea()) { //Switch encargado de redirigir segun el area del usuario que este ingresando.
                case 1: //Administracion
                    forward(request, response, "/Hospital/jsp/manager-home.jsp");
                    
                break;
                case 2: //Recursos humanos
                    this.areas = employeeManager.getAreaName("SELECT Name FROM Area;");                  
                    int areaId = employeeManager.getLatestIdArea() + 1;
                    request.getSession().setAttribute("idArea", areaId); //Manda a la sesion el ultimo id de la lista
                    request.getSession().setAttribute("employees", employeeManager.filterEmployees());
                    request.getSession().setAttribute("areas", this.areas); //Manda a la sesion la lista de areas del sistema.
                    forward(request, response, "jsp/human-resources-home.jsp");                    
                break;
                case 3: //Farmaceutica
                    forward(request, response, "/Hospital/jsp/pharmaceutics-home.jsp");
                break;
                case 4: //Recepcion
                    forward(request, response, "/Hospital/jsp/receptionist-home.jsp");
                break;
    }

        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "index.jsp");
            
        }
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
