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
public class Medicine {
    private String idMedicine;
    private String name;
    private int amount;
    private int minimumAmount;
    private double price;

    public Medicine(HttpServletRequest request) {
        int random = (int) (Math.random()*1000 + 1);
        this.idMedicine = random + "-" + request.getParameter("medicineName");
        this.name = request.getParameter("medicineName");
        this.amount = 0;
        this.minimumAmount = Integer.valueOf(request.getParameter("minimum"));
        this.price = Double.valueOf(request.getParameter("price"));
    }
    
    public Medicine(String idMedicine, String name, int amount, int minimumAmount, double price) {
        this.idMedicine = idMedicine;
        this.name = name;
        this.amount = amount;
        this.minimumAmount = minimumAmount;
        this.price = price;
    }

    public String getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(String idMedicine) {
        this.idMedicine = idMedicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(int minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
