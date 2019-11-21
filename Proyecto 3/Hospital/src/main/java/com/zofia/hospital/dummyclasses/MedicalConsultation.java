/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dummyclasses;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zofia
 */
public class MedicalConsultation {
    private String idConsultation;
    private String employeeCUI;
    private String patientCUI;
    private double price;
    private Date date;

    public MedicalConsultation(HttpServletRequest request, double price) {
        int random = (int) (Math.random()*1000 + 1);
        this.employeeCUI = request.getParameter("doctor");
        this.patientCUI = request.getParameter("patientCui");
        this.idConsultation = random + "-Consulta-" + employeeCUI + "-" + patientCUI;
        this.price = price;
        this.date = Date.valueOf(request.getParameter("date"));
    }

    public MedicalConsultation(String idConsultation, String employeeCUI, String patientCUI, double price, Date date) {
        this.idConsultation = idConsultation;
        this.employeeCUI = employeeCUI;
        this.patientCUI = patientCUI;
        this.price = price;
        this.date = date;
    }

    public String getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(String idConsultation) {
        this.idConsultation = idConsultation;
    }

    public String getEmployeeCUI() {
        return employeeCUI;
    }

    public void setEmployeeCUI(String employeeCUI) {
        this.employeeCUI = employeeCUI;
    }

    public String getPatientCUI() {
        return patientCUI;
    }

    public void setPatientCUI(String patientCUI) {
        this.patientCUI = patientCUI;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
