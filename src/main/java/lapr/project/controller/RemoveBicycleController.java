package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.VehicleDB;

/**
 * @author Toshiba
 */
public class RemoveBicycleController {

    private VehicleDB vehicleDB;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException
     */
    public RemoveBicycleController() throws IOException, SQLException {
        this.vehicleDB = new VehicleDB();
    }

    /**
     * Method that removes a bicycle from the database
     * @param bicycleID id of the bicycle that we want to remove from the database
     * @return true/false if the bicycle was successfully removed or not 
     */
    public boolean removeBicycle(String bicycleID) {
        return vehicleDB.removeBycicle(bicycleID);
    }

}
