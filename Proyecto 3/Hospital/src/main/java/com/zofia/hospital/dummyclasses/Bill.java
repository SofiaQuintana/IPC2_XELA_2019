/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dummyclasses;

import java.sql.Date;

/**
 *
 * @author zofia
 */
public class Bill {
    private String cui;
    private String room;
    private String idBill;
    private String idAssignment;
    private String idMedication;
    private String specialistAssignment;
    private String idSurgery;
    private Date initialDate;
    private Date endDate;

    public Bill(String cui, String room, String idBill, String idAssignment, String idMedication, String specialistAssignment, String idSurgery, Date initialDate, Date endDate) {
        this.cui = cui;
        this.room = room;
        this.idBill = idBill;
        this.idAssignment = idAssignment;
        this.idMedication = idMedication;
        this.specialistAssignment = specialistAssignment;
        this.idSurgery = idSurgery;
        this.initialDate = initialDate;
        this.endDate = endDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getIdAssignment() {
        return idAssignment;
    }

    public void setIdAssignment(String idAssignment) {
        this.idAssignment = idAssignment;
    }

    public String getIdMedication() {
        return idMedication;
    }

    public void setIdMedication(String idMedication) {
        this.idMedication = idMedication;
    }

    public String getSpecialistAssignment() {
        return specialistAssignment;
    }

    public void setSpecialistAssignment(String specialistAssignment) {
        this.specialistAssignment = specialistAssignment;
    }

    public String getIdSurgery() {
        return idSurgery;
    }

    public void setIdSurgery(String idSurgery) {
        this.idSurgery = idSurgery;
    }
    
}
