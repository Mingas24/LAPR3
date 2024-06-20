/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Arrays;
import javax.mail.Address;
import javax.mail.MessagingException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Francisco
 */
public class SendEmailTest {

    SendEmail send;
    String email, email2, subject, text;

    public SendEmailTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        send = new SendEmail();
        email = "1180990@isep.ipp.pt";
        email2 = "";
        subject = "TESO";
        text = "COMPENSA TESOOOOO!!!";
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSend() throws MessagingException {
        boolean expResult = true;
        boolean result = send.sendLockDescription(email, subject, text);
        assertEquals(expResult, result);
        Address[] from = send.message.getFrom();
        assertEquals(Arrays.toString(from), "[lapr3.2019.g012@gmail.com]");
        String sub = send.message.getSubject();
        assertEquals(sub, subject);
    }

    @Test
    public void testSendNull() throws MessagingException {
        boolean expResult = false;
        boolean result = send.sendLockDescription(email2, subject, text);
        assertEquals(expResult, result);
        Address[] from = send.message.getFrom();
        assertEquals(Arrays.toString(from), "[lapr3.2019.g012@gmail.com]");
        String sub = send.message.getSubject();
        assertEquals(sub, subject);
    }
}
