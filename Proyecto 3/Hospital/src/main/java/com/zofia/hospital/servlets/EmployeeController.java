/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.servlets;

import com.zofia.hospital.dbmanagers.EmployeeDBManager;
import com.zofia.hospital.dbmanagers.GeneralDBManager;
import com.zofia.hospital.dummyclasses.Employee;
import com.zofia.hospital.dummyclasses.User;
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
@WebServlet("/jsp/EmployeeController")
public class EmployeeController extends HttpServlet {
    private GeneralDBManager manager = new GeneralDBManager();
    private Connection connection;
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM Employee;";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = manager.dataBaseConnection(); //Coneccion a la db.
        EmployeeDBManager employeeDB = new EmployeeDBManager(this.connection);
        System.out.println(request.getParameter("date") + " fechaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        try {
            int idArea = employeeDB.getSelectedArea(request.getParameter("area")); //Recibe el id del nombre del area seleccionada.
            double discount = Double.valueOf(request.getParameter("discount"));
            if(discount != 0) {
                discount = discount / 100;
            }
            
            Employee employee = new Employee(request, idArea, discount);
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            employeeDB.validateEmployeeInformation(employee);
            WorkingRecord record = new WorkingRecord(employee.getCui() + "-Contratacion", employee.getCui(), "Contratacion", date);
            employeeDB.addRecord(record, Date.valueOf(date)); //Agrega el record de contratacion a la db.
            //RECORDAR HAY QUE FORWARD HACIA ALGUN LADO.
            request.getSession().setAttribute("employees", employeeDB.filterEmployees(SELECT_ALL_EMPLOYEES)); //Manda de nuevo la lista de empleados filtrados.
            if(employee.getIdArea() == 1 || employee.getIdArea() == 2 || employee.getIdArea() == 3 
                || employee.getIdArea() == 4) {
                request.setAttribute("info", employee.getCui());
                forward(request, response, "user-registration.jsp");
            } else {
                forward(request, response, "employee-hiring.jsp");
            }             
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "employee-hiring.jsp");
        }        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = manager.dataBaseConnection(); //Coneccion a la db.
        EmployeeDBManager employeeDB = new EmployeeDBManager(this.connection);
        try {
            String cui = request.getParameter("r");
            User user = new User(request, cui);
            employeeDB.validateUserInformation(user); //Valida la informacion que se esta ingresando.
            employeeDB.addUser(user); //Agrega el usuario a la db.
            forward(request, response, "employee-hiring.jsp");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "employee-hiring.jsp");
        }
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
