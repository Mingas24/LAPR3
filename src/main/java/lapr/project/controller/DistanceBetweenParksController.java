package lapr.project.controller;

import lapr.project.model.Location.Park;
import lapr.project.model.PhysicsAlgorithms;

/**
 *
 * @author Nuno Capela
 */
public class DistanceBetweenParksController {
    
    /**
     *  Empty constructor
     */
    public DistanceBetweenParksController() {
    }

    /**
     *  Method that calculates the distance between two parks
     * @param park1
     * @param park2
     * @return the distance between park1 and park2
     */
    public double distanceBetweenParks(Park park1, Park park2) {
        double l1 = park1.getLatitude();
        double lo1 = park1.getLongitude();
        double l2 = park2.getLatitude();
        double lo2 = park2.getLongitude();
        return PhysicsAlgorithms.distanceBetweenPoints(l1, lo1, l2, lo2,0,0);
    }
}
