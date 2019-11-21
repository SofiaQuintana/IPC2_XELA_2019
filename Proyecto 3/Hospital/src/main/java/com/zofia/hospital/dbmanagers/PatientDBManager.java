/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dbmanagers;

import com.zofia.hospital.dummyclasses.EmployeeAssignment;
import com.zofia.hospital.dummyclasses.MedicalConsultation;
import com.zofia.hospital.dummyclasses.Patient;
import com.zofia.hospital.dummyclasses.PatientBill;
import com.zofia.hospital.dummyclasses.RoomAssignment;
import com.zofia.hospital.dummyclasses.SpecialistAssignment;
import com.zofia.hospital.dummyclasses.Surgery;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zofia
 */
public class PatientDBManager {
    private Connection connection; //Coneccion a la db.
    private List<Patient> patients; //Lista vacia de pacientes.
    private List<RoomAssignment> roomAssignments; //Lista vacia de asignaciones a cuartos.
    private List<PatientBill> bills; //Lista vacia de facturas.
    private List<MedicalConsultation> consultations; //Lista de consultas vacia; 
    private List<EmployeeAssignment> employees; //Lista de empleados asignados vacia; 
    private List<SpecialistAssignment> specialists; //Lista de especialistas asignados vacia; 
    private List<Surgery> surgeries; //Lista de cirugias vacia; 
    private static final String SELECT_PATIENT = "SELECT * FROM Patient WHERE CUI = '";
    
    public PatientDBManager(Connection connection) {
        this.connection = connection;
        this.patients = new ArrayList<>();
        this.roomAssignments = new ArrayList<>();
        this.bills = new ArrayList<>();
        this.consultations = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.specialists = new ArrayList<>();
        this.surgeries = new ArrayList<>();
    }
    
    public List<EmployeeAssignment> getAssignedEmployees(String query) { //Obtiene los empleados asignados seleccionadas de la DB.
        employees.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String assignmentId = result.getString("IdAssignment");
                String cui = result.getString("EmployeeCUI");
                String bill = result.getString("IdBill");
                Date date = result.getDate("Date");
                boolean active = result.getBoolean("Active");
                EmployeeAssignment assignment = new EmployeeAssignment(assignmentId, cui, bill, date, active);
                employees.add(assignment);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return employees;
    }
    
    public List<SpecialistAssignment> getAssignedSpecialists(String query) { //Obtiene los especialistas asignados seleccionadas de la DB.
        specialists.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String id = result.getString("IdAssignment");
                String cui = result.getString("SpecialistCUI");
                String bill = result.getString("IdBill");
                double payment = result.getDouble("SpecialistPayment");
                Date date = result.getDate("Date");
                boolean active = result.getBoolean("Active");
                SpecialistAssignment assigned = new SpecialistAssignment(id, cui, bill, payment, date, active);
                specialists.add(assigned);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return specialists;
    }
    
    public List<Surgery> getSurgeries(String query) { //Obtiene cirugias seleccionadas de la DB.
        surgeries.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String id = result.getString("IdSurgery");
                String bill = result.getString("IdBill");
                Date date = result.getDate("Date");
                boolean active = result.getBoolean("Active");
                double price = result.getDouble("SurgeryPrice");
                double cost = result.getDouble("SurgeryCost");
                Surgery surgery = new Surgery(id, bill, date, active, price, cost);
                surgeries.add(surgery);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return surgeries;
    }
    
    public List<Patient> getPatients(String query) { //Obtiene los pacientes seleccionadas de la DB.
        patients.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String cui = result.getString("CUI");
                String name = result.getString("Name");
                String lastName = result.getString("LastName");
                Patient purchase = new Patient(cui, name, lastName);
                patients.add(purchase);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return patients;
    }
    
    public List<RoomAssignment> getRoomAssignments(String query) { //Obtiene las asignaciones de habitacion seleccionadas de la DB.
        roomAssignments.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String idAssignment = result.getString("IdAssignment");
                String patientCui = result.getString("PatientCUI");
                int idRoom = result.getInt("IdRoom");
                boolean active = result.getBoolean("Active");
                Date initialDate = result.getDate("InitialDate");
                Date finalDate = result.getDate("EndDate");
                RoomAssignment assignment = new RoomAssignment(idAssignment, patientCui, idRoom, active, initialDate, finalDate);
                roomAssignments.add(assignment);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return roomAssignments;
    }
    
    public List<MedicalConsultation> getConsultations(String query) { //Obtiene las asignaciones de habitacion seleccionadas de la DB.
        consultations.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String idConsultation = result.getString("IdConsultation");
                String employeeCUI = result.getString("EmployeeCUI");
                String patientCUI = result.getString("PatientCUI");
                double price = result.getDouble("Price");
                Date date = result.getDate("Date");
                MedicalConsultation consultation = new MedicalConsultation(idConsultation, employeeCUI, patientCUI, price, date);
                consultations.add(consultation);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return consultations;
    }
    
    public void addConsultation(MedicalConsultation consultation) throws Exception { //Agrega una asignacion de una habitacion a la DB.
        try {
            String query = ("INSERT INTO MedicalConsultation (IdConsultation, EmployeeCUI, PatientCUI, "
                    + "Price, Date) VALUES (?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, consultation.getIdConsultation());
            object.setString(2, consultation.getEmployeeCUI());
            object.setString(3, consultation.getPatientCUI());
            object.setDouble(4, consultation.getPrice());
            object.setDate(5, consultation.getDate());
            object.execute();

        } catch(SQLException e) {
            System.out.println(e.getMessage() + e.getCause());
            throw new Exception("Esta consulta medica ya existe, verifique el id.");
        }
    }
    
    public void addRoomAssignment(RoomAssignment assignment) throws Exception { //Agrega una asignacion de una habitacion a la DB.
        try {
            String query = ("INSERT INTO RoomAssignment (IdAssignment, PatientCUI, IdRoom, "
                    + "Active, InitialDate, EndDate) VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, assignment.getIdAssignment());
            object.setString(2, assignment.getPatientCui());
            object.setInt(3, assignment.getIdRoom());
            object.setBoolean(4, assignment.isActive());
            object.setDate(5, assignment.getInitialDate());
            object.setDate(6, assignment.getFinalDate());
            object.execute();

        } catch(SQLException e) {
            System.out.println(e.getMessage() + e.getCause());
            throw new Exception("Esta asignacion de habitacion ya existe, verifique el id.");
        }
    }
    
    public List<PatientBill> getBills(String query) { //Obtiene las facturas seleccionadas de la DB.
        bills.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String idBill = result.getString("IdBill");
                String patientCui = result.getString("PatientCUI");
                boolean active = result.getBoolean("Payed");
                PatientBill bill = new PatientBill(idBill, patientCui, active);
                bills.add(bill);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bills;
    }
    
    public void addAssignedEmployee(EmployeeAssignment employee) throws Exception { 
        try {
            String query = ("INSERT INTO EmployeeAssignment (IdAssignment, EmployeeCUI, IdBill, Date, Active) VALUES (?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, employee.getIdAssignment());
            object.setString(2, employee.getEmployeeCui());
            object.setString(3, employee.getIdBill());
            object.setDate(4, employee.getDate());
            object.setBoolean(5, employee.isActive());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Esta asignacion ya ha sido registrada, verifique el id.");
        }
    }
    
    public void addAssignedSpecialist(SpecialistAssignment specialist) throws Exception {
        try {
            String query = ("INSERT INTO SpecialistAssignment (IdAssignment, SpecialistCUI, "
                    + "IdBill, SpecialistPayment, Date, Active) VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, specialist.getIdAssignment());
            object.setString(2, specialist.getCui());
            object.setString(3, specialist.getIdBill());
            object.setDouble(4, specialist.getPayment());
            object.setDate(5, specialist.getDate());
            object.setBoolean(6, specialist.isActive());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Esta asignacion ya ha sido registrada, verifique el id.");
        }
    }
    
    public void addSurgery(Surgery surgery) throws Exception { //Agrega una nueva factura a la DB.
        try {
            String query = ("INSERT INTO Surgery (IdSurgery, IdBill, Date, Active, "
                    + "SurgeryPrice, SurgeryCost) VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, surgery.getSurgeryId());
            object.setString(2, surgery.getIdBill());
            object.setDate(3, surgery.getDate());
            object.setBoolean(4, surgery.isActive());
            object.setDouble(5, surgery.getSurgeryPrice());
            object.setDouble(6, surgery.getSurgeryCost());
            object.execute();

        } catch(SQLException e) {
                throw new Exception("Esta cirugia ya ha sido registrada, verifique el id.");
        }
    }
    
    public void addBill(PatientBill bill) throws Exception { //Agrega una nueva factura a la DB.
        try {
            String query = ("INSERT INTO PatientBill (IdBill, PatientCUI, Payed) VALUES (?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, bill.getIdBill());
            object.setString(2, bill.getPatientCui());
            object.setBoolean(3, bill.isPayed());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Esta factura ya ha sido registrada, verifique el id.");
        }
    }
    
    public void addPatient(Patient patient) throws Exception { //Agrega un paciente a la DB.
        try {
            String query = ("INSERT INTO Patient (CUI, Name, LastName) VALUES (?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, patient.getCui());
            object.setString(2, patient.getName());
            object.setString(3, patient.getLastName());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Este paciente ya ha sido registrado, verifique el cui.");
        }
    }
    
    public void updatePatient(Patient patient) throws Exception { //Actualiza los datos de un paciente a la DB.
        try {
            String query = ("UPDATE Patient SET Name = ?, LastName = ? WHERE CUI = ?");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, patient.getName());
            object.setString(2, patient.getLastName());
            object.setString(3, patient.getCui());
            object.executeUpdate();

        } catch(SQLException e) {
            throw new Exception("Error al realizar la actualizacion.");
        }
    }
    
    public Object getSelectedObject(String cui, String assignmentId) { //Obtiene el usuario seleccionado de la lista.
        if(assignmentId.equals("")) { //Retorna un paciente
            Patient patient;
            String query = SELECT_PATIENT + cui + "';";
            this.patients = getPatients(query);
            if(this.patients.isEmpty()) {
                patient = null;
                return patient;
            } else {
                patient = this.patients.get(0);
                return patient;
            } 
        } else if(cui.equals("")) { //Retorna un empleado
            return null;
        } else {
            return null;
        }       
    }
}
