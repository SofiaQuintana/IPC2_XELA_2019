/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dummyclasses;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zofia
 */
public class WorkingRecord {
    private String idRecord;
    private String cui;
    private String description;
    private LocalDate date;

    public WorkingRecord(HttpServletRequest request, LocalDate date) {
        this.idRecord = request.getParameter("employeeCui") + "-" + request.getParameter("cause");
        this.cui = request.getParameter("employeeCui");
        this.description = request.getParameter("cause");
        this.date = date;
    }
    
    public WorkingRecord(String idRecord, String cui, String description, LocalDate date) {
        this.idRecord = idRecord;
        this.cui = cui;
        this.description = description;
        this.date = date;
    }

    public String getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(String idRecord) {
        this.idRecord = idRecord;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
