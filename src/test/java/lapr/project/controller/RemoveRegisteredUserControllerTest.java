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
public class RemoveRegisteredUserControllerTest {

    RegisteredUserDB registeredUserDB;
    RegisteredUser registeredUser, test;

    public RemoveRegisteredUserControllerTest() {
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
    public void testRemoveRegisteredUserUser() throws SQLException, IOException {
        System.out.println("removeRegisteredUser");
        try {
            AddRegisteredUserController aruc = new AddRegisteredUserController();
            RemoveRegisteredUserController rruc = new RemoveRegisteredUserController();
            aruc.addRegisteredUser(registeredUser);
            boolean expResult = true;
            boolean result = rruc.removeRegisteredUser(registeredUser.getUsername());
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            registeredUserDB.ClearDatabase();
        }
    }
}
