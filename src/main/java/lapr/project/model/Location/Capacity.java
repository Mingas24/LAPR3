package lapr.project.model.Location;

/**
 *
 * @author Francisco
 */
public class Capacity {

    private int bicycles;
    private int scooters;

    /**
     * Gives the available slots for both types of vehicles
     *
     * @param bicycles available slots for bicycles
     * @param scooters available slots for scooters
     */
    public Capacity(int bicycles, int scooters) {
        this.bicycles = bicycles;
        this.scooters = scooters;
    }

    /**
     *
     * @return the available spots for bicycles
     */
    public int getBicycleCapacity() {
        return bicycles;
    }

    /**
     *
     * @return the available spots for scooters
     */
    public int getScooterCapacity() {
        return scooters;
    }

    /**
     * Sets bicycles slots to the given parameter
     *
     * @param bicycles
     */
    public void setBicycleCapacity(int bicycles) {
        this.bicycles = bicycles;
    }

    /**
     * Sets scooters slots to the given parameter
     *
     * @param scooters
     */
    public void setScooterCapacity(int scooters) {
        this.scooters = scooters;
    }

}
