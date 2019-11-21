/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.reports;

import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author zofia
 */
public class ReportsController {
    private JasperPrint jPrint;
    private JasperViewer jViewer;
    
    /*
    Metodo encargado de crear y mostrar un reporte.
    */
    public void createReport(String reportName, List list){
        try {  
            jPrint = JasperFillManager.fillReport(getClass().getResourceAsStream(reportName), null, new JRBeanCollectionDataSource(list));
            jViewer = new JasperViewer(jPrint);
            jViewer.setVisible(true);
            } 
            catch (JRException ex) {
                System.out.println("Something went wrong creating the report: " + ex.getMessage());
            }
    }
    
}
