/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.LocationDB;
import lapr.project.model.Location.Park;

/**
 *
 * @author Asus
 */
public class UpdateParkController {
     private LocationDB parkDB;

    /**
     * Default constructor 
     * @throws SQLException
     */
    public UpdateParkController()throws SQLException {
        parkDB = new LocationDB();
    }

    /**
     * Method that updates a park
     * @param park new park to be updated 
     * @return true/false if the park was successfully updated or not 
     */
    public boolean updatePark(Park park) {
        return parkDB.updatePark(park);
    }
    
}
