package lapr.project.model.Location;


/**
 *
 * @author Francisco
 */
public class Park extends Location {

    private Capacity capacity;
    private ParkCharger parkCharger;

    /**
     * Full Constructor
     *
     * @param locationId id of a park
     * @param description description of a park
     * @param latitude latitude of a park
     * @param longitude longitude of a park
     * @param capacity capacity of a park
     * @param elevation elevation of a park
     * @param parkCharger
     */
    public Park(String locationId, String description, double latitude, double longitude, Capacity capacity, int elevation, ParkCharger parkCharger) {
        super(locationId, latitude, longitude, elevation, description);
        this.capacity = capacity;
        this.parkCharger = parkCharger;
    }

    /**
     * Empty COonstructor
     */
    public Park() {
        super();
        this.capacity = capacity;
        this.parkCharger = parkCharger;

    }

    /**
     *
     * @return available slots
     */
    public Capacity getCapacity() {
        return capacity;
    }

    /**
     *
     * @return available slots for bicycles
     */
    public int getBikeCapacity() {
        return capacity.getBicycleCapacity();
    }

    /**
     *
     * @return available slots for scooters
     */
    public int getScooterCapacity() {
        return capacity.getScooterCapacity();
    }

    /**
     *
     * @return the input voltage and current
     */
    public ParkCharger getParkCharger() {
        return parkCharger;
    }

    /**
     *
     * @return the voltage of the park
     */
    public int getParkInputVoltage() {
        return parkCharger.getParkInputVoltage();
    }

    /**
     *
     * @return the input current of the park
     */
    public int getParkInputCurrent() {
        return parkCharger.getParkInputCurrent();
    }

    /**
     * Sets capacity to the given parameter
     *
     * @param capacity
     */
    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }

    /**
     * Sets the parkCharge to the given parameter
     *
     * @param parkCharger
     */
    public void setParkCharger(ParkCharger parkCharger) {
        this.parkCharger = parkCharger;
    }

}
