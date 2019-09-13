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
public class Register {
    private String packageId;
    private String checkpointId;
    private int hours;
    private LocalDate date;
    private double charge;

    public Register(String packageId, String checkpointId, int hours, LocalDate date, double charge) {
        this.packageId = packageId;
        this.checkpointId = checkpointId;
        this.hours = hours;
        this.date = date;
        this.charge = charge;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(String checkpointId) {
        this.checkpointId = checkpointId;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }
    
}
