package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import lapr.project.data.TripDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Park;
import lapr.project.model.Trip;
import lapr.project.model.Vehicle.Scooter;

/**
 *
 * @author Nuno Capela
 */
public class UnlockScooterController {

    private TripDB tripDB;
    private VehicleDB vehicleDB;

    private Trip trip;
    private Scooter scooter;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException
     */
    public UnlockScooterController() throws IOException, SQLException {
        tripDB = new TripDB();
        vehicleDB = new VehicleDB();
    }

    /**
     * Method that creates a new trip
     * @param username for the new trip
     * @param vehicleId for the new trip
     * @return true/false if the trip was successfully created or not
     */
    public boolean addAndCreateTrip(String username, String vehicleId) {
        System.out.println(vehicleId);
        scooter = vehicleDB.getScooter(vehicleId);

        trip = new Trip(0, username, vehicleId, scooter.getParkID(), "", LocalDateTime.now(), null);
        return tripDB.addTrip(trip);
    }

}
