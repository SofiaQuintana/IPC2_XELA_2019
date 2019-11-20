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
public class Specialist {
    private String cui;
    private String name;
    private String lastName;
    private boolean active;

    public Specialist(HttpServletRequest request) {
        this.cui = request.getParameter("cui");
        this.name = request.getParameter("name");
        this.lastName = request.getParameter("lastName");
        this.active = true;
    }
    
    public Specialist(String cui, String name, String lastName, boolean active) {
        this.cui = cui;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
