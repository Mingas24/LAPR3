package lapr.project.model;

public class RegisteredUser {

    String username;
    String email;
    String gender;
    int height;
    int weight;
    double cyclingAverageSpeed;
    long visa;
    String password;
    int points;

    /**
     * Full Constructor
     *
     * @param username username of a registered user
     * @param email email of a registered user
     * @param gender gender of a registered user
     * @param height height of a registered user
     * @param weight weight of a registered user
     * @param cyclingAverageSpeed average speed of a registered user
     * @param visa credit card number of a registered user
     * @param password password of a registered user
     * @param points points of a registered user
     */
    public RegisteredUser(String username, String email, String gender, int height, int weight, double cyclingAverageSpeed, long visa, String password, int points) {
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.cyclingAverageSpeed = cyclingAverageSpeed;
        this.visa = visa;
        this.password = password;
        this.points = points;
    }

    /**
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return the height
     */
    public int getHeight() {
        return height;
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
     * @return the cycling average speed
     */
    public double getCyclingAverageSpeed() {
        return cyclingAverageSpeed;
    }

    /**
     *
     * @return the credit card
     */
    public long getVisa() {
        return visa;
    }

    /**
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
