package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle.Bicycle;

/**
 * @author Toshiba
 */
public class UpdateBicycleController {
    private VehicleDB vehicleDB;

    /**
     * Default constructor
     * @throws SQLException
     * @throws IOException
     */
    public UpdateBicycleController() throws SQLException, IOException{
        this.vehicleDB = new VehicleDB();
    }
    
    /**
     * Method for updating bicycle information
     * @param bicycle to be updated 
     * @return true/false if the bicycle was successfully updated or not 
     */
    public boolean updateBicycle(Bicycle bicycle){
        return vehicleDB.updateBicycle(bicycle);        
    }
}
