package lapr.project.controller;


import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle.Bicycle;

/**
 * @author diogo
 */
public class AddBicycleController {


    private VehicleDB VehicleDB;

    /**
     * Default constructor
     * @throws IOException
     * @throws SQLException 
     */
    public AddBicycleController() throws IOException, SQLException{
        VehicleDB = new VehicleDB();
    }

    /**
     * Method that adds a new bicycle to the database
     * @param bicycle to be added
     * @return  true/false if added successfully
     */
    public boolean addBicycle(Bicycle bicycle) {
        return VehicleDB.addBicycle(bicycle);
    }
}
