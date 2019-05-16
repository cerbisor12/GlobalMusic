import java.awt.*;

/**
 * This application is developed for Global Music which is a Booking Agency.
 * It is allowing to login in with four different types of users (admin, customer, 
 * corporate organisation and event organiser) each of these users having their own
 * personalised view of the application.
 * Class for running the main frame of the application.
 * @author Dimitra Krezia, Radu Popescu, Daniel Serbanescu.
 *
 */
public class Main {

    final static String IMAGE_DIR = "Images/";
    final static String ARTIST_IMAGE_DIR = "Images/Artist_Images/";
    final static String EVENT_IMAGE_DIR = "Images/Event_Images/";
    final static String CARD_ICON_DIR = "Images/Card_Icons/";
    final static String INVOICES_DIR = "Images/Invoices/";


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new LoginView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
