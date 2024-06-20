package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.LocationDB;

/**
 * @author diogo
 */
public class RemoveParkController {

    private LocationDB parkDB;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException
     */
    public RemoveParkController() throws IOException, SQLException {
        this.parkDB = new LocationDB();

    }

    /**
     * Method that removes a park from the database
     * @param parkID park that we want to remove
     * @return true/false if the park was successfully removed or not
     */
    public boolean removePark(String parkID) {
        return parkDB.removePark(parkID);
    }
}
