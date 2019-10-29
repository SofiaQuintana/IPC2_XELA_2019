/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.FrontEnd.CheckIn;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import packagedelivery.DBmanagers.PackageDBManager;
import packagedelivery.DummyClasses.Bill;
import packagedelivery.DummyClasses.Package;
/**
 *
 * @author zofia
 */
public class BillDialog extends javax.swing.JDialog {
    private Connection connection;
    private Bill bill;
    private double totalPrice = 0;
    private List<Package> packages;
    private PackageDBManager packageManager;
    private ObservableList<Package> packagesObservable;
    private static final String PACKAGE_QUERY = "SELECT * FROM Package WHERE Bill_id = ";
    /**
     * Creates new form BillDialog
     */
    public BillDialog(boolean modal, Connection connection, List<Package> packages, Bill bill) {
        this.packages = new ArrayList<>();
        packagesObservable = ObservableCollections.observableList(packages);
        initComponents();
        this.setModal(modal);
        this.connection = connection;
        packageManager = new PackageDBManager(connection);
        this.packages = packages;
        this.bill = bill;
        setFields();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        nameField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        nitLabel = new javax.swing.JLabel();
        nitField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        billNumberLabel = new javax.swing.JLabel();
        billNumberField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        printButton = new javax.swing.JButton();
        furnitureLabel4 = new javax.swing.JLabel();
        totalField = new javax.swing.JLabel();

        nameLabel.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(153, 0, 51));
        nameLabel.setText("Nombre:");

        nitLabel.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        nitLabel.setForeground(new java.awt.Color(153, 0, 51));
        nitLabel.setText("NIT:");

        addressLabel.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        addressLabel.setForeground(new java.awt.Color(153, 0, 51));
        addressLabel.setText("Direccion:");

        billNumberLabel.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        billNumberLabel.setForeground(new java.awt.Color(153, 0, 51));
        billNumberLabel.setText("No. Factura:");

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${packagesObservable}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, table);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${packageId}"));
        columnBinding.setColumnName("Package Id");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${destinationId}"));
        columnBinding.setColumnName("Destination Id");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${price}"));
        columnBinding.setColumnName("Price");
        columnBinding.setColumnClass(Double.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(table);

        printButton.setBackground(new java.awt.Color(153, 0, 51));
        printButton.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        printButton.setForeground(new java.awt.Color(204, 204, 204));
        printButton.setText("Imprimir");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        furnitureLabel4.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        furnitureLabel4.setForeground(new java.awt.Color(153, 0, 51));
        furnitureLabel4.setText("TOTAL:");

        totalField.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
        totalField.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(billNumberLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(billNumberField, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nitLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nitField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(printButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(furnitureLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(billNumberLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(billNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nitLabel)
                    .addComponent(nitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressLabel)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(furnitureLabel4)
                    .addComponent(totalField)
                    .addComponent(printButton))
                .addGap(44, 44, 44))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_printButtonActionPerformed

    public void setFields() {
        nameField.setText(this.bill.getName());
        billNumberField.setText(this.bill.getBillNumber());
        nitField.setText(this.bill.getClientNit());
        addressField.setText(this.bill.getAddress());
        System.out.println(this.packages.size());
        for (int i = 0; i < this.packages.size(); i++) {
            Package packge = this.packages.get(i);
            this.totalPrice = this.totalPrice + packge.getPrice();      
            System.out.println(totalPrice);
        }
        totalField.setText(String.valueOf(this.totalPrice));
        String query = PACKAGE_QUERY + "'" + this.bill.getBillNumber() + "';";
        refreshObservableList(packageManager.getElements(query));
    }
    
    public void refreshObservableList(List<Package> list) {
        this.packagesObservable.clear();
        this.packagesObservable.addAll(list);
    }
    
    public ObservableList<Package> getPackagesObservable() {
        return packagesObservable;
    }

    public void setPackagesObservable(ObservableList<Package> packagesObservable) {
        this.packagesObservable = packagesObservable;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField billNumberField;
    private javax.swing.JLabel billNumberLabel;
    private javax.swing.JLabel furnitureLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nitField;
    private javax.swing.JLabel nitLabel;
    private javax.swing.JButton printButton;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalField;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}