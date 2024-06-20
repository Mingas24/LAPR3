package lapr.project.model.Vehicle;

/**
 *
 * @author Francisco
 */
public class Scooter extends Vehicle {

    private String typeOfScooter;
    private int motor;
    private Battery battery;

    /**
     *
     * @param vehicleID
     * @param typeOfScooter
     * @param vehicleInfos
     * @param vehicleCharacteristics
     * @param battery
     * @param motor
     */
    public Scooter(String vehicleID, String typeOfScooter, VehicleInfos vehicleInfos, VehicleCharacteristics vehicleCharacteristics, Battery battery, int motor) {

        super(vehicleID, vehicleInfos, vehicleCharacteristics);
        this.typeOfScooter = typeOfScooter;
        this.battery = battery;
        this.motor = motor;
    }

    public Scooter() {
        super();
        this.typeOfScooter = "";
        this.battery = new Battery();
        this.motor = 0;
    }

    /**
     *
     * @return max battery of a scooter
     */
    public double getMaxBattery() {
        return battery.getMaxBatt();
    }

    /**
     * Sets the max battery to the given parameter
     *
     * @param maxBattery
     */
    public void setMaxBattery(double maxBattery) {
        this.battery.setMaxBatt(maxBattery);
    }

    /**
     *
     * @return the actual battery of a scooter
     */
    public double getActualBattery() {
        return battery.getActualBatt();
    }

    /**
     * Sets the actual battery to the given parameter
     *
     * @param actualBattery
     */
    public void setActualBattery(double actualBattery) {
        this.battery.setActualBatt(actualBattery);
    }

    /**
     *
     * @return the type of a scooter
     */
    public String getTypeOfScooter() {
        return typeOfScooter;
    }

    /**
     * Sets the type of the scooter to the given parameter
     *
     * @param typeOfScooter
     */
    public void setTypeOfScooter(String typeOfScooter) {
        this.typeOfScooter = typeOfScooter;
    }

    /**
     *
     * @return the motor
     */
    public int getMotor() {
        return motor;
    }

    /**
     * Changes the motor to the given parameter
     *
     * @param motor
     */
    public void setMotor(int motor) {
        this.motor = motor;
    }

    /**
     *
     * @return the battery
     */
    public Battery getBattery() {
        return battery;
    }

    /**
     * Changes the battery to the given parameter
     *
     * @param battery
     */
    public void setBattery(Battery battery) {
        this.battery = battery;
    }

}
