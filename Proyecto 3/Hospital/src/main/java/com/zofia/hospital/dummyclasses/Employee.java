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
public class Employee {
    private String cui;
    private int idArea;
    private String name;
    private String lastName;
    private double salary;
    private double discount;

    public Employee(HttpServletRequest request, int idArea, double discount) { //Constructor a traves de un request
        this.cui = request.getParameter("cui");
        this.idArea = idArea;
        this.name = request.getParameter("name");
        this.lastName = request.getParameter("lastName");
        this.salary = Double.valueOf(request.getParameter("salary"));
        this.discount = discount;
    }
    
    public Employee(String cui, int idArea, String name, String lastName, double salary, double discount) {
        this.cui = cui;
        this.idArea = idArea;
        this.name = name;
        this.lastName = lastName;
        this.salary = salary;
        this.discount = discount;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
