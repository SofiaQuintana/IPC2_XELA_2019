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
public class Employee {
    private String cui;
    private int idArea;
    private String name;
    private String lastName;
    private double salary;

    public Employee(String cui, int idArea, String name, String lastName, double salary) {
        this.cui = cui;
        this.idArea = idArea;
        this.name = name;
        this.lastName = lastName;
        this.salary = salary;
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
}
