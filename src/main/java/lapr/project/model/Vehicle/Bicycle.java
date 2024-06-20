package lapr.project.model.Vehicle;

/**
 *
 * @author Nuno Capela
 */
public class Bicycle extends Vehicle {

    private int wheelSize;

    /**
     * Full Constructor
     *
     * @param vehicleID id of a bicycle
     * @param vehicleInfos
     * @param vehicleCharacteristics
     * @param wheelSize size of the bike wheels
     */
    public Bicycle(String vehicleID, VehicleInfos vehicleInfos, VehicleCharacteristics vehicleCharacteristics, int wheelSize) {

        super(vehicleID, vehicleInfos, vehicleCharacteristics);
        this.wheelSize = wheelSize;
    }


    /**
     *
     * @return the bicycle size
     */
    public int getWheelSize() {
        return wheelSize;
    }

    /**
     * Sets the bicycle size to the given parameter
     *
     * @param wheelSize
     */
    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

}
