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
public class Bill {
    private String billNumber;
    private String clientNit;
    private String packageId;

    public Bill(String billNumber, String clientNit, String packageId) {
        this.billNumber = billNumber;
        this.clientNit = clientNit;
        this.packageId = packageId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getClientNit() {
        return clientNit;
    }

    public void setClientNit(String clientNit) {
        this.clientNit = clientNit;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
    
}
