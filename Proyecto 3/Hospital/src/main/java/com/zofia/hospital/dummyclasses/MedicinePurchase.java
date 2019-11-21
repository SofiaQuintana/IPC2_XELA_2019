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
public class MedicinePurchase { //Compras de medicamentos
    private String idPurchase;
    private String idMedicine;
    private String employeeCui;
    private int amount;
    private double purchasingPrice;
    private Date date;

    public MedicinePurchase(HttpServletRequest request, Date date) {
        int random = (int) (Math.random()*1000 + 1);
        this.idPurchase = random + "-" + request.getParameter("id") + "-" + request.getParameter("cuiEmployee");
        this.idMedicine = request.getParameter("id");
        this.employeeCui = request.getParameter("cuiEmployee");
        this.amount = Integer.valueOf(request.getParameter("amount"));
        this.purchasingPrice = Double.valueOf(request.getParameter("total"));
        this.date = date;
    }
    
    public MedicinePurchase(String idPurchase, String idMedicine, String employeeCui, int amount, double purchasingPrice, Date date) {
        this.idPurchase = idPurchase;
        this.idMedicine = idMedicine;
        this.employeeCui = employeeCui;
        this.amount = amount;
        this.purchasingPrice = purchasingPrice;
        this.date = date;
    }

    public String getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(String idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(String idMedicine) {
        this.idMedicine = idMedicine;
    }

    public String getEmployeeCui() {
        return employeeCui;
    }

    public void setEmployeeCui(String employeeCui) {
        this.employeeCui = employeeCui;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(double purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
