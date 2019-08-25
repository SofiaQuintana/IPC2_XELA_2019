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
public class Route {
    private String routeId;
    private String destinationId;
    private boolean availability;
    private boolean disabled;
    private int packagesInRoute;

    public Route(String routeId, String destinationId, boolean availability, boolean disabled, int packagesInRoute) {
        this.routeId = routeId;
        this.destinationId = destinationId;
        this.availability = availability;
        this.disabled = disabled;
        this.packagesInRoute = packagesInRoute;
    }

    public Route(String routeId, String destinationId, boolean availability, boolean disabled) {
        this.routeId = routeId;
        this.destinationId = destinationId;
        this.availability = availability;
        this.disabled = disabled;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
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

    public int getPackagesInRoute() {
        return packagesInRoute;
    }

    public void setPackagesInRoute(int packagesInRoute) {
        this.packagesInRoute = packagesInRoute;
    }
    
}
