import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * This class handles the canceled events, by sending email messages 
 * to all the users that have bought tickets. 
 */

class SendMail {

    final static String CANCELLATION_SUB = "One of your bookings have been cancelled :(";
    final static String CANCELLATION_MSG = "It seems like you had made some bookings for an event that has unfortunately" +
            " been cancelled. Please use the app to review them in detail." +
            " We are sorry about that, a full refund will be issued as soon as possible! \n\n" +
            "We apologize for any inconvenience, and we hope to see you again soon! \n\n" +
            "Best regards,\nThe Global Music Admin Team";

    private String title;
    private String lastName;
    /**
     * This constructs the details of the user that will receive the canceling email.
     * @param title the title of the user
     * @param lastName the last name of the user
     */
    public SendMail(String title, String lastName){
        this.title = title;
        this.lastName = lastName;
    }
    
    
    
    /**
     * This method send's the canceling of the event email.
     * @param userMail the receiving user email
     */
    public void sendCancellationMail( String userMail){
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        String from = "GlobalMusicAdm1n@gmail.com";
        String password = "b1c1a1a4c1b3c2!";
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(userMail));
            message.setSubject(CANCELLATION_SUB);
            message.setText("Dear "+ title+ " "+ lastName+",\n"+CANCELLATION_MSG);
            //sendCancellationMail message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }


    public static void main(String[] args) {
        //from,password,to,subject,message
        new SendMail("Mrs","Krezia").sendCancellationMail("a");
        //change from, password and to
    }
}
