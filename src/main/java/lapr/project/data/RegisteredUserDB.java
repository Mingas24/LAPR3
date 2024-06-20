package lapr.project.data;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.RegisteredUser;
import lapr.project.model.Trip;

public class RegisteredUserDB extends DataHandler {

    public boolean addRegisteredUser(RegisteredUser regUser) {
        return addRegisteredUser(regUser.getUsername(), regUser.getEmail(), regUser.getGender(), regUser.getHeight(), regUser.getWeight(), regUser.getCyclingAverageSpeed(), regUser.getVisa(), regUser.getPassword(), regUser.getPoints());
    }

    private boolean addRegisteredUser(String username, String email, String gender, int height, int weight, double cyclingAverageSpeed, long visa, String password, int points) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{call addRegisteredUser(?,?,?,?,?,?,?,?,?) }");
            callStmt.setString(1, username);
            callStmt.setString(2, email);
            callStmt.setString(3, gender);
            callStmt.setInt(4, height);
            callStmt.setInt(5, weight);
            callStmt.setDouble(6, cyclingAverageSpeed);
            callStmt.setString(7, password);
            callStmt.setLong(8, visa);
            callStmt.setInt(9, points);

            callStmt.execute();
            closeAll();
            return true;

        } catch (SQLException e) {
            closeAll();
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeRegisteredUser(String username) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeRegisteredUser(?) }");
            callStmt.setString(1, username);
            callStmt.execute();
            closeAll();
            return true;
        } catch (SQLException e) {
            closeAll();
            e.printStackTrace();
            return false;
        }
    }

    public RegisteredUser getRegisteredUser(String username) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ?= call getUser(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, username);
            callStmt.execute();
            ResultSet result = (ResultSet) callStmt.getObject(1);

            if (result.next()) {
                String user = result.getString(1);
                String email = result.getString(2);
                String gender = result.getString(3);
                int height = result.getInt(4);
                int weight = result.getInt(5);
                double cyclingAverageSpeed = result.getDouble(6);
                long visa = result.getLong(7);
                String password = result.getString(8);
                int points = result.getInt(9);
                RegisteredUser regUser = new RegisteredUser(user, email, gender, height, weight, cyclingAverageSpeed, visa, password, points);
                return regUser;
            }
            closeAll();
        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the user");
        }
        throw new IllegalArgumentException();
    }

    public boolean updateUserPoints(RegisteredUser user) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{call updateUserPoints(?,?) }");
            callStmt.setString(1, user.getUsername());
            callStmt.setInt(2, user.getPoints());
            callStmt.execute();
            closeAll();
            return true;

        } catch (SQLException e) {
            closeAll();
            e.printStackTrace();
            return false;
        }
    }

    public List<Trip> checkHistory(String username) throws SQLException {
        CallableStatement callStmt = null;
        List<Trip> trips = new ArrayList<>();
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ?= call getHistory(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, username);
            callStmt.execute();
            ResultSet result = (ResultSet) callStmt.getObject(1);
            while (result.next()) {
                int tripID = result.getInt(1);
                String usern = result.getString(2);
                String vehicleID = result.getString(3);
                String startPark = result.getString(4);
                String endPark = result.getString(5);
                LocalDateTime startTime = result.getTimestamp(6).toLocalDateTime();
                LocalDateTime endTime = result.getTimestamp(7).toLocalDateTime();
                Trip e = new Trip(tripID, usern, vehicleID, startPark, endPark, startTime, endTime);
                trips.add(e);
            }
            closeAll();
            return trips;
        } catch (SQLException e) {
            closeAll();
            return null;
        }
    }
}
