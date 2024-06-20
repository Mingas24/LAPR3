package lapr.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import lapr.project.data.RegisteredUserDB;
import lapr.project.model.RegisteredUser;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Toshiba
 */
public class AddRegisteredUserControllerTest {

    RegisteredUserDB registeredUserDB;
    RegisteredUser registeredUser, test;

    public AddRegisteredUserControllerTest() {
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        registeredUserDB = new RegisteredUserDB();
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {

    }

    @BeforeEach
    public void setUp() {
        registeredUser = new RegisteredUser("filipe", "filipe123@gmail.com", "male", 1, 40, 1.1, 123456777, "fifi", 0);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddRegisteredUser() throws SQLException, IOException {
        System.out.println("addRegisteredUser");
        
        try {
            AddRegisteredUserController aruc = new AddRegisteredUserController();
            boolean expResult = true;
            boolean result = aruc.addRegisteredUser(registeredUser);
            assertEquals(expResult, result);

            test = registeredUserDB.getRegisteredUser("filipe");
            assertEquals(registeredUser.getUsername(), test.getUsername());
            assertEquals(registeredUser.getEmail(), test.getEmail());
            assertEquals(registeredUser.getGender(), test.getGender());
            assertEquals(registeredUser.getHeight(), test.getHeight());
            assertEquals(registeredUser.getWeight(), test.getWeight());
            assertEquals(registeredUser.getCyclingAverageSpeed(), test.getCyclingAverageSpeed());
            assertEquals(registeredUser.getVisa(), test.getVisa());
            assertEquals(registeredUser.getPassword(), test.getPassword());
            assertEquals(registeredUser.getPoints(), test.getPoints());

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
           registeredUserDB.ClearDatabase();
        }
    }
}
