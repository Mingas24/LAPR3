package lapr.project.data;

import lapr.project.model.Path;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Nuno Capela
 */
public class PathDB extends DataHandler {

    public boolean addPath(Path newPath) throws SQLException {
        return addPath(newPath.getlatitudeA(), newPath.getlongitudeA(), newPath.getlatitudeB(),
                newPath.getlongitudeB(), newPath.getkinetic_coefficient(), newPath.getWindSpeed(), newPath.getWindDirection());
    }

    private boolean addPath(double pathLatA, double pathLongA, double pathLatB,
            double pathLongB, double newKinCoef, double newWindSpeed, int newWindDir) throws SQLException {

        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call addPaths(?,?,?,?,?,?,?) }");
            callStmt.setDouble(1, pathLatA);
            callStmt.setDouble(2, pathLongA);
            callStmt.setDouble(3, pathLatB);
            callStmt.setDouble(4, pathLongB);
            callStmt.setDouble(5, newKinCoef);
            callStmt.setDouble(6, newWindSpeed);
            callStmt.setInt(7, newWindDir);
            callStmt.execute();
            closeAll();
            System.out.println("Added Path to DB");
            return true;

        } catch (SQLException e) {
            closeAll();
            e.printStackTrace();
            return false;
        }
    }

    public Path getPath(String identification1, String identification2) {
        CallableStatement callStmt = null;
        String idLocation1, idLocation2;
        double kinetic_coefficient;
        double windSpeed;
        int windDirection;

        try {

            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getPaths(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, identification1);
            callStmt.setString(3, identification2);

            callStmt.execute();

            ResultSet result = (ResultSet) callStmt.getObject(1);

            if (result.next()) {
                idLocation1 = result.getString(1);
                idLocation2 = result.getString(2);
                kinetic_coefficient = result.getDouble(3);
                windDirection = result.getInt(4);
                windSpeed = result.getDouble(5);
                Path path = new Path(idLocation1, idLocation2, kinetic_coefficient, windSpeed, windDirection);
                return path;
            }
            closeAll();

        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the path");
        }

        return null;
    }
    
    public List<Path> getAllPaths(){
        List<Path> paths = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getAllPaths }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet result = (ResultSet) callStmt.getObject(1);
            while(result.next()){
                String locationA = result.getString(1);
                String locationB = result.getString(2);
                double kinetic_coefficient = result.getDouble(3);
                double windSpeed = result.getDouble(4);
                int windDirection = result.getInt(5);
                paths.add(new Path(locationA, locationB, kinetic_coefficient, windSpeed, windDirection));
            }
            closeAll();
            return paths;
        } catch (SQLException e){
            closeAll();
        }
        return paths;
    }
}
