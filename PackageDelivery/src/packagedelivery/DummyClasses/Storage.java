/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.DummyClasses;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author zofia
 */
public class Storage {
    private String packageId;
    private LocalDate date;
    private boolean priorized;

    public Storage(String packageId, LocalDate date, boolean priorized) {
        this.packageId = packageId;
        this.date = date;
        this.priorized = priorized;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPriorized() {
        return priorized;
    }

    public void setPriorized(boolean priorized) {
        this.priorized = priorized;
    }
}
