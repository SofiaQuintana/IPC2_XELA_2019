/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dbmanagers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import magazineswebapplication.dummyclasses.Payment;
import magazineswebapplication.dummyclasses.Subscription;

/**
 *
 * @author zofia
 */
public class SubscriptionDBManager {
    private Connection connection;
    private List<Subscription> subscriptions = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    public SubscriptionDBManager(Connection connection) {
        this.connection = connection;
    }
    
    public List<Subscription> getSubscriptions(String query) {
        subscriptions.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String subscriptionId = result.getString("SubscriptionId");
                String username = result.getString("Username");
                String idPost = result.getString("IdPost");
                double charge = result.getDouble("Charge");
                Date subscriptionDate = result.getDate("SubscriptionDate");
                LocalDate date = subscriptionDate.toLocalDate();
                double managerProfit = result.getDouble("ManagerProfit");
                double editorProfit = result.getDouble("EditorProfit");
                Subscription subscription = new Subscription(subscriptionId, username, idPost, charge, date, managerProfit, editorProfit);
                subscriptions.add(subscription);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return subscriptions;
    }
    
    public List<Payment> getPayments(String query) {
        payments.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String paymentId = result.getString("PaymentId");
                String subscriptionId = result.getString("SubscriptionId");
                Date date = result.getDate("Date");
                LocalDate parsedDate = date.toLocalDate();
                double pay = result.getDouble("Payment");
                Payment payment = new Payment(subscriptionId, subscriptionId, parsedDate, pay);
                payments.add(payment);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return payments;
    }
    
    public void addSubscription(Subscription subscription, Date date) throws Exception {
        try {
            String query = ("INSERT INTO Subscription (SubscriptionId, Username, IdPost, Charge, "
                    + "SubscriptionDate, ManagerProfit, EditorProfit) VALUES (?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, subscription.getSubscriptionId());
            object.setString(2, subscription.getUsername());
            object.setString(3, subscription.getIdPost());
            object.setDouble(4, subscription.getCharge());
            object.setDate(5, date);
            object.setDouble(6, subscription.getManagerProfit());
            object.setDouble(7, subscription.getEditorProfit());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Esta suscripcion ya existe, debe elegir uno nuevo.");
        }
    }
    
    public void addPayment(Payment payment, Date date) throws Exception {
        try {
            String query = ("INSERT INTO Payment (PaymentId, SubscriptionId, Date, Payment) VALUES (?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, payment.getPaymentId());
            object.setString(2, payment.getSubscriptionId());
            object.setDate(3, date);
            object.setDouble(4, payment.getPayment());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Este pago ya existe, debe elegir uno nuevo.");
        }
    }
}

