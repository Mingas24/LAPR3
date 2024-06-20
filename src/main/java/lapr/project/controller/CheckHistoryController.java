/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.data.RegisteredUserDB;
import lapr.project.model.Trip;

/**
 *
 * @author Francisco
 */
public class CheckHistoryController {
    
    private RegisteredUserDB registeredUserDB;
            
    public CheckHistoryController(){
        registeredUserDB = new RegisteredUserDB();
    }
    
    public List<Trip> checkHistory(String username) throws SQLException{
        return registeredUserDB.checkHistory(username);
    }
    
}
