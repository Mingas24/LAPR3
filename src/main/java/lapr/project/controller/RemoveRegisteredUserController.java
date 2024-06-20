package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.RegisteredUserDB;

/**
 * @author Toshiba
 */
public class RemoveRegisteredUserController {

    private RegisteredUserDB registeredUserDB;

    public RemoveRegisteredUserController() throws SQLException, IOException {
        registeredUserDB = new RegisteredUserDB();
    }
    
    public boolean removeRegisteredUser(String username){
        return registeredUserDB.removeRegisteredUser(username);
    }
}
