
package packagedelivery.FrontEnd;

import packagedelivery.FrontEnd.Operating.OperatorFrame;
import packagedelivery.FrontEnd.CheckIn.ReceptionistFrame;
import packagedelivery.FrontEnd.Management.ManagementFrame;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import packagedelivery.DBmanagers.UserDBManager;
import packagedelivery.DummyClasses.User;
import packagedelivery.Storage.StorageManager;

/**
 *
 * @author zofia
 */
public class LogInDialog extends javax.swing.JDialog {
    private String query = "SELECT * FROM User WHERE Username = ";
    List<User> users;
    private User user;
    private UserDBManager userInformation;
    private ManagementFrame management;
    private OperatorFrame operator;
    private ReceptionistFrame recepcionist;
    private StorageManager storage;
    private Connection connection;
    /**
     * Creates new form LogInDialog
     */
    public LogInDialog(boolean modal, Connection connection) {
        users = new ArrayList<>();
        initComponents();
        userInformation = new UserDBManager(connection);
        this.setModal(modal);
        this.connection = connection;
        storage = new StorageManager(connection);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        infoLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        infoLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Delivery Service Login");
        setBackground(new java.awt.Color(51, 51, 51));

        usernameLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(0, 51, 102));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLabel.setText("Username:");

        passwordLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(0, 51, 102));
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setText("Password:");

        infoLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(102, 102, 102));
        infoLabel.setText("Enter your ID");

        loginButton.setBackground(new java.awt.Color(153, 153, 153));
        loginButton.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 15)); // NOI18N
        loginButton.setForeground(new java.awt.Color(102, 102, 102));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        infoLabel1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 11)); // NOI18N
        infoLabel1.setForeground(new java.awt.Color(102, 102, 102));
        infoLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoLabel1.setText("Please sing in using the form below");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loginButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(passwordField)
                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(usernameLabel)
                        .addComponent(passwordLabel)))
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoLabel1)
                    .addComponent(infoLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(infoLabel)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(infoLabel1)
                .addGap(18, 18, 18)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loginButton)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        getUser();
        if(usernameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese Usuario", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(passwordField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese contraseña", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(this.user != null && this.user.isAvailability() == false) {
            JOptionPane.showMessageDialog(this, "Usuario no autorizado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            cleanFields();
        } else if(this.user == null) {
            JOptionPane.showMessageDialog(this, "Usuario no existente", "Error", JOptionPane.ERROR_MESSAGE);
            cleanFields();
        }else {
            try {
              determinateRole();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            storage.storage();
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void cleanFields() {
        usernameField.setText("");
        passwordField.setText("");
    }   
    
    private void getUser() {
        this.user = userInformation.getUserInList(usernameField.getText());
    }

    private void determinateRole() throws Exception {
        if(this.user.getPassword().equals(passwordField.getText())) {
            switch(this.user.getRole()) {
                case "Administrador":
                    management = new ManagementFrame(this.user, this.connection);
                    management.setVisible(true);
                break;
                case "Operador":
                    operator = new OperatorFrame(this.user, this.connection);
                    operator.setVisible(true);
                break;
                case "Recepcionista":
                    recepcionist = new ReceptionistFrame(this.user, this.connection);
                    recepcionist.setVisible(true);
                break;
            }
            cleanFields();
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            cleanFields();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel infoLabel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}