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
public class Rate {
    private int idRate;
    private double surgeriesPrice;
    private double surgeriesCost;
    private double specialistPayment;
    private double roomStayPrice;
    private double consultationPrice;

    public Rate(int idRate, double surgeriesPrice, double surgeriesCost, double specialistPayment, double roomStayPrice, double consultationPrice) {
        this.idRate = idRate;
        this.surgeriesPrice = surgeriesPrice;
        this.surgeriesCost = surgeriesCost;
        this.specialistPayment = specialistPayment;
        this.roomStayPrice = roomStayPrice;
        this.consultationPrice = consultationPrice;
    }

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public double getSurgeriesPrice() {
        return surgeriesPrice;
    }

    public void setSurgeriesPrice(double surgeriesPrice) {
        this.surgeriesPrice = surgeriesPrice;
    }

    public double getSurgeriesCost() {
        return surgeriesCost;
    }

    public void setSurgeriesCost(double surgeriesCost) {
        this.surgeriesCost = surgeriesCost;
    }

    public double getSpecialistPayment() {
        return specialistPayment;
    }

    public void setSpecialistPayment(double specialistPayment) {
        this.specialistPayment = specialistPayment;
    }

    public double getRoomStayPrice() {
        return roomStayPrice;
    }

    public void setRoomStayPrice(double roomStayPrice) {
        this.roomStayPrice = roomStayPrice;
    }

    public double getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(double consultationPrice) {
        this.consultationPrice = consultationPrice;
    }
}
