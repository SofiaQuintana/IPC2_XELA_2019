/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.DBmanagers;

import java.sql.Connection;
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
    private static final String SELECT_ROUTE_QUERY = "SELECT * FROM Route WHERE IdRoute = ";

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
    
    
}
