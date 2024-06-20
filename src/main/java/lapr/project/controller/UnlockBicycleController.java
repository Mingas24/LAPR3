package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import lapr.project.data.TripDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Park;
import lapr.project.model.Trip;
import lapr.project.model.Vehicle.Bicycle;

/**
 * @author diogo
 */
public class UnlockBicycleController {

    private TripDB TripDB;

    VehicleDB VehicleDB;
    Trip trip;
    Bicycle bike;
    Park Park;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException
     */
    public UnlockBicycleController() throws IOException, SQLException {
        TripDB = new TripDB();
        VehicleDB = new VehicleDB();
    }

    /**
     * Method that creates a new trip
     * @param username for the new trip
     * @param vehicleId for the new trip
     * @return true/false if the trip was successfully created or not
     */
    public boolean addAndCreateTrip(String username, String vehicleId) {
        
        bike = VehicleDB.getBycicle(vehicleId);
        System.out.println("encontrou a bike");
     
        trip = new Trip(0, username, vehicleId, bike.getParkID(), "", LocalDateTime.now(),null);
        return TripDB.addTrip(trip);
    }


}
