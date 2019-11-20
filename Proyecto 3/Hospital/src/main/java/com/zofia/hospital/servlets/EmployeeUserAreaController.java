/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.servlets;

import com.zofia.hospital.dbmanagers.EmployeeDBManager;
import com.zofia.hospital.dbmanagers.GeneralDBManager;
import com.zofia.hospital.dummyclasses.Employee;
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
@WebServlet("/jsp/EmployeeUserAreaController")
public class EmployeeUserAreaController extends HttpServlet {
    private GeneralDBManager manager = new GeneralDBManager();
    private Connection connection;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = manager.dataBaseConnection();
        int action = Integer.valueOf(request.getParameter("action"));
        try {
            switch(action) {
                case 1:
                    getEmployee(request, response);
                break;
                case 2:
                    updateEmployeeRegister(request, response);
                break;
                case 3:
                    EmployeeDBManager employeeManager = new EmployeeDBManager(this.connection);
                    request.getSession().setAttribute("employees", employeeManager.filterEmployees());
                    response.sendRedirect("/Hospital/jsp/human-resources-home.jsp");
                break;
            }
        } catch(Exception e) {
            
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void updateEmployeeRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDBManager employeeManager = new EmployeeDBManager(this.connection);
        String cui = request.getParameter("cui");
        Employee employee = (Employee) employeeManager.getSelectedEmployee("", cui);
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        try {
            double salary = Double.valueOf(request.getParameter("salary"));
            double discount = Double.valueOf(request.getParameter("discount"));
            if((discount != 0 || discount != 0.0) && discount != employee.getDiscount()) {
                discount = discount / 100;
            }
            String query = "UPDATE Employee SET Name = '" + name +"', LastName = '" 
                    + lastName + "', Salary = " + salary + ", Discount = " + discount + " WHERE CUI = '" + cui + "';";
            manager.updateDataBaseTable(query);
            request.getSession().setAttribute("employees", employeeManager.filterEmployees());
            manager.disconnect();
            response.sendRedirect("/Hospital/jsp/human-resources-home.jsp");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "employee-editing.jsp");
        }      
    }
    
    //Obtiene el empleado seleccionado desde el frontEnd, para enviar sus datos al jsp de la edicion.
    protected void getEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDBManager employeeManager = new EmployeeDBManager(this.connection);
        Employee employee = (Employee) employeeManager.getSelectedEmployee("", request.getParameter("cui"));
        request.getSession().setAttribute("employee", employee);
        this.manager.disconnect();
        forward(request, response, "employee-editing.jsp");
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
