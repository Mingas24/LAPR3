package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.VehicleDB;

/**
 * @author diogo
 */
public class RemoveScooterController {


    private VehicleDB VehicleDB;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException
     */
    public RemoveScooterController() throws IOException, SQLException {
        this.VehicleDB = new VehicleDB();

    }

    /**
     * Method that removes a scooter from the database 
     * @param ScooterId id of the scooter we want to remove
     * @return true/false if the scooter was successfully removed or not
     */
    public boolean removeScooter(String ScooterId) {
        return VehicleDB.removeScooter(ScooterId);
    }
}
