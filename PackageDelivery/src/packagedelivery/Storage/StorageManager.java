/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagedelivery.Storage;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import packagedelivery.DBmanagers.CheckpointDBManager;
import packagedelivery.DBmanagers.DBManager;
import packagedelivery.DummyClasses.Package;
import packagedelivery.DBmanagers.PackageDBManager;
import packagedelivery.DBmanagers.RegisterDBManager;
import packagedelivery.DBmanagers.RouteDBManager;
import packagedelivery.DBmanagers.StorageDBManager;
import packagedelivery.DummyClasses.Checkpoint;
import packagedelivery.DummyClasses.Register;
import packagedelivery.DummyClasses.Route;
import packagedelivery.DummyClasses.Storage;

/**
 *
 * @author zofia
 */
public class StorageManager {
    private Connection connection;
    private List<Storage> storages = new ArrayList<>();
    private List<Package> packages = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();
    private List<Checkpoint> checkpoints = new ArrayList<>();
    private StorageDBManager storageManager;
    private PackageDBManager packageManager;
    private RouteDBManager routeManager;
    private RegisterDBManager registerManager;
    private CheckpointDBManager checkpointManager;
    private DBManager manager;
    
    public StorageManager(Connection connection) {
        this.connection = connection;
        storageManager = new StorageDBManager(connection);
        routeManager = new RouteDBManager(connection);
        registerManager = new RegisterDBManager(connection);
        packageManager = new PackageDBManager(connection);
        checkpointManager = new CheckpointDBManager(connection);
        manager = new DBManager(connection);
    }
    
    public void storage() {
        
        try {
            getPackages(this.storages);
            getRoutes();
            getCheckpoints();
            movePackageInRoute();
        } catch( Exception e) {
            
        }
    }
    
    public void getPackages(List<Storage> packge) throws Exception{
        packge = storageManager.getPackagesInQueue();
        List<Storage> temporal = packge;
        Package temporalPackage;
        
        for(Storage storage : temporal) {
            temporalPackage = packageManager.getPackageInList(storage.getPackageId());
            this.packages.add(temporalPackage); 
        }
    }
    
    public void getRoutes() throws Exception{
        List<Package> temporal = this.packages;
        Route route;
        
        for(Package packge : temporal) {
            route = routeManager.getRoute(packge.getDestinationId());
            if(route.isAvailability() && !route.isDisabled()) {
                this.routes.add(route);
            }
        }
    }
    
    public void getCheckpoints() {
        List<Route> temporal = this.routes;
        Checkpoint checkpoint;
        
        for(Route route : temporal) {
            checkpoint = checkpointManager.getCheckpoint(route.getRouteId());
            if(checkpoint.isAvailability() && !checkpoint.isDisabled()) {
                this.checkpoints.add(checkpoint);
            }
        }
    }
    
    public void movePackageInRoute() throws Exception {
        List<Package> temporal = this.packages;
        List<Route> routeTemporal = this.routes;
        List<Checkpoint> checkpointTemporal = this.checkpoints;
        if(this.routes.isEmpty()) {
            
        } else {
            if(this.checkpoints.isEmpty()) {
                
            } else {
                for(Checkpoint checkpoint : checkpointTemporal) {
                    if(checkpoint.getCheckpointId().contains("1")) {
                        int queueSize = checkpoint.getQueueSize() + 1;
                        for (int i = 0; i < queueSize; i++) {
                            Date date = Date.valueOf(temporal.get(i).getDate());
                            registerManager.addRegister(temporal.get(i).getPackageId(), 
                                    checkpointTemporal.get(i).getCheckpointId(), 0, date, 0);
                            Route route = routeTemporal.get(i);
                            int packageInRoute = 1;
                            String query = "UPDATE Route SET PackagesInRoute =" + packageInRoute + 
                                    "WHERE IdRoute = '" + route.getRouteId() + "';";
                            manager.updateElement(query);
                        }
                    }
                }
            }
            
        }
    }
}
