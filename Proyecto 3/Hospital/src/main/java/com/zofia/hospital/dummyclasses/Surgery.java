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
public class Surgery {
    private String surgeryId;
    private String idBill;
    private Date date;
    private boolean active;
    private double surgeryCost;
    private double surgeryPrice;

    public Surgery(String surgeryId, String idBill, Date date, boolean active, double surgeryCost, double surgeryPrice) {
        this.surgeryId = surgeryId;
        this.idBill = idBill;
        this.date = date;
        this.active = active;
        this.surgeryCost = surgeryCost;
        this.surgeryPrice = surgeryPrice;
    }

    public String getSurgeryId() {
        return surgeryId;
    }

    public void setSurgeryId(String surgeryId) {
        this.surgeryId = surgeryId;
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

    public double getSurgeryCost() {
        return surgeryCost;
    }

    public void setSurgeryCost(double surgeryCost) {
        this.surgeryCost = surgeryCost;
    }

    public double getSurgeryPrice() {
        return surgeryPrice;
    }

    public void setSurgeryPrice(double surgeryPrice) {
        this.surgeryPrice = surgeryPrice;
    }
    
    
}
