/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dummyclasses;

/**
 *
 * @author zofia
 */
public class WorkingRecord {
    private String idRecord;
    private String cui;
    private String description;

    public WorkingRecord(String idRecord, String cui, String description) {
        this.idRecord = idRecord;
        this.cui = cui;
        this.description = description;
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
