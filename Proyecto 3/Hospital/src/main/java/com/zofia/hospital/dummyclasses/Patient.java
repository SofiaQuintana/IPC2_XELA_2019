/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dummyclasses;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zofia
 */
public class Patient {
    private String cui;
    private String name;
    private String lastName;

    public Patient(HttpServletRequest request) {
        this.cui = request.getParameter("patientCui");
        this.name = request.getParameter("patientName");
        this.lastName = request.getParameter("patientLastName");
    }

    public Patient(String cui, String name, String lastName) {
        this.cui = cui;
        this.name = name;
        this.lastName = lastName;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
