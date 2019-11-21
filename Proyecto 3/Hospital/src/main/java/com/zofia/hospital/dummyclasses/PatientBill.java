/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dummyclasses;

/**
 *
 * @author zofia
 */
public class PatientBill {
    private String idBill;
    private String patientCui;
    private boolean payed;

    public PatientBill(String idBill, String patientCui, boolean payed) {
        this.idBill = idBill;
        this.patientCui = patientCui;
        this.payed = payed;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getPatientCui() {
        return patientCui;
    }

    public void setPatientCui(String patientCui) {
        this.patientCui = patientCui;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
