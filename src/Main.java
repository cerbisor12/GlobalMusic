import java.awt.*;

public class Main {

    final static String ARTIST_IMAGE_DIR = "Images/Artist_Images/";
    final static String EVENT_IMAGE_DIR = "Images/Event_Images/";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView window = new LoginView();
                    //window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
