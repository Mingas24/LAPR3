package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle.Scooter;

/**
 * @author diogo
 */
public class UpdateScooterController {

    private VehicleDB VehicleDB;

    /**
     * Default constructor
     * @throws SQLException
     * @throws IOException
     */
    public UpdateScooterController() throws SQLException, IOException {
        VehicleDB = new VehicleDB();
    }

    /**
     * Method that updated a new scooter 
     * @param Scooter new scooter to be updated 
     * @return true/false if successfully updated or not 
     */
    public boolean updateScooter(Scooter Scooter) {
        return VehicleDB.updateScooter(Scooter);
    }
}
