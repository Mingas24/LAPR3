package lapr.project.model.Vehicle;

/**
 *
 * @author diogo
 */
public class VehicleCharacteristics {

    private int weight;
    private double aerodynamicCoefficient;
    private double frontalArea;

    /**
     * EMPTY CONSTROCTOR
     */
    public VehicleCharacteristics() {
    }

    /**
     *
     * @param weight
     * @param aerodynamicCoefficient
     * @param frontalArea
     */
    public VehicleCharacteristics(int weight, double aerodynamicCoefficient, double frontalArea) {
        this.weight = weight;
        this.aerodynamicCoefficient = aerodynamicCoefficient;
        this.frontalArea = frontalArea;
    }

    /**
     *
     * @return the weight 
     */
    public int getWeight() {
        return weight;
    }

    /**
     *
     * @return the aerodynamic coefficient
     */
    public double getAerodynamicCoefficient() {
        return aerodynamicCoefficient;
    }

    /**
     *
     * @return the frontal area
     */
    public double getFrontalArea() {
        return frontalArea;
    }

    /**
     * Changes the weight to the given parameter
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Changes the aerodynamic coefficient to the given parameter
     * @param aerodynamicCoefficient
     */
    public void setAerodynamicCoefficient(double aerodynamicCoefficient) {
        this.aerodynamicCoefficient = aerodynamicCoefficient;
    }

    /**
     * Changes the frontal area to the given parameter
     * @param frontalArea
     */
    public void setFrontalArea(double frontalArea) {
        this.frontalArea = frontalArea;
    }

}
