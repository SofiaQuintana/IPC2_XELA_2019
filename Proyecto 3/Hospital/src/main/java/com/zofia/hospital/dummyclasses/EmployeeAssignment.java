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
public class EmployeeAssignment {
    private String idAssignment;
    private String employeeCui;
    private String idBill;
    private Date date;
    private boolean active;

    public EmployeeAssignment(HttpServletRequest request, String idBill) {
        int random = (int) (Math.random()*1000 + 1);
        this.employeeCui = request.getParameter("doctor");
        this.idBill = idBill;
        this.idAssignment = random + "-" + employeeCui+ "-" + idBill;
        this.date = Date.valueOf(request.getParameter("date"));
        this.active = true;
    }

    public EmployeeAssignment(String idAssignment, String employeeCui, String idBill, Date date, boolean active) {
        this.idAssignment = idAssignment;
        this.employeeCui = employeeCui;
        this.idBill = idBill;
        this.date = date;
        this.active = active;
    }

    public String getIdAssignment() {
        return idAssignment;
    }

    public void setIdAssignment(String idAssignment) {
        this.idAssignment = idAssignment;
    }

    public String getEmployeeCui() {
        return employeeCui;
    }

    public void setEmployeeCui(String employeeCui) {
        this.employeeCui = employeeCui;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
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
