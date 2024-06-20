package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.LocationDB;

/**
 * @author Toshiba
 */
public class RemovePOIController {


    private LocationDB locationDB;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException
     */
    public RemovePOIController() throws IOException, SQLException {
        this.locationDB = new LocationDB();
    }

    /**
     * Method that removes a Point of interest from the database
     * @param poiID id of the point of interest we want to remove
     * @return true/false if poi removed successfully or not
     */
    public boolean removePointOfInterest(String poiID) {
        return locationDB.removePointOfInterest(poiID);
    }
}

