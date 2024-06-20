package lapr.project.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import lapr.project.data.LocationDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle.Bicycle;
import lapr.project.model.Vehicle.Scooter;


public class AvailableVehiclesInAParkController {

 
  private VehicleDB vehicleDB;
  
   
   public AvailableVehiclesInAParkController() throws SQLException, IOException{
        this.vehicleDB = new VehicleDB();
    
   }
   public List<Scooter> getAllAvailableScooterInPark(String parkID){
        return vehicleDB.getAllAvailableScooterInPark(parkID);        
  }
    
    public List<Bicycle> getAllAvailableBikeInPark(String parkID){
        return vehicleDB.getAllAvailableBikeInPark(parkID);        
    }
    
    
}
