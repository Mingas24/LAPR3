package lapr.project.model.Vehicle;

/**
 * @author diogo
 */
public class Battery {

    private double maxBatt;
    private double actualBatt;

    /**
     * Full Constructor
     *
     * @param maxBatt maximum battery of a vehicle
     * @param actualBatt actual battery of a vehicle
     */
    public Battery(double maxBatt, double actualBatt) {
        this.maxBatt = maxBatt;
        this.actualBatt = actualBatt;
    }

    /**
     * Empty Constructor
     */
    public Battery() {
        this.actualBatt = 0;
        this.maxBatt = 0;
    }

    /**
     *
     * @return the maximum battery of a vehicle
     */
    public double getMaxBatt() {
        return maxBatt;
    }

    /**
     *
     * @return the actual battery of a vehicle
     */
    public double getActualBatt() {
        return actualBatt;
    }

    /**
     * Changes the maximum battery to the given parameter
     *
     * @param maxBatt
     */
    public void setMaxBatt(double maxBatt) {
        this.maxBatt = maxBatt;
    }

    /**
     * Changes the actual battery to the given parameter
     *
     * @param actualBatt
     */
    public void setActualBatt(double actualBatt) {
        this.actualBatt = actualBatt;
    }

}
