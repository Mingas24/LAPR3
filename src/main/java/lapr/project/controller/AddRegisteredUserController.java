package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.RegisteredUserDB;
import lapr.project.model.RegisteredUser;

/**
 *
 * @author Nuno Capela
 */
public class AddRegisteredUserController {

    private RegisteredUserDB registeredUserDB;

    /**
     * Default constructor
     * @throws SQLException
     */
    public AddRegisteredUserController() throws SQLException, IOException{
        registeredUserDB = new RegisteredUserDB();
    }

    /**
     * Method that adds a new registered user to the database
     * @param registeredUser to be added
     * @return true/false if added successfully
     */
    public boolean addRegisteredUser(RegisteredUser registeredUser) {
        return registeredUserDB.addRegisteredUser(registeredUser);
    }
}
