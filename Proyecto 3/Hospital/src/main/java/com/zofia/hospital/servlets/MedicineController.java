/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.servlets;

import com.zofia.hospital.dbmanagers.DoctorDBManager;
import com.zofia.hospital.dbmanagers.EmployeeDBManager;
import com.zofia.hospital.dbmanagers.GeneralDBManager;
import com.zofia.hospital.dbmanagers.MonetaryRegisterDBManager;
import com.zofia.hospital.dummyclasses.Medicine;
import com.zofia.hospital.dummyclasses.MedicinePurchase;
import com.zofia.hospital.dummyclasses.MonetaryRegistration;
import com.zofia.hospital.reports.ReportsController;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
@WebServlet("/jsp/MedicineController")
public class MedicineController extends HttpServlet {
    private GeneralDBManager manager = new GeneralDBManager();
    private Connection connection;
    private static final String SELECT_ALL_MEDICINES = "SELECT * FROM Medicine;";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM Employee WHERE IdArea = 3;";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = manager.dataBaseConnection();
        int action = Integer.valueOf(request.getParameter("action"));
        try {
            switch(action) {
                case 1: //Para mostrar las alertas para compra de medicamentos.
                    showRegisters(request, response);
                    response.sendRedirect("/Hospital/jsp/pharmacy-home.jsp");
                break;
                case 2: //Registra un medicamento en el sistema.
                    addRegister(request, response);
                break;
                case 3: //Para mostrar todos los medicamentos registrados.
                    showRegisters(request, response);
                    response.sendRedirect("/Hospital/jsp/medicine-crud.jsp");
                break;
                case 4: //Para mandar datos al jsp de edicion medicamentos.
                    getRegister(request, response);
                    forward(request, response, "medicine-editing.jsp");
                break;
                case 5: //Para actualizar un registro de la db.
                    updateRegister(request, response);
                break;
                case 6: //Para mandar datos al frontEnd de ingreso de compra.
                    getRegister(request, response);
                    getEmployees(request, response);
                    forward(request, response, "medicine-purchasing.jsp");
                break;
                case 7: //Para registrar una venta de medicamentos.
                    addPurchasingRegister(request, response);
                    response.sendRedirect("/Hospital/jsp/MedicineController?action=3");
                break;
                case 9:
                    
                break;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void medicineReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportsController report = new ReportsController();
        DoctorDBManager medicineManager = new DoctorDBManager(this.connection);
        //report.createReport(, list);
        
    }
    
    protected void addPurchasingRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MonetaryRegisterDBManager purchaseManager = new MonetaryRegisterDBManager(this.connection);
        try {
            Date date = Date.valueOf(request.getParameter("date"));
            MedicinePurchase purchase = new MedicinePurchase(request, date);
            int registrationId = purchaseManager.getSelectedRegistration();
            MonetaryRegistration registration = new MonetaryRegistration(registrationId, null, null, purchase.getIdPurchase(), null, null, 1);
            purchaseManager.addPurchase(purchase);
            purchaseManager.addRegister(registration);
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "medicine-purchasing.jsp");

        }      
    }
    
    protected void getEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDBManager employeeManager = new EmployeeDBManager(this.connection);
        request.getSession().setAttribute("employees", employeeManager.filterEmployees(SELECT_ALL_EMPLOYEES));
        manager.disconnect();
    }
    
    //Encargado de recibir los valores editados para su actualizacion en el sistema.
    protected void updateRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("medicineName");
        try {
            int minimum = Integer.valueOf(request.getParameter("minimum"));
            int available = Integer.valueOf(request.getParameter("amount"));
            double price = Double.valueOf(request.getParameter("price"));
            String query = "UPDATE Medicine SET Name = '" + name + "', Amount = " 
                    + available + ", MinimumAmount = " + minimum + ", Price = " + price + " WHERE IdMedicine = '" + id + "';";
            this.manager.updateDataBaseTable(query);
            this.manager.disconnect();
            showRegisters(request, response);
            response.sendRedirect("/Hospital/jsp/MedicineController?action=3");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "medicine-editing.jsp");
        }
    }
    
    //Encargado de obtener un registro para mandarlo hacia el frontend.
    protected void getRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        DoctorDBManager medicineManager = new DoctorDBManager(this.connection);
        Medicine medicine = (Medicine) medicineManager.getSelectedObject(request.getParameter("id"), "");
        request.getSession().setAttribute("medicine", medicine);
    }
    
    //Encargado de realizar los datos que se han obtenido del FrontEnd y de agregar un nuevo registro de medicina a la db.
    protected void addRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoctorDBManager medicineManager = new DoctorDBManager(this.connection);
        try {
            if(request.getParameter("minimum").contains(".")) { //No deja ingresar valores decimales en valores que deben ser enteros.
                throw new Exception("Ingrese solo valores enteros.");
            }
            Medicine medicine = new Medicine(request);
            medicineManager.addMedicines(medicine);
            showRegisters(request, response);
            response.sendRedirect("/Hospital/jsp/medicine-crud.jsp");
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "medicine-registration.jsp");
        }
    }
    
    //se encarga de obtener y mandar como atributo los registros de medicamentos de la db.
    protected void showRegisters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoctorDBManager medicineManager = new DoctorDBManager(this.connection);
        request.getSession().setAttribute("medicines", medicineManager.getMedicines(SELECT_ALL_MEDICINES));
        manager.disconnect();
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
    
}
