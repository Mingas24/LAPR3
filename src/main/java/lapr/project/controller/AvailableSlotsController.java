package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.LocationDB;

/**
 * @author Francisco
 */
public class AvailableSlotsController {

    private final LocationDB locationDB;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException
     */
    public AvailableSlotsController() throws IOException, SQLException {
        locationDB = new LocationDB();
    }

    /**
     * Method that returns the number of available parking slots for bicycles
     * @param parkID ID of the park for which we want to know the available slots
     * @return the number of available parking slots
     * @throws SQLException
     */
    public int availableSlotsBike(String parkID) throws SQLException {
        return locationDB.parkDisponibilityBike(parkID);
    }
    
    /**
     * Method that returns the number of available parking slots for scooter
     * @param parkID ID of the park for which we want to know the available slots
     * @return the number of available parking slots
     * @throws SQLException
     */
    public int availableSlotsScooter(String parkID) throws SQLException {
        return locationDB.parkDisponibilityScooter(parkID);

    }
}