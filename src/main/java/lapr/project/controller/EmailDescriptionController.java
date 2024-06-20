package lapr.project.controller;

import lapr.project.utils.SendEmail;


/**
 *
 * @author Francisco
 */
public class EmailDescriptionController {

    private SendEmail sendEmail;
    private String email, subject, text;

    /**
     * Default constructor. Initialise variables and calls SendEmail() method
     * @param email that will be set
     * @param subject of the email
     * @param text of the email
     */
    public EmailDescriptionController(String email, String subject, String text) {
        this.email = email;
        this.subject = subject;
        this.text = text;
        sendEmail = new SendEmail();
    }

    /**
     * Method that sends an email
     * @return true/false if email was successfully sent or not
     */
    public boolean sendEmail() {
        return sendEmail.sendLockDescription(email, subject, text);
    }
}
