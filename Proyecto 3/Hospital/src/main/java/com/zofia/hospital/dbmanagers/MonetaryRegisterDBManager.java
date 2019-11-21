/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dbmanagers;

import com.zofia.hospital.dummyclasses.MedicinePurchase;
import com.zofia.hospital.dummyclasses.MonetaryRegistration;
import com.zofia.hospital.dummyclasses.Rate;
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
public class MonetaryRegisterDBManager {
    private Connection connection; //Coneccion a la db.
    private List<MedicinePurchase> purchasings; //Lista de compras vacia.
    private List<MonetaryRegistration> registrations; //Lista de registros monetarios vacia.
    private Rate rate; //tarifario, vacio.
    private static final String SELECT_ALL_REGISTRATIONS_DESC = "SELECT * FROM MonetaryRegistration ORDER BY RegistrationId DESC;";

    public MonetaryRegisterDBManager(Connection connection) {
        this.connection = connection;
        this.purchasings = new ArrayList<>();
        this.registrations = new ArrayList<>();
    }
    
    public List<MedicinePurchase> getPurchasings(String query) { //Obtiene las compras seleccionadas de la DB.
        purchasings.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String idPurchase = result.getString("IdPurchase");
                String idMedicine = result.getString("IdMedicine");
                String employeeCui = result.getString("EmployeeCUI");
                int amount = result.getInt("Amount");
                double price = result.getDouble("PurchasingPrice");
                Date date = result.getDate("Date");
                MedicinePurchase purchase = new MedicinePurchase(idPurchase, idMedicine, employeeCui, amount, price, date);
                purchasings.add(purchase);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return purchasings;
    }
    
    public List<MonetaryRegistration> getRegistrations(String query) { //Obtiene los registros monetarios seleccionadas de la DB.
        registrations.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                int registrationId = result.getInt("RegistrationId");
                String paymentId = result.getString("PaymentId");
                String idSale = result.getString("IdSale");
                String idPurchase = result.getString("IdPurchase");
                String idMedication = result.getString("IdMedication");
                String idSpecialistAssignment = result.getString("IdSpecialistAssignment");
                int type = result.getInt("Type");
                MonetaryRegistration registration = new MonetaryRegistration(registrationId, paymentId, 
                        idSale, idPurchase, idMedication, idSpecialistAssignment, type);
                registrations.add(registration);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return registrations;
    }
    
    public Rate getRate(String query) { //Obtiene las compras seleccionadas de la DB.
        rate = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                int idRate = result.getInt("IdRate");
                double surgeriesPrice = result.getDouble("SurgeriesPrice");
                double surgeriesCost = result.getDouble("SurgeriesCost");
                double specialistPayment = result.getDouble("SpecialistPayment");
                double roomStayPrice = result.getDouble("RoomStayPrice");
                double consultationPrice = result.getDouble("ConsultationPrice");
                
                this.rate = new Rate(idRate, surgeriesPrice, surgeriesCost, specialistPayment, roomStayPrice, consultationPrice);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rate;
    }
    
    public void updateRate(double value, String tupla) throws Exception { //Actualiza el tarifario de la DB.
        try {
            String query = "UPDATE Rate SET " + tupla + " = ? WHERE IdRate = 1";
            PreparedStatement object = connection.prepareStatement(query);
            object.setDouble(1, value);
            object.executeUpdate();

        } catch(SQLException e) {
            throw new Exception("Error al realizar la actualizacion.");
        }
    }
    
    public void addRegister(MonetaryRegistration registration) throws Exception { //Agrega un registro monetario a la DB.
        try {
            String query = ("INSERT INTO MonetaryRegistration (RegistrationId, PaymentId, IdSale, "
                    + "IdPurchase, IdMedication, IdSpecialistAssignment, Type) VALUES (?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setInt(1, registration.getRegistrationId());
            object.setString(2, registration.getPaymentId());
            object.setString(3, registration.getIdSale());
            object.setString(4, registration.getIdPurchase());
            object.setString(5, registration.getIdMedication());
            object.setString(6, registration.getIdSpecialistAssignment());
            object.setInt(7, registration.getType());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Esta registro monetario ya existe, verifique el id.");
        }
    }
    
    public void addPurchase(MedicinePurchase purchase) throws Exception { //Agrega una compra a la DB.
        try {
            String query = ("INSERT INTO MedicinePurchase (IdPurchase, IdMedicine, "
                    + "EmployeeCUI, Amount, PurchasingPrice, Date) VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, purchase.getIdPurchase());
            object.setString(2, purchase.getIdMedicine());
            object.setString(3, purchase.getEmployeeCui());
            object.setInt(4, purchase.getAmount());
            object.setDouble(5, purchase.getPurchasingPrice());
            object.setDate(6, purchase.getDate());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Esta compra ya ha sido registrada, verifique el id.");
        }
    }

    //Obtiene el ultimo valor del identificador de registros monetarios.
    public int getSelectedRegistration() {
        MonetaryRegistration registration;
        int registrationId;
        this.registrations = getRegistrations(SELECT_ALL_REGISTRATIONS_DESC);
        if(this.registrations.isEmpty()) {
            registrationId = 1;
        } else {
            registration = this.registrations.get(0);       
            registrationId = registration.getRegistrationId();
            registrationId++;
        }
        return registrationId;
    }
}
