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
import packagedelivery.DBmanagers.UserDBManager;
import packagedelivery.DummyClasses.User;

/**
 *
 * @author zofia
 */
public class UsersFrame extends javax.swing.JInternalFrame {
    private String generalQuery = "SELECT * FROM User;";
    private String ascByNameQuery = "SELECT * FROM User ORDER BY Name ASC";
    private String descByNameQuery = "SELECT * FROM User ORDER BY Name DESC";
    private String desactivateQuery = "UPDATE User SET Availability = 0 WHERE Username = ";
    private List<User> users;
    private ObservableList<User> userObservable;
    private UserDBManager userData;
    private DBManager manager;
    private Connection connection;
    private UpdateDialog update;
    private User user;
    private static final String SELECTION = "SELECT * FROM User ORDER BY ";
    /**
     * Creates new form usersFrame
     */
    public UsersFrame(Connection connection) {
        users = new ArrayList<>();
        userObservable = ObservableCollections.observableList(users);
        initComponents();
        this.connection = connection;
        userData = new UserDBManager(connection);
        manager = new DBManager(connection);
        refreshObservableList(userData.getUsers(generalQuery));
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

        searchingButton = new javax.swing.JButton();
        searchingText = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        searchingComboBox = new javax.swing.JComboBox<>();
        createUserPanel = new javax.swing.JTabbedPane();
        tablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        desactivateButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        createPanel = new javax.swing.JPanel();
        creatStuffPanel = new javax.swing.JPanel();
        userField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        roleText = new javax.swing.JLabel();
        nameText = new javax.swing.JLabel();
        roleCombo = new javax.swing.JComboBox<>();
        passwordText = new javax.swing.JLabel();
        lastNameText = new javax.swing.JLabel();
        lastNameField = new javax.swing.JTextField();
        createButton = new javax.swing.JButton();
        userText = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        orderComboBox = new javax.swing.JComboBox<>();
        elementText = new javax.swing.JLabel();
        orderText = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuarios");

        searchingButton.setBackground(new java.awt.Color(153, 153, 153));
        searchingButton.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        searchingButton.setForeground(new java.awt.Color(102, 102, 102));
        searchingButton.setText("Buscar");
        searchingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchingButtonActionPerformed(evt);
            }
        });

        searchingText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 16)); // NOI18N
        searchingText.setForeground(new java.awt.Color(0, 51, 102));
        searchingText.setText("Busqueda Personalizada--------------------------------------------------------------------");

        searchingComboBox.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        searchingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---vacio---", "Username", "Name", "LastName", "Role", "Availability" }));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${userObservable}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, table);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${username}"));
        columnBinding.setColumnName("Username");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${lastName}"));
        columnBinding.setColumnName("Last Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${role}"));
        columnBinding.setColumnName("Role");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${availability}"));
        columnBinding.setColumnName("Activated");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(table);

        desactivateButton.setBackground(new java.awt.Color(153, 153, 153));
        desactivateButton.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        desactivateButton.setForeground(new java.awt.Color(102, 102, 102));
        desactivateButton.setText("Desactivar");
        desactivateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desactivateButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(153, 153, 153));
        updateButton.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        updateButton.setForeground(new java.awt.Color(102, 102, 102));
        updateButton.setText("Actualizar Datos");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Seleccione una fila -----------------------------------------");

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(desactivateButton)))
                .addContainerGap())
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desactivateButton)
                    .addComponent(updateButton)
                    .addComponent(jLabel2))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        createUserPanel.addTab("Usuarios", tablePanel);

        creatStuffPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        roleText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 15)); // NOI18N
        roleText.setForeground(new java.awt.Color(0, 51, 102));
        roleText.setText("Rol:");

        nameText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 15)); // NOI18N
        nameText.setForeground(new java.awt.Color(0, 51, 102));
        nameText.setText("Nombre:");

        roleCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "------VACIO------", "Administrador", "Operador", "Recepcionista" }));

        passwordText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 15)); // NOI18N
        passwordText.setForeground(new java.awt.Color(0, 51, 102));
        passwordText.setText("Contraseña:");

        lastNameText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 15)); // NOI18N
        lastNameText.setForeground(new java.awt.Color(0, 51, 102));
        lastNameText.setText("Apellido:");

        createButton.setBackground(new java.awt.Color(153, 153, 153));
        createButton.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        createButton.setForeground(new java.awt.Color(102, 102, 102));
        createButton.setText("Crear");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        userText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 15)); // NOI18N
        userText.setForeground(new java.awt.Color(0, 51, 102));
        userText.setText("Usuario:");

        javax.swing.GroupLayout creatStuffPanelLayout = new javax.swing.GroupLayout(creatStuffPanel);
        creatStuffPanel.setLayout(creatStuffPanelLayout);
        creatStuffPanelLayout.setHorizontalGroup(
            creatStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creatStuffPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(creatStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(creatStuffPanelLayout.createSequentialGroup()
                        .addGroup(creatStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordText)
                            .addComponent(lastNameText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roleText, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(creatStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userField)
                            .addComponent(nameField)
                            .addComponent(lastNameField)
                            .addComponent(roleCombo, 0, 214, Short.MAX_VALUE)
                            .addComponent(passwordField))))
                .addContainerGap())
        );
        creatStuffPanelLayout.setVerticalGroup(
            creatStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creatStuffPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(creatStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userText)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(creatStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameText)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(creatStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameText)
                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(creatStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordText)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(creatStuffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleText)
                    .addComponent(roleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(createButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout createPanelLayout = new javax.swing.GroupLayout(createPanel);
        createPanel.setLayout(createPanelLayout);
        createPanelLayout.setHorizontalGroup(
            createPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(creatStuffPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(255, Short.MAX_VALUE))
        );
        createPanelLayout.setVerticalGroup(
            createPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(creatStuffPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        createUserPanel.addTab("Crear Usuario", createPanel);

        orderComboBox.setForeground(new java.awt.Color(153, 153, 153));
        orderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-vacio-", "ASC", "DESC" }));

        elementText.setText("Por Elemento:");

        orderText.setText("Orden:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(separator)
                            .addComponent(createUserPanel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(elementText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(168, 168, 168)
                                .addComponent(orderText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(searchingText)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(searchingText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchingButton)
                    .addComponent(searchingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elementText)
                    .addComponent(orderText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchingButtonActionPerformed
        // TODO add your handling code here:
        if(searchingComboBox.getModel().getSelectedItem().equals("---vacio---") && 
                !orderComboBox.getModel().getSelectedItem().equals("-vacio-")) {
            if(orderComboBox.getModel().getSelectedItem().equals("ASC")) {
                refreshObservableList(userData.getUsers(ascByNameQuery));
            } else {
                refreshObservableList(userData.getUsers(descByNameQuery));
            }                                  
        } else if(!searchingComboBox.getModel().getSelectedItem().equals("---vacio---")
                && !orderComboBox.getModel().getSelectedItem().equals("-vacio-")) {
            String data = searchingComboBox.getModel().getSelectedItem().toString();
            String order = orderComboBox.getModel().getSelectedItem().toString();
            String query = SELECTION + data + " " +order;
            refreshObservableList(userData.getUsers(query));
        } else if(searchingComboBox.getModel().getSelectedItem().equals("---vacio---") &&
                orderComboBox.getModel().getSelectedItem().equals("-vacio-")) {
            refreshObservableList(userData.getUsers(generalQuery));
        }
    }//GEN-LAST:event_searchingButtonActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        // TODO add your handling code here:
        if(userField.getText().isEmpty() || nameField.getText().isEmpty() || lastNameField.getText().isEmpty()
            || passwordField.getText().isEmpty() || roleCombo.getModel().getSelectedItem().equals("------VACIO------")) {
            JOptionPane.showMessageDialog(this, "Ingrese todos los campos...", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                userData.addUser(userField.getText(), nameField.getText(), lastNameField.getText(),
                    passwordField.getText(), roleCombo.getModel().getSelectedItem().toString(), true);
                cleanFields();
                JOptionPane.showMessageDialog(this, "Usuario creado con exito...", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                refreshObservableList(userData.getUsers(generalQuery));
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_createButtonActionPerformed

    private void desactivateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desactivateButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = table.getSelectedRow();
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila...", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            desactivateSelectedUser(selectedRow);
            refreshObservableList(userData.getUsers(generalQuery));
            JOptionPane.showMessageDialog(this, "Usuario desactivado con exito!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_desactivateButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = table.getSelectedRow();
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila...", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            getSelectedUser(selectedRow);
            update = new UpdateDialog(true, connection, this.user, null, null);
            update.setVisible(true);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    public void getSelectedUser(int selectedRow) {
        String selectedUser = table.getModel().getValueAt(selectedRow, 0).toString();
        this.user = userData.getUserInList(selectedUser);
    }
    
    public void desactivateSelectedUser(int selectedRow) {
        String selectedUser = table.getModel().getValueAt(selectedRow, 0).toString();
        String query = desactivateQuery + "'"+selectedUser+"';";
        manager.updateElement(query);
    }
    
    public void cleanFields() {
        userField.setText("");
        nameField.setText("");
        lastNameField.setText("");
        passwordField.setText("");
        roleCombo.getModel().setSelectedItem("------VACIO------");
    }
    
    public void refreshObservableList(List<User> list) {
        this.userObservable.clear();
        this.userObservable.addAll(list);
    }

    public ObservableList<User> getUserObservable() {
        return userObservable;
    }

    public void setUserObservable(ObservableList<User> userObservable) {
        this.userObservable = userObservable;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel creatStuffPanel;
    private javax.swing.JButton createButton;
    private javax.swing.JPanel createPanel;
    private javax.swing.JTabbedPane createUserPanel;
    private javax.swing.JButton desactivateButton;
    private javax.swing.JLabel elementText;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel lastNameText;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameText;
    private javax.swing.JComboBox<String> orderComboBox;
    private javax.swing.JLabel orderText;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordText;
    private javax.swing.JComboBox<String> roleCombo;
    private javax.swing.JLabel roleText;
    private javax.swing.JButton searchingButton;
    private javax.swing.JComboBox<String> searchingComboBox;
    private javax.swing.JLabel searchingText;
    private javax.swing.JSeparator separator;
    private javax.swing.JTable table;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField userField;
    private javax.swing.JLabel userText;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}