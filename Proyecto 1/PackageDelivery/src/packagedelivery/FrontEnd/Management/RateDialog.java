/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.FrontEnd.Management;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import packagedelivery.DBmanagers.DBManager;
import packagedelivery.DummyClasses.Rate;

/**
 *
 * @author zofia
 */
public class RateDialog extends javax.swing.JDialog {

    private List<Rate> rates;
    private ObservableList<Rate> observableRate;
    private DBManager manager;
    private Connection connection;
    private static final String UPDATE_RATE_QUERY = "UPDATE Rate SET ";
    private static final String GENERAL_QUERY = "SELECT * FROM Rate;";
    /**
     * Creates new form RateDialog
     */
    public RateDialog(boolean modal, Connection connection) {
        rates = new ArrayList<>();
        observableRate = ObservableCollections.observableList(rates);
        initComponents();
        this.setModal(modal);
        this.connection = connection;
        manager = new DBManager(connection);
        refreshObservableList(manager.getRate(GENERAL_QUERY));
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

        changePanel = new javax.swing.JPanel();
        feeComboBox = new javax.swing.JComboBox<>();
        userText = new javax.swing.JLabel();
        userText1 = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        valueField = new javax.swing.JTextField();
        actualFeePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setTitle("Tarifario");

        changePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cambio de tarifas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans Condensed", 1, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        feeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------VACIO-------", "Tarifa Global", "Tarifa de Peso", "Tarifa de Priorizacion" }));

        userText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 15)); // NOI18N
        userText.setForeground(new java.awt.Color(0, 51, 102));
        userText.setText("Tarifa:");

        userText1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 15)); // NOI18N
        userText1.setForeground(new java.awt.Color(0, 51, 102));
        userText1.setText("Valor:");

        updateButton.setBackground(new java.awt.Color(153, 153, 153));
        updateButton.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        updateButton.setForeground(new java.awt.Color(102, 102, 102));
        updateButton.setText("Actualizar");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changePanelLayout = new javax.swing.GroupLayout(changePanel);
        changePanel.setLayout(changePanelLayout);
        changePanelLayout.setHorizontalGroup(
            changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(changePanelLayout.createSequentialGroup()
                            .addComponent(userText1)
                            .addGap(18, 18, 18)
                            .addComponent(valueField))
                        .addGroup(changePanelLayout.createSequentialGroup()
                            .addComponent(userText)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(feeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(updateButton))
                .addContainerGap())
        );
        changePanelLayout.setVerticalGroup(
            changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userText)
                    .addComponent(feeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userText1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateButton)
                .addContainerGap())
        );

        actualFeePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarifas actuales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans Condensed", 1, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableRate}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${rateId}"));
        columnBinding.setColumnName("Tarifario");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${globalFare}"));
        columnBinding.setColumnName("Global Fare");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${priorizationFare}"));
        columnBinding.setColumnName("Priorization Fare");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${weightFare}"));
        columnBinding.setColumnName("Weight Fare");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout actualFeePanelLayout = new javax.swing.GroupLayout(actualFeePanel);
        actualFeePanel.setLayout(actualFeePanelLayout);
        actualFeePanelLayout.setHorizontalGroup(
            actualFeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actualFeePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        actualFeePanelLayout.setVerticalGroup(
            actualFeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actualFeePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(actualFeePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(changePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(changePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actualFeePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        if(valueField.getText().isEmpty() ||  feeComboBox.getModel().getSelectedItem().equals("-------VACIO-------")) {
                JOptionPane.showMessageDialog(this, "Ingrese todos los campos...", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                        manager.updateElement(determinateSelectedFee());
                        cleanFields();
                        JOptionPane.showMessageDialog(this, "Tarifa actualizada con exito...", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                        refreshObservableList(manager.getRate(GENERAL_QUERY));
                } catch(Exception e) {
                        JOptionPane.showMessageDialog(this, "Por favor, ingrese un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }//GEN-LAST:event_updateButtonActionPerformed

    public String determinateSelectedFee() {
        String query = UPDATE_RATE_QUERY;
        switch(feeComboBox.getModel().getSelectedItem().toString()) {
            case "Tarifa Global":
                query += "GlobalFare= "+ Double.parseDouble(valueField.getText());
            break;
            case "Tarifa de Peso":
                query += "WeightFare = " + Double.parseDouble(valueField.getText());
            break;
            case "Tarifa de Priorizacion":
                query += "PriorizationFare =" + Double.parseDouble(valueField.getText());
            break;        
        }
        return query;
    }
    
    public void cleanFields() {
        valueField.setText("");
        feeComboBox.getModel().setSelectedItem("-------VACIO-------");
    }
    
     public void refreshObservableList(List<Rate> list) {
        this.observableRate.clear();
        this.observableRate.addAll(list);
    }
     
    public ObservableList<Rate> getObservableRate() {
        return observableRate;
    }

    public void setObservableRate(ObservableList<Rate> observableRate) {
        this.observableRate = observableRate;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actualFeePanel;
    private javax.swing.JPanel changePanel;
    private javax.swing.JComboBox<String> feeComboBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel userText;
    private javax.swing.JLabel userText1;
    private javax.swing.JTextField valueField;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
