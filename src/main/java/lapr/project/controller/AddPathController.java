package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.PathDB;
import lapr.project.model.Path;

/**
 *
 * @author Nuno Capela
 */
public class AddPathController {

    private PathDB pathDB;

    /**
     * Default constructor
     *
     * @throws IOException
     * @throws SQLException
     */
    public AddPathController() throws IOException, SQLException {
        pathDB = new PathDB();
    }

    /**
     * Method that adds a new path to the database
     *
     * @param newPath to be added
     * @return true/false if added successfully
     */
    public boolean addPath(Path newPath) throws SQLException {
        return pathDB.addPath(newPath);
    }

}
