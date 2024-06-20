package lapr.project.model;

/**
 * @author Asus
 */
public class Path {

    private double latitudeA;
    private double longitudeA;
    private double latitudeB;
    private double longitudeB;
    private String locationA;
    private String locationB;
    private double kinetic_coefficient;
    private double windSpeed;
    private int windDirection;



    /**
     *
     * @param latA
     * @param longA
     * @param latB
     * @param longB
     * @param kcoef
     * @param windSp
     * @param windDi
     */
    public Path(double latA, double longA, double latB, double longB, double kcoef, double windSp, int windDi) {

        this.latitudeA = latA;
        this.longitudeA = longA;
        this.latitudeB = latB;
        this.longitudeB = longB;
        this.kinetic_coefficient = kcoef;
        this.windSpeed = windSp;
        this.windDirection = windDi;

    }

    public Path(String locationA, String locationB, double kinetic_coefficient, double windSpeed, int windDirection) {
        this.locationA = locationA;
        this.locationB = locationB;
        this.kinetic_coefficient = kinetic_coefficient;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    /**
     *
     * @return latitudeA
     */
    public double getlatitudeA() {
        return latitudeA;
    }

    /**
     * Sets the A latitude to the given parameter
     *
     * @param latitudeA
     */
    public void setlatitudeA(double latA) {

        this.latitudeA = latA;
    }

    /**
     *
     * @return latitudeB
     */
    public double getlatitudeB() {
        return latitudeB;
    }

    /**
     * Sets the B latitude to the given parameter
     *
     * @param latitudeB
     */
    public void setlatitudeB(double latB) {

        this.latitudeB = latB;
    }

    /**
     *
     * @return longitude A
     *
     */

    public double getlongitudeA() {
        return longitudeA;
    }

    /**
     * Sets the A longitude to the given parameter
     *
     * @param longitudeA
     */
    public void setlongitudeA(double longA) {

        this.longitudeA = longA;
    }

    /**
     *
     * @return longitude B
     *
     */
    public double getlongitudeB() {
        return longitudeB;
    }

    /**
     * Sets the B longitude to the given parameter
     *
     * @param longitudeB
     */
    public void setlongitudeB(double longB) {

        this.longitudeB = longB;
    }

    /**
     *
     * @return kinetic_coefficient
     *
     */
    public double getkinetic_coefficient() {
        return kinetic_coefficient;
    }

    /**
     * Sets the kinetic_coefficient to the given parameter
     *
     * @param kinetic_coefficient
     */
    public void setkinetic_coefficient(double coef) {

        this.kinetic_coefficient = coef;
    }

    /**
     *
     * @return windSpeed
     *
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Sets the WindSpeed to the given parameter
     *
     * @param WindSpeed
     */
    public void setWindSpeed(double windSp) {

        this.windSpeed = windSp;
    }

    /**
     *
     * @return windDirection
     *
     */
    public int getWindDirection() {
        return windDirection;
    }

    /**
     * Sets the windDirection to the given parameter
     *
     * @param windDirection
     */
    public void setWindDirection(int windDi) {

        this.windDirection = windDi;
    }

    public String getLocationA() {
        return locationA;
    }

    public void setLocationA(String locationA) {
        this.locationA = locationA;
    }

    public String getLocationB() {
        return locationB;
    }

    public void setLocationB(String locationB) {
        this.locationB = locationB;
    }
    
    
}
