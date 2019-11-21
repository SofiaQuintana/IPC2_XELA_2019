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
public class MonetaryRegistration {
    private int registrationId;
    private String paymentId;
    private String idSale;
    private String idPurchase;
    private String idMedication;
    private String idSpecialistAssignment;
    private int type;

    public MonetaryRegistration(int registrationId, String paymentId, String idSale, String idPurchase, String idMedication, String idSpecialistAssignment, int type) {
        this.registrationId = registrationId;
        this.paymentId = paymentId;
        this.idSale = idSale;
        this.idPurchase = idPurchase;
        this.idMedication = idMedication;
        this.idSpecialistAssignment = idSpecialistAssignment;
        this.type = type;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }

    public String getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(String idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getIdMedication() {
        return idMedication;
    }

    public void setIdMedication(String idMedication) {
        this.idMedication = idMedication;
    }

    public String getIdSpecialistAssignment() {
        return idSpecialistAssignment;
    }

    public void setIdSpecialistAssignment(String idSpecialistAssignment) {
        this.idSpecialistAssignment = idSpecialistAssignment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
}
