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
public class Checkpoint {
    private String checkpointId;
    private String routeId;
    private String operatorName;
    private String rateId;
    private int queueSize;
    private double globalFare;
    private double specialFare;
    private boolean availability;
    private boolean disabled;

    public Checkpoint(String checkpointId, String routeId, String operatorName, String rateId, int queueSize, double globalFare, double specialFare, boolean availability, boolean disabled) {
        this.checkpointId = checkpointId;
        this.routeId = routeId;
        this.operatorName = operatorName;
        this.rateId = rateId;
        this.queueSize = queueSize;
        this.globalFare = globalFare;
        this.specialFare = specialFare;
        this.availability = availability;
        this.disabled = disabled;
    }
  
    public String getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(String checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public double getGlobalFare() {
        return globalFare;
    }

    public void setGlobalFare(double globalFare) {
        this.globalFare = globalFare;
    }

    public double getSpecialFare() {
        return specialFare;
    }

    public void setSpecialFare(double specialFare) {
        this.specialFare = specialFare;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
}
