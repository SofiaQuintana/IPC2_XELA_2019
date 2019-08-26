/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.FrontEnd.Management;

import java.sql.Connection;
import packagedelivery.DummyClasses.User;
import packagedelivery.FrontEnd.LogInDialog;

/**
 *
 * @author zofia
 */
public class ManagementFrame extends javax.swing.JFrame {
    private Connection connection;
    private UsersFrame userFrame;
    private DestinationFrame destinationFrame;
    private RoutesFrame routeFrame;
    private CheckpointFrame checkpointFrame;
    private RateDialog rateDialog;
    /**
     * Creates new form ManagementFrame
     */
    public ManagementFrame(User user, Connection connection) {
        userFrame = new UsersFrame(connection);
        destinationFrame = new DestinationFrame(connection);
        routeFrame = new RoutesFrame(connection);
        checkpointFrame = new CheckpointFrame(connection);
        initComponents();
        this.connection = connection;
        this.desktopPane.add(userFrame);
        this.desktopPane.add(destinationFrame);
        this.desktopPane.add(routeFrame);
        this.desktopPane.add(checkpointFrame);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        routeMenu = new javax.swing.JMenu();
        userItem = new javax.swing.JMenuItem();
        routeItem = new javax.swing.JMenuItem();
        destinationMenu = new javax.swing.JMenuItem();
        checkpointItem = new javax.swing.JMenuItem();
        tarifaMenu = new javax.swing.JMenu();
        tarifarioItem = new javax.swing.JMenuItem();
        ReportesMenu = new javax.swing.JMenu();
        otherMenu = new javax.swing.JMenu();
        logoutItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Management");

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1239, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );

        routeMenu.setText("Registros");

        userItem.setText("Usuario");
        userItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userItemActionPerformed(evt);
            }
        });
        routeMenu.add(userItem);

        routeItem.setText("Ruta");
        routeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routeItemActionPerformed(evt);
            }
        });
        routeMenu.add(routeItem);

        destinationMenu.setText("Destino");
        destinationMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinationMenuActionPerformed(evt);
            }
        });
        routeMenu.add(destinationMenu);

        checkpointItem.setText("Punto de control");
        checkpointItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkpointItemActionPerformed(evt);
            }
        });
        routeMenu.add(checkpointItem);

        menuBar.add(routeMenu);

        tarifaMenu.setText("Tarifas");

        tarifarioItem.setText("Tarifario");
        tarifarioItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifarioItemActionPerformed(evt);
            }
        });
        tarifaMenu.add(tarifarioItem);

        menuBar.add(tarifaMenu);

        ReportesMenu.setText("Reportes");
        menuBar.add(ReportesMenu);

        otherMenu.setText("Otros");

        logoutItem.setText("Cerrar Sesion");
        logoutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutItemActionPerformed(evt);
            }
        });
        otherMenu.add(logoutItem);

        menuBar.add(otherMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktopPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(desktopPane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userItemActionPerformed
        // TODO add your handling code here:
        userFrame.setVisible(true);
    }//GEN-LAST:event_userItemActionPerformed

    private void logoutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutItemActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        LogInDialog login = new LogInDialog(true, this.connection);
        login.setVisible(true);
    }//GEN-LAST:event_logoutItemActionPerformed

    private void destinationMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinationMenuActionPerformed
        // TODO add your handling code here:
        destinationFrame.setVisible(true);
    }//GEN-LAST:event_destinationMenuActionPerformed

    private void routeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_routeItemActionPerformed
        // TODO add your handling code here:
        routeFrame.setVisible(true);
        routeFrame.setComboModel();
    }//GEN-LAST:event_routeItemActionPerformed

    private void tarifarioItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifarioItemActionPerformed
        // TODO add your handling code here:
        rateDialog = new RateDialog(true, this.connection);
        rateDialog.setVisible(true);
    }//GEN-LAST:event_tarifarioItemActionPerformed

    private void checkpointItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkpointItemActionPerformed
        // TODO add your handling code here:
        checkpointFrame.setVisible(true);
        checkpointFrame.setComboBoxesModel();
    }//GEN-LAST:event_checkpointItemActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ReportesMenu;
    private javax.swing.JMenuItem checkpointItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem destinationMenu;
    private javax.swing.JMenuItem logoutItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu otherMenu;
    private javax.swing.JMenuItem routeItem;
    private javax.swing.JMenu routeMenu;
    private javax.swing.JMenu tarifaMenu;
    private javax.swing.JMenuItem tarifarioItem;
    private javax.swing.JMenuItem userItem;
    // End of variables declaration//GEN-END:variables
}
