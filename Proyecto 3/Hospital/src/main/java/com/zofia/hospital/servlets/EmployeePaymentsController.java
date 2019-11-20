/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.servlets;

import com.zofia.hospital.dbmanagers.EmployeeDBManager;
import com.zofia.hospital.dbmanagers.GeneralDBManager;
import com.zofia.hospital.dummyclasses.Employee;
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
@WebServlet("/jsp/EmployeePaymentsController")
public class EmployeePaymentsController extends HttpServlet {
    private GeneralDBManager manager = new GeneralDBManager();
    private Connection connection;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = manager.dataBaseConnection(); //Coneccion a la db.
        EmployeeDBManager employeeManager = new EmployeeDBManager(this.connection);
        try {
            double newSalary = Double.valueOf(request.getParameter("increase"));
            Employee employee = (Employee) employeeManager.getSelectedEmployee("", request.getParameter("employeeCui"));
            employeeManager.validateSalaryRise(newSalary, employee);
            String query = "UPDATE Employee SET Salary = " + newSalary 
                   + "WHERE CUI = '" + employee.getCui() + "';"; //Query para actualizar el salario del empleado seleccionado.           
            LocalDate date = LocalDate.parse(request.getParameter("date")); //Fecha
            WorkingRecord record = new WorkingRecord(Math.random() + "-" + employee.getCui() + "-Aumento", employee.getCui(), "Aumento", date);
            employeeManager.addRecord(record, Date.valueOf(date)); //Ingresa un nuevo registro de aumento a la db.
            manager.updateDataBaseTable(query);
            request.getSession().setAttribute("employees", employeeManager.filterEmployees()); //Manda de nuevo la lista de empleados filtrados.
            forward(request, response, "pay-rise-registration.jsp");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "pay-rise-registration.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
