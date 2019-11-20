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
public class Room {
    private int idRoom;
    private double maintenancePrice;
    private boolean available;
    private boolean active;

    public Room(HttpServletRequest request) {
        this.idRoom = Integer.valueOf(request.getParameter("id"));
        this.maintenancePrice = Double.valueOf(request.getParameter("price"));
        this.available = true;
        this.active = true;
    }
    
    public Room(int idRoom, double maintenancePrice, boolean available, boolean active) {
        this.idRoom = idRoom;
        this.maintenancePrice = maintenancePrice;
        this.available = available;
        this.active = active;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public double getMaintenancePrice() {
        return maintenancePrice;
    }

    public void setMaintenancePrice(double maintenancePrice) {
        this.maintenancePrice = maintenancePrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
