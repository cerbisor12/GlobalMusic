import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.DataSource;
import javax.swing.*;

/**
 * This class handles the canceled events, by sending email messages 
 * to all the users that have bought tickets. 
 */

class SendMail {



    private final static String FROM = "GlobalMusicAdm1n@gmail.com";
    private final static String PASSWORD = "b1c1a1a4c1b3c2!";

    private String title;
    private String lastName;
    private String email;
    /**
     * This constructs the details of the user that will receive the canceling email.
     * @param title the title of the user
     * @param lastName the last name of the user
     * @param email the email of the user
     */
    public SendMail(String title, String lastName,String email){
        this.title = title;
        this.lastName = lastName;
        this.email = email;
    }
    
    
    
    /**
     * This method send's the canceling of the event email.
     *
     */
    public void sendCancellationMail(){
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
        String cancellationSub = "One of your bookings have been cancelled :(";
        String cancellationMsg = "It seems like you had made some bookings for an event that has unfortunately" +
                " been cancelled. Please use the app to review them in detail." +
                " We are sorry about that, a full refund will be issued as soon as possible! \n\n" +
                "We apologize for any inconvenience, and we hope to see you again soon! \n\n" +
                "Best regards,\nThe Global Music Admin Team";
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
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            message.setSubject(cancellationSub);
            message.setText("Dear "+ title+ " "+ lastName+",\n"+cancellationMsg);
            //sendCancellationMail message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }

    public void sendMonthlyInvoice(String invName){
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM, PASSWORD);
                    }
                });
        //compose message
        String monthlyInvSub = "Your monthly invoice is ready";
        String monthlyInvMsg = "Dear "+ lastName+ ", \n"+
        "Please find attached your monthly Invoice from Global Music regarding the bookings you made "+
                "during last month on behalf of your company. \n\n" +
                "Payment details are included on the invoice, kindly proceed with payment as soon as possible." +
                "\n\nBest Regards, \nThe Global Music Admin Team";
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            message.setSubject(monthlyInvSub);

            BodyPart msgBodyPart = new MimeBodyPart();
            msgBodyPart.setText(monthlyInvMsg);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(msgBodyPart);

            msgBodyPart = new MimeBodyPart();
            String filename = Main.INVOICES_DIR + invName;
            DataSource source = new FileDataSource(filename);
            msgBodyPart.setDataHandler(new DataHandler(source));
            msgBodyPart.setFileName(invName);
            multipart.addBodyPart(msgBodyPart);

            message.setContent(multipart);
            //sendCancellationMail message
            Transport.send(message);

        } catch (MessagingException e) {throw new RuntimeException(e);}

    }

    public static void main(String[] args) {
        //FROM,PASSWORD,to,subject,message
        new SendMail("Mrs","Krezia","aran0ia90@gmail.com").sendMonthlyInvoice("Inv_1.jpg");
        //change FROM, PASSWORD and to
    }
}
