/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.DummyClasses;

/**
 *
 * @author zofia
 */
public class Package {
    private String packageId;
    private String clientNit;
    private String destinationId;
    private double weight;
    private double fare;
    private double price;
    private boolean availability;

    public Package(String packageId, String clientNit, String destinationId, double weight, double fare, double price, boolean availability) {
        this.packageId = packageId;
        this.clientNit = clientNit;
        this.destinationId = destinationId;
        this.weight = weight;
        this.fare = fare;
        this.price = price;
        this.availability = availability;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
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
    
    
}
