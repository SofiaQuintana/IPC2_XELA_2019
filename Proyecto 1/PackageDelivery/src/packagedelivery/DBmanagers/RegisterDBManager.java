/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.DBmanagers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import packagedelivery.DummyClasses.Register;

/**
 *
 * @author zofia
 */
public class RegisterDBManager {
    private Connection connection;
    private List<Register> registers = new ArrayList<>();

    public RegisterDBManager(Connection connection) {
        this.connection = connection;
    }

    public List<Register> getElements(String query) {
        registers.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String packageId = result.getString("Package_Id");
                String checkpointId = result.getString("Checkpoint_Id");
                int hours = result.getInt("Hours");
                Date date = result.getDate("Date");
                double charge = result.getDouble("Charge");
                LocalDate localDate = date.toLocalDate();
                Register register = new Register(packageId, checkpointId, hours, localDate, charge);
                registers.add(register);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return registers;
    }

     public void addRegister(String packageId, String checkpointId, int hours, Date date, double charge) throws Exception{

            try {
                String query = ("INSERT INTO Register (Package_Id, Checkpoint_Id, Hours, Date, Charge) VALUES (?, ?, ?, ?, ?)");

                PreparedStatement object = connection.prepareStatement(query);
                object.setString(1, packageId);
                object.setString(2, checkpointId);
                object.setInt(3, hours);
                object.setDate(4, date);
                object.setDouble(5, charge);
                object.execute();
                
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }

    }
}
