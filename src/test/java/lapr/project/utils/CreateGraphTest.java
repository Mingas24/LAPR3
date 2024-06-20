/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import lapr.project.controller.*;
import lapr.project.data.*;
import lapr.project.model.Location.*;
import lapr.project.model.Path;
import lapr.project.utils.graphbase.Graph;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Nuno Capela
 */
public class CreateGraphTest {

    LocationDB locationDB;
    PathDB pathDB;
    AddParkController aparkc;
    AddPathController apathc;
    AddPOIController apoic;
    PointOfInterest poi1, poi2;
    Park park1;
    Path path1, path2;
    CreateGraphs cg;

    public CreateGraphTest() {
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
        pathDB = new PathDB();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        Capacity capacity = new Capacity(20, 10);
        ParkCharger charger = new ParkCharger(120, 120);

        poi1 = new PointOfInterest("1", 41.148518, -8.644335, 4, "testRibeira");
        poi2 = new PointOfInterest("2", 41.14788, -8.61112, 5, "testAliados");
        park1 = new Park("001", "Sé do Porto", 41.14278, -8.60928, capacity, 100, charger);
        path1 = new Path(park1.getLatitude(), park1.getLongitude(), poi1.getLatitude(), poi1.getLongitude(), 0.004, 10, 15);
        path2 = new Path(poi2.getLatitude(), poi2.getLongitude(), park1.getLatitude(), park1.getLongitude(), 0.004, 8, 12);
    }

    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testCreateCaloriesTest() throws SQLException, IOException {
        try {
            aparkc = new AddParkController();
            apathc = new AddPathController();
            apoic = new AddPOIController();
            cg = new CreateGraphs();

            aparkc.addPark(park1);
            apoic.addPoint(poi1);
            apoic.addPoint(poi2);
            apathc.addPath(path1);
            apathc.addPath(path2);
            int resultVert = 3;
            int resultEdges = 2;
            Graph<Location, Double> graphCal = cg.createGraphCalories();
            int expResultVert = graphCal.numVertices();
            int expResultEdges = graphCal.numEdges();

            assertEquals(expResultVert, resultVert);
            assertEquals(expResultEdges, resultEdges);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            pathDB.ClearDatabase();
        }
    }

    @Test
    public void testCreateDistTest() throws SQLException, IOException {
        try {
            aparkc = new AddParkController();
            apathc = new AddPathController();
            apoic = new AddPOIController();
            cg = new CreateGraphs();

            aparkc.addPark(park1);
            apoic.addPoint(poi1);
            apoic.addPoint(poi2);
            apathc.addPath(path1);
            apathc.addPath(path2);
            int resultVert = 3;
            int resultEdges = 2;

            System.out.println();
            Graph<Location, Double> graphDist = cg.createGraphDistance();

            int expResultVert = graphDist.numVertices();
            int expResultEdges = graphDist.numEdges();

            assertEquals(expResultVert, resultVert);
            assertEquals(expResultEdges, resultEdges);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            pathDB.ClearDatabase();
        }
    }

    

    @Test
    public void testCreateEnergyTest() throws SQLException, IOException {
        try {
            aparkc = new AddParkController();
            apathc = new AddPathController();
            apoic = new AddPOIController();
            cg = new CreateGraphs();

            aparkc.addPark(park1);
            apoic.addPoint(poi1);
            apoic.addPoint(poi2);
            apathc.addPath(path1);
            apathc.addPath(path2);
            int resultVert = 3;
            int resultEdges = 2;

            Graph<Location, Double> graphEnergy = cg.createGraphEnergy();

            int expResultVert = graphEnergy.numVertices();
            int expResultEdges = graphEnergy.numEdges();

            assertEquals(expResultVert, resultVert);
            assertEquals(expResultEdges, resultEdges);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            pathDB.ClearDatabase();
        }
    }

}
