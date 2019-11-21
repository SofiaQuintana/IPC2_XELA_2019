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
public class RoomAssignment {
    private String idAssignment;
    private String patientCui;
    private int idRoom;
    private boolean active;
    private Date initialDate;
    private Date finalDate;

    public RoomAssignment(HttpServletRequest request, Date initialDate, Date finalDate) {
        this.idAssignment = request.getParameter("patientCui") + "-" + request.getParameter("idRoom");
        this.patientCui = request.getParameter("patientCui");
        this.idRoom = Integer.valueOf(request.getParameter("idRoom"));
        this.active = true;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public RoomAssignment(String idAssignment, String patientCui, int idRoom, boolean active, Date initialDate, Date finalDate) {
        this.idAssignment = idAssignment;
        this.patientCui = patientCui;
        this.idRoom = idRoom;
        this.active = active;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public String getIdAssignment() {
        return idAssignment;
    }

    public void setIdAssignment(String idAssignment) {
        this.idAssignment = idAssignment;
    }

    public String getPatientCui() {
        return patientCui;
    }

    public void setPatientCui(String patientCui) {
        this.patientCui = patientCui;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
    
}
