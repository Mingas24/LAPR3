package lapr.project.model.Location;

/**
 * @author Toshiba
 */
public class ParkCharger {

    private int parkInputVoltage;
    private int parkInputCurrent;

    /**
     * Full Constructor
     * @param parkInputVoltage
     * @param parkInputCurrent
     */
    public ParkCharger(int parkInputVoltage, int parkInputCurrent) {
        this.parkInputVoltage = parkInputVoltage;
        this.parkInputCurrent = parkInputCurrent;
    }

    /**
     *
     * @return the park input voltage
     */
    public int getParkInputVoltage() {
        return parkInputVoltage;
    }

    /**
     * Changes the park input voltage to the given parameter
     * @param parkInputVoltage
     */
    public void setParkInputVoltage(int parkInputVoltage) {
        this.parkInputVoltage = parkInputVoltage;
    }

    /**
     *
     * @return the park input current
     */
    public int getParkInputCurrent() {
        return parkInputCurrent;
    }

    /**
     * Changes the park input current to the given parameter
     * @param parkInputCurrent
     */
    public void setParkInputCurrent(int parkInputCurrent) {
        this.parkInputCurrent = parkInputCurrent;
    }

}
