package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle.Scooter;

/**
 * @author diogo
 */
public class AddScooterController {
    private final VehicleDB VehicleDB;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException
     */
    public AddScooterController() throws IOException, SQLException{
        VehicleDB = new VehicleDB();
    }

    /**
     * Method that adds a new scooter to the database
     * @param Scooter to be added
     * @return true/false if added successfully
     */
    public boolean addScooter(Scooter Scooter) {
        return VehicleDB.addScooter(Scooter);
    }
}
