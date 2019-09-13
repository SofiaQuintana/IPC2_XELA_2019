/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.DBmanagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import packagedelivery.DummyClasses.Route;

/**
 *
 * @author zofia
 */
public class RouteDBManager {
    private Connection connection;
    private List<Route> routes = new ArrayList<>();
    private static final String SELECT = "SELECT * FROM Route WHERE IdRoute = ";
    private static final String SELECT_ROUTE_QUERY = "SELECT * FROM Route WHERE Destination_Id = ";

    public RouteDBManager(Connection connection) {
        this.connection = connection;
    }

    public List<Route> getRoutes(String query) {
        routes.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String idRoute = result.getString("IdRoute");
                String destinationId = result.getString("Destination_Id");
                boolean availability = result.getBoolean("Availability");
                boolean disabled = result.getBoolean("Disabled");
                int packages = result.getInt("PackagesInRoute");
                Route route = new Route(idRoute, destinationId, availability, disabled, packages);
                routes.add(route);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return routes;
    }
    
    public Route getRouteInList(String routeId) {
        Route route;
        String destinationQuery = SELECT + "'"+ routeId + "';";
        this.routes = getRoutes(destinationQuery);
        if(this.routes.isEmpty()) {
            route = null;
            return route;
        } else {
            route = this.routes.get(0);
            return route;
        } 
    }
    
     public Route getRoute(String destinationId) {
        Route route;
        String destinationQuery = SELECT_ROUTE_QUERY + "'"+ destinationId + "';";
        this.routes = getRoutes(destinationQuery);
        if(this.routes.isEmpty()) {
            route = null;
            return route;
        } else {
            route = this.routes.get(0);
            return route;
        } 
    }
    
     public void addRoute(String idRoute, String destinationId, boolean availability, 
             boolean disabled, int packagesInRoute) throws SQLException, Exception {
        Route route = getRouteInList(idRoute);
        if(route == null) {
            try {
                String query = ("INSERT INTO Route (IdRoute, Destination_Id, Availability, Disabled, PackagesInRoute) " 
                        + "VALUES (?, ?, ?, ?, ?)");

                PreparedStatement object = connection.prepareStatement(query);
                object.setString(1, idRoute);
                object.setString(2, destinationId);
                object.setBoolean(3, availability);
                object.setBoolean(4, disabled);
                object.setInt(5, packagesInRoute);
                object.execute();
                
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new Exception("Esta ruta ya existe");
        }
    }
    
}
