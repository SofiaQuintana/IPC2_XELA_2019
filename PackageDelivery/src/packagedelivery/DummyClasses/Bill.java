/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.DummyClasses;


import java.time.LocalDate;

/**
 *
 * @author zofia
 */
public class Bill {
    private String billNumber;
    private String clientNit;
    private String name;
    private String address;
    private LocalDate date;

    public Bill(String billNumber, String clientNit, String name, String address, LocalDate date) {
        this.billNumber = billNumber;
        this.clientNit = clientNit;
        this.name = name;
        this.address = address;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
   
    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getClientNit() {
        return clientNit;
    }

    public void setClientNit(String clientNit) {
        this.clientNit = clientNit;
    }
}
