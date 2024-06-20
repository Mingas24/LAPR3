package lapr.project.controller;

import lapr.project.model.Location.PointOfInterest;
import lapr.project.data.LocationDB;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Francisco
 */
public class AddPOIController {

    private LocationDB poiDB;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException
     */
    public AddPOIController() throws IOException, SQLException {

        this.poiDB = new LocationDB();

    }

    /**
     * Method that adds a new Point of Interest to the database
     * @param poi point of interest to be added
     * @return true/false if added successfully
     */
    public boolean addPoint(PointOfInterest poi) {

        return poiDB.addPointOfInterest(poi);
    }

}
