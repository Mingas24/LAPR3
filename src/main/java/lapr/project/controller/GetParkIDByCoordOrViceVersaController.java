package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import lapr.project.data.LocationDB;

/**
 * @author Toshiba
 */
public class GetParkIDByCoordOrViceVersaController {

    LocationDB locationDB;

    public GetParkIDByCoordOrViceVersaController() throws IOException, SQLException {
        locationDB = new LocationDB();
    }

    public String getParkIDByCoord(double latitude, double longitude) {
        return locationDB.getParkIDbyCoordinates(latitude, longitude);
    }

    public List<Double> getCoordinatesByParkID(String parkID) {
        return locationDB.getCoordinatesByParkID(parkID);
    }

}
