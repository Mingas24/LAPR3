package lapr.project.model.Location;


/**
 *
 * @author Francisco
 */
public class PointOfInterest extends Location {

    /**
     * Full constructor
     *
     * @param locationId id of a point of interest
     * @param latitude latitude of a point of interest
     * @param longitude longitude of a point of interest
     * @param elevation elevation of a point of interest
     * @param poiDescription description of a point of interest
     */
    public PointOfInterest(String locationId, double latitude, double longitude, int elevation, String poiDescription) {
        super(locationId, latitude, longitude, elevation, poiDescription);
    }

    /**
     * Empty constructor
     */
    public PointOfInterest() {
        super();
    }


}
