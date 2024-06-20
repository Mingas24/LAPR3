package lapr.project.model.Location;

/**
 *
 * @author Francisco
 */
public abstract class Location {

    private String locationId;
    private double latitude;
    private double longitude;
    private int elevation;
    private String description;

    /**
     * Full constructor
     *
     * @param locationId id of a location
     * @param latitude latitude of a location
     * @param longitude longitude of a location
     * @param elevation elevation of a location
     * @param description description of a location
     */
    public Location(String locationId, double latitude, double longitude, int elevation, String description) {
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.description = description;

    }

    /**
     * Empty Constructor
     */
    public Location() {
        this.locationId = "";
        this.latitude = 0;
        this.longitude = 0;
        this.elevation = 0;
        this.description = "";
 }
    /**
     *
     * @return the id of a location
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * Sets the id of a location to the given parameter
     *
     * @param locationId
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    /**
     *
     * @return the latitude of a location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of a location to the given parameter
     *
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return the longitude of a location
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of a location to the given parameter
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return the elevation of a location
     */
    public int getElevation() {
        return elevation;
    }

    /**
     * Sets the elevation of a location to the given parameter
     *
     * @param elevation
     */
    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    /**
     *
     * @return the description of a location
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of a location to the given parameter
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "locationId=" + locationId + ", description=" + description;
    }

    @Override
    public boolean equals(Object obj) {
        Location obj2 = (Location) obj;
        return this.getLocationId().equalsIgnoreCase(obj2.getLocationId());
    }

    
}
