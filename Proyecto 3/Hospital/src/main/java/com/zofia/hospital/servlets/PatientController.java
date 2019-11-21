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
import com.zofia.hospital.dbmanagers.PatientDBManager;
import com.zofia.hospital.dummyclasses.Bill;
import com.zofia.hospital.dummyclasses.EmployeeAssignment;
import com.zofia.hospital.dummyclasses.MedicalConsultation;
import com.zofia.hospital.dummyclasses.MonetaryRegistration;
import com.zofia.hospital.dummyclasses.Patient;
import com.zofia.hospital.dummyclasses.PatientBill;
import com.zofia.hospital.dummyclasses.Rate;
import com.zofia.hospital.dummyclasses.Room;
import com.zofia.hospital.dummyclasses.RoomAssignment;
import com.zofia.hospital.dummyclasses.SpecialistAssignment;
import com.zofia.hospital.dummyclasses.Surgery;
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
@WebServlet("/jsp/PatientController")
public class PatientController extends HttpServlet {
    private GeneralDBManager manager = new GeneralDBManager();
    private Connection connection;
    private static final String SELECT_ALL_ASSIGNMENTS = "SELECT * FROM RoomAssignment WHERE Active = 1;";
    private static final String SELECT_ALL_PATIENTS = "SELECT * FROM Patient;";
    private static final String SELECT_RATE = "SELECT * FROM Rate;";
    private static final String SELECT_DOCTORS = "SELECT * FROM Employee WHERE IdArea = 6;";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM Employee WHERE IdArea = 5 OR IdArea = 6;";
    private static final String SELECT_ESPECIALISTS = "SELECT * FROM Specialist WHERE Active = 1;";
    private static final String SELECT_BILL = "SELECT * FROM PatientBill WHERE PatientCUI = '";
    private static final String SPECIALIST_ASSIGNED = "SELECT * FROM SpecialistAssignment WHERE IdBill = '";
    private static final String SELECTED_SURGERY = "SELECT * FROM Surgery WHERE IdBill = '";
    private static final String JOIN_BILL = "SELECT p.CUI, (r.IdAssignment) Room, b.IdBill, "
            + "e.IdAssignment, m.IdMedication, (s.IdAssignment) Specialist , su.IdSurgery, r.InitialDate, "
            + "r.EndDate FROM Patient p INNER JOIN RoomAssignment r ON p.CUI = r.PatientCUI INNER JOIN PatientBill b "
            + "ON p.CUI = b.PatientCUI LEFT JOIN EmployeeAssignment e ON b.IdBill = e.IdBill LEFT JOIN Medication m "
            + "ON b.Idbill = m.IdBill LEFT JOIN SpecialistAssignment s ON b.IdBill = s.IdBill LEFT JOIN Surgery su "
            + "ON b.IdBill = su.IdBill WHERE p.CUI = '";
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = manager.dataBaseConnection();
        int action = Integer.valueOf(request.getParameter("action"));
        try {
            switch(action) {
                case 1: //Para obtener todas las habitaciones ocupadas, en el momento.
                    getAdmittedPatients(request, response);
                    response.sendRedirect("/Hospital/jsp/receptionist-home.jsp");
                break;
                case 2: //Para obtener los registros de pacientes.
                    showRegisters(request, response);
                    response.sendRedirect("/Hospital/jsp/patient-crud.jsp");
                break;
                case 3: //Para crear un nuevo paciente.
                    addPatient(request, response);
                    showRegisters(request, response);
                    response.sendRedirect("/Hospital/jsp/PatientController?action=2");
                break;
                case 4: //Para editar los datos de un paciente.
                    updatePatient(request, response);
                    showRegisters(request, response);
                    response.sendRedirect("/Hospital/jsp/PatientController?action=2");
                break;
                case 5: //Para obtener los datos del paciente a editar.
                    getPatient(request, response);
                    forward(request, response, "patient-edit.jsp");
                break;
                case 6: //Para ingresar el registro de un paciente internado y generar su cuenta hospitalaria.
                    ingresarPatient(request, response);
                    response.sendRedirect("/Hospital/jsp/patient-crud.jsp");
                break;
                case 7:
                    addConsultation(request, response);
                    response.sendRedirect("/Hospital/jsp/patient-crud.jsp");
                break;
                case 8:
                    addAssignment(request, response);
                    response.sendRedirect("/Hospital/jsp/PatientController?action=1");
                break;
                case 9:
                    addSurgery(request, response);
                    response.sendRedirect("/Hospital/jsp/PatientController?action=1");
                break;
                case 10:
                    request.setAttribute("error", true);
                    getPatient(request, response);
                    forward(request, response, "patient-crud.jsp");               
                break;
                case 11:
                    getPatient(request, response);
                    getEmployees(request, response, false);
                    forward(request, response, "consultation-registration.jsp");               
                break;
                case 13:
                    getEmployees(request, response, true);
                    getPatient(request, response);
                    forward(request, response, "employee-assignment.jsp");
                break;
                case 14:
                    getPatient(request, response);
                    forward(request, response, "surgery-registration.jsp");
                break;
                case 15:
                    obtainBill(request, response);
                    forward(request, response, "bill-patient.jsp");
                break;
                case 16:
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
    //jhhhhhhhhhhhhhhhhhhhhhhhhhh
    protected void obtainBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MonetaryRegisterDBManager monetaryManager = new MonetaryRegisterDBManager(this.connection);
        PatientDBManager patientManager = new PatientDBManager(this.connection);
        String query = JOIN_BILL + request.getParameter("id") + "';";
        Bill bill = monetaryManager.getBills(query).get(0);
        Rate rate = monetaryManager.getRate(SELECT_RATE);
        if(!bill.getSpecialistAssignment().equals("")) {
            String temp = SPECIALIST_ASSIGNED + bill.getIdBill() + "';";
            request.setAttribute("specialist", patientManager.getAssignedSpecialists(temp));
        }
        if(!bill.getIdSurgery().equals("")) {
            String temp = SELECTED_SURGERY + bill.getIdBill() + "';";
            request.setAttribute("surgery", patientManager.getSurgeries(temp));
        }
        
        request.getSession().setAttribute("bill", bill);
    }
    
    protected void addSurgery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDBManager patientManager = new PatientDBManager(this.connection);
        MonetaryRegisterDBManager monetaryManager = new MonetaryRegisterDBManager(this.connection);
        try {
            String query = SELECT_BILL + request.getParameter("patientCui") + "';";
            String id = "Surgery-" + request.getParameter("patientCui") + "-" + Date.valueOf(request.getParameter("date"));
            PatientBill bill = patientManager.getBills(query).get(0);
            Rate rate = monetaryManager.getRate(SELECT_RATE);
            Surgery surgery;
            if(request.getParameter("active").equals("true")) {
                surgery = new Surgery(id, bill.getIdBill(), Date.valueOf(request.getParameter("date")), 
                        true, rate.getSurgeriesPrice(), rate.getSurgeriesCost());
            } else {
                surgery = new Surgery(id, bill.getIdBill(), Date.valueOf(request.getParameter("date")), 
                        false, rate.getSurgeriesPrice(), rate.getSurgeriesCost());
            }
            patientManager.addSurgery(surgery);
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "surgery-registration.jsp");
        }
    }
    
    protected void addAssignment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDBManager patientManager = new PatientDBManager(this.connection);
        MonetaryRegisterDBManager monetaryManager = new MonetaryRegisterDBManager(this.connection);
        try {
            String query = SELECT_BILL + request.getParameter("patientCui") + "';";
            PatientBill bill = patientManager.getBills(query).get(0);
            EmployeeAssignment employee = new EmployeeAssignment(request, bill.getIdBill());
            patientManager.addAssignedEmployee(employee);
            if(!request.getParameter("specialist").equals("")) {
                Rate rate = monetaryManager.getRate(SELECT_RATE);
                SpecialistAssignment specialist = new SpecialistAssignment(request, bill.getIdBill(), rate.getSpecialistPayment());
                patientManager.addAssignedSpecialist(specialist);
            }
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "employee-assignment.jsp");
        }
    }
    
    protected void getAdmittedPatients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDBManager patientManager = new PatientDBManager(this.connection);
        request.getSession().setAttribute("admitted", patientManager.getRoomAssignments(SELECT_ALL_ASSIGNMENTS));
        manager.disconnect();
    }
    
    protected void showRegisters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDBManager patientManager = new PatientDBManager(this.connection);
        request.getSession().setAttribute("patients", patientManager.getPatients(SELECT_ALL_PATIENTS));
        manager.disconnect();
    }
    
    protected void addConsultation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MonetaryRegisterDBManager monetaryManager = new MonetaryRegisterDBManager(this.connection);
        PatientDBManager patientManager = new PatientDBManager(this.connection);
        try {
            Rate rate = monetaryManager.getRate(SELECT_RATE);
            MedicalConsultation consultation = new MedicalConsultation(request, rate.getConsultationPrice());
            patientManager.addConsultation(consultation);
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "consultation-registration.jsp");
        }
    }
    
    protected void addPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDBManager patientManager = new PatientDBManager(this.connection);
        try {
            Patient patient = new Patient(request);
            patientManager.addPatient(patient);
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "patient-registration.jsp");
        }
    }
    
    protected void getEmployees(HttpServletRequest request, HttpServletResponse response, boolean type) throws ServletException, IOException {
        EmployeeDBManager employeeManager = new EmployeeDBManager(this.connection);
        DoctorDBManager doctorManager = new DoctorDBManager(this.connection);
        if(type) {
            request.getSession().setAttribute("employees", employeeManager.filterEmployees(SELECT_ALL_EMPLOYEES));
            request.setAttribute("specialists", doctorManager.getSpecialists(SELECT_ESPECIALISTS));
        } else {
            request.getSession().setAttribute("employees", employeeManager.filterEmployees(SELECT_DOCTORS));
        }
    }
    
    //Encargado de obtener un registro para mandarlo hacia el frontend.
    protected void getPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDBManager patientManager = new PatientDBManager(this.connection);
        Patient patient = (Patient) patientManager.getSelectedObject(request.getParameter("id"), "");
        request.setAttribute("patient", patient);
        manager.disconnect();
    }
    
    protected void updatePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDBManager patientManager = new PatientDBManager(this.connection);
        try {
            Patient patient = new Patient(request);
            patientManager.updatePatient(patient);
        } catch(Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("message", e.getMessage()); //obtiene el mensaje del error y lo manda al frontEnd.
            forward(request, response, "patient-edit.jsp");
        }
    }
    
    protected void ingresarPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDBManager patientManager = new PatientDBManager(this.connection);
        DoctorDBManager roomManager = new DoctorDBManager(this.connection);
        Patient patient = (Patient) patientManager.getSelectedObject(request.getParameter("id"), "");
        try {
            Room room = (Room) roomManager.getSelectedObject("", "este si jaja");
            String id = patient.getCui() + "-" + room.getIdRoom();           
            RoomAssignment assignment = new RoomAssignment(id, patient.getCui(), 
                    room.getIdRoom(), true, Date.valueOf(request.getParameter("date")), null);           
            PatientBill bill = new PatientBill(id, patient.getCui(), false);
            patientManager.addRoomAssignment(assignment);
            patientManager.addBill(bill);            
        } catch(Exception e) {
            request.setAttribute("error", true);
            forward(request, response, "patient-crud.jsp");
        }
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
