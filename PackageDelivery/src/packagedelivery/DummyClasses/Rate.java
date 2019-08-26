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
public class Rate {
    private String rateId;
    private double globalFare;
    private double weightFare;
    private double priorizationFare;

    public Rate(String rateId, double globalFare, double weightFare, double priorizationFare) {
        this.rateId = rateId;
        this.globalFare = globalFare;
        this.weightFare = weightFare;
        this.priorizationFare = priorizationFare;
    }

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    public double getGlobalFare() {
        return globalFare;
    }

    public void setGlobalFare(double globalFare) {
        this.globalFare = globalFare;
    }

    public double getWeightFare() {
        return weightFare;
    }

    public void setWeightFare(double weightFare) {
        this.weightFare = weightFare;
    }

    public double getPriorizationFare() {
        return priorizationFare;
    }

    public void setPriorizationFare(double priorizationFare) {
        this.priorizationFare = priorizationFare;
    }
    
}
