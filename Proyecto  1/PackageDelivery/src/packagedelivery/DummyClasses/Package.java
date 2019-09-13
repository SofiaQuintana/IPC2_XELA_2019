/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.DummyClasses;

;

import java.time.LocalDate;

/**
 *
 * @author zofia
 */
public class Package {
    private String packageId;
    private String clientNit;
    private String destinationId;
    private String billId;
    private String rateId;
    private double weight;
    private double globalFare;
    private double priorizationFare;
    private double weightFare;
    private double price;
    private boolean availability;
    private boolean priorized;
    private LocalDate date;

    public Package(String packageId, String clientNit, String destinationId, String billId, String rateId, 
            double weight, double globalFare, double priorizationFare, double weightFare, double price, 
            boolean availability, boolean priorized, LocalDate date) {
        this.packageId = packageId;
        this.clientNit = clientNit;
        this.destinationId = destinationId;
        this.billId = billId;
        this.rateId = rateId;
        this.weight = weight;
        this.globalFare = globalFare;
        this.priorizationFare = priorizationFare;
        this.weightFare = weightFare;
        this.price = price;
        this.availability = availability;
        this.priorized = priorized;
        this.date = date;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getClientNit() {
        return clientNit;
    }

    public void setClientNit(String clientNit) {
        this.clientNit = clientNit;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getGlobalFare() {
        return globalFare;
    }

    public void setGlobalFare(double globalFare) {
        this.globalFare = globalFare;
    }

    public double getPriorizationFare() {
        return priorizationFare;
    }

    public void setPriorizationFare(double priorizationFare) {
        this.priorizationFare = priorizationFare;
    }

    public double getWeightFare() {
        return weightFare;
    }

    public void setWeightFare(double weightFare) {
        this.weightFare = weightFare;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean isPriorized() {
        return priorized;
    }

    public void setPriorized(boolean priorized) {
        this.priorized = priorized;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
