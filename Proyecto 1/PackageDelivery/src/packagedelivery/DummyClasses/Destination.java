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
public class Destination {
    private String destinationId;
    private double fee;
    private double profit;

    public Destination(String destinationId, double fee, double profit) {
        this.destinationId = destinationId;
        this.fee = fee;
        this.profit = profit;
    }

    public Destination(String destinationId, double fee) {
        this.destinationId = destinationId;
        this.fee = fee;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
 
}
