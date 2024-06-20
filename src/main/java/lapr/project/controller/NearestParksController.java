package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import lapr.project.data.LocationDB;
import lapr.project.model.Location.Park;
import static lapr.project.model.PhysicsAlgorithms.distanceBetweenPoints;

public class NearestParksController {

    /**
     *
     */
    public final LocationDB locationDB;

    /**
     * Default constructor
     */
    public NearestParksController() throws IOException, SQLException {
        locationDB = new LocationDB();
    }

    /**
     * Method that returns a list of the nearest parks from a respective
     * location
     *
     * @param latitude
     * @param longitude
     * @param radius
     * @return list of parks that are nearest to the received coordenates
     */
    public List<Park> getNearestParks(double latitude, double longitude, int radius) {
        List<Park> allParks = locationDB.getAllParks();
        List<Park> parksReturnList = new ArrayList<>();
        Map<Double, Set<Park>> parksReturnMap = new TreeMap<>();
        Set<Park> parks = null;
        for (Park p : allParks) {
            double distance = distanceBetweenPoints(latitude, longitude, p.getLatitude(), p.getLongitude(), 0, 0);
            if (distance < radius) {
                if (!parksReturnMap.containsKey(distance)) {
                    parks = new HashSet<>();
                    parksReturnMap.put(distance, parks);
                    parks.add(p);
                } else {
                    parks = parksReturnMap.get(distance);
                    parks.add(p);
                }
            }
        }
        for (Double dist : parksReturnMap.keySet()) {
            for (Park p : parksReturnMap.get(dist)) {
                parksReturnList.add(p);
            }
        }
        return parksReturnList;
    }
}
