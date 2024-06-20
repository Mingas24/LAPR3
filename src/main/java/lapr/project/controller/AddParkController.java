package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.LocationDB;
import lapr.project.model.Location.Park;

/**
 * @author diogo
 */
public class AddParkController {

    private LocationDB parkDB;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException
     */
    public AddParkController() throws IOException, SQLException {

        this.parkDB = new LocationDB();

    }

    /**
     * Method that adds a new park to the database
     * @param park to be added
     * @return true/false if added successfully
     */
    public boolean addPark(Park park) {
        return parkDB.addPark(park);
    }


}
