/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import lapr.project.utils.SendEmail;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Francisco
 */
public class EmailDescriptionControllerTest {

    SendEmail sendEmail;
    String email, email2, subject, text;
    EmailDescriptionController inv;

    public EmailDescriptionControllerTest() {
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
        sendEmail = new SendEmail();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        email = "1180990@isep.ipp.pt";
        email2 = "";
        subject = "TESO";
        text = "COMPENSA TESOOOOOOOOOO!!!!!";
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void sendEmailTest() {
        System.out.println("sendEmailTest");
        inv = new EmailDescriptionController(email, subject, text);
        boolean expResult = true;
        boolean result = inv.sendEmail();
        assertEquals(expResult, result);

        inv = new EmailDescriptionController(email2, subject, text);
        expResult = false;
        result = inv.sendEmail();
        assertEquals(expResult, result);
    }

}
