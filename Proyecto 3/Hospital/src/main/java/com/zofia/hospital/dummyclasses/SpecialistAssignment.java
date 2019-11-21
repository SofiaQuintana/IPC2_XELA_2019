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
public class SpecialistAssignment {
    private String idAssignment;
    private String cui;
    private String idBill;
    private double payment;
    private Date date;
    private boolean active;

    public SpecialistAssignment(HttpServletRequest request, String idBill, double payment) {
        int random = (int) (Math.random()*1000 + 1);
        this.cui = request.getParameter("specialist");
        this.idBill = idBill;
        this.idAssignment = random + "-" + cui + "-" + idBill;
        this.payment = payment;
        this.date = Date.valueOf(request.getParameter("date"));
        this.active = true;
    }

    public SpecialistAssignment(String idAssignment, String cui, String idBill, double payment, Date date, boolean active) {
        this.idAssignment = idAssignment;
        this.cui = cui;
        this.idBill = idBill;
        this.payment = payment;
        this.date = date;
        this.active = active;
    }

    public String getIdAssignment() {
        return idAssignment;
    }

    public void setIdAssignment(String idAssignment) {
        this.idAssignment = idAssignment;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
