/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Francisco
 */
public class SendEmail {

    Properties properties;
    Session session;
    MimeMessage message;

    /**
     * Empty Constructor
     */
    public SendEmail() {
        this.properties = System.getProperties();
        this.session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lapr3.2019.g012@gmail.com", "duziaLapr12");
            }
        });
        this.message = new MimeMessage(session);
    }

    /**
     * Method that receives the email of a user and sends him an email with the
     * invoice
     *
     * @param email
     * @param subject
     * @param text
     * @return
     */
    public boolean sendLockDescription(String email, String subject, String text) {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        try {
            message.setFrom("lapr3.2019.g012@gmail.com");
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

}
