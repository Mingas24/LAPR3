package lapr.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import lapr.project.data.LocationDB;
import lapr.project.model.Location.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NearestParksControllerTest {

    ParkCharger pc1;
    Capacity cap1, cap2, cap3, cap4;
    Park park1, park2, park3, park4;
    NearestParksController npc;
    AddParkController apc;
    LocationDB locationDB;

    public NearestParksControllerTest() {
        try {

            Properties properties
                    = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
        locationDB = new LocationDB();
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @AfterEach
    public void tearDown() {
    }

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        pc1 = new ParkCharger(110, 220);
        cap1 = new Capacity(50, 50);
        cap2 = new Capacity(40, 40);
        cap3 = new Capacity(30, 30);
        cap4 = new Capacity(20, 20);
        // 0.03 km
        park1 = new Park("park1", "Parque Arca d'Agua 1", 41.171419, -8.612145, cap1, 5, pc1);
        //5.30 km 
        park2 = new Park("park2", "Parque da Cidade", 41.167735, -8.675196, cap4, 0, pc1);
        //1.02 km
        park3 = new Park("park3", "Parque Perdido", 41.16197, -8.6120, cap3, 20, pc1);
        //22.24
        park4 = new Park("park4", "Parque das Pontes", 41.371140, -8.611989, cap1, 0, pc1);
//Central Coordinates: 41.171140, -8.611989
        npc = new NearestParksController();
    }

    @Test
    public void testGetNearestParksRadiusEquals1Km() throws IOException, SQLException {
        System.out.println("testGetNearestParksNullReturnList");

        try {
            apc = new AddParkController();
            locationDB.ClearDatabase();
            apc.addPark(park1);
            apc.addPark(park2);
            apc.addPark(park3);
            apc.addPark(park4);

            List<Park> expResult = new ArrayList<>();
            expResult.add(park1);

            List<Park> result = npc.getNearestParks(41.171140, -8.611989, 1);
            assertEquals(expResult.size(), result.size());
            for (int i = 0; i < result.size(); i++) {
                assertEquals(expResult.get(i).getLocationId(), result.get(i).getLocationId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            locationDB.ClearDatabase();
        }

    }

    @Test
    public void testGetNearestParksNullAllParksList() throws IOException, SQLException {
        System.out.println("testGetNearestParksNullAllParksList");
        try {
            apc = new AddParkController();
            locationDB.ClearDatabase();

            ArrayList<Park> expResult = new ArrayList<>();

            List<Park> result = npc.getNearestParks(41.171140, -8.611989, 1);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            locationDB.ClearDatabase();
        }

    }

    @Test
    public void testGetNearestParksRadius6Km() throws IOException, SQLException {
        System.out.println("testGetNearestParksRadius10Km");

        try {
            apc = new AddParkController();
            locationDB.ClearDatabase();
            apc.addPark(park1);
            apc.addPark(park2);
            apc.addPark(park3);
            apc.addPark(park4);

            ArrayList<Park> expResult = new ArrayList<>();
            expResult.add(park1);
            expResult.add(park3);
            expResult.add(park2);
            List<Park> result = npc.getNearestParks(41.171140, -8.611989, 6);
            assertEquals(expResult.size(), result.size());
            for (int i = 0; i < result.size(); i++) {
                assertEquals(expResult.get(i).getLocationId(), result.get(i).getLocationId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            locationDB.ClearDatabase();
        }
    }

}
