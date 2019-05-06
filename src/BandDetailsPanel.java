import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BandDetailsPanel extends JPanel {

    int eventID;

    public BandDetailsPanel(int eventID){
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

        this.eventID = eventID;
        createPanel();
    }

    public List<String[]> getBandDetails(){
        String query = "SELECT B.Name, B.Image, B.Genre, B.Link FROM tbl_band B, tbl_event_band EB WHERE EventID= "+eventID+
                " AND B.BandID = EB.BandID";
        List<String[]> bandDetails = new ArrayList<>();
        try{
            ResultSet rs = Connect.selectStm(query);

            while(rs.next()){
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                String genre = rs.getString("Genre");
                String link = rs.getString("Link");
                bandDetails.add(new String[]{name,image,genre,link});
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());}

        return bandDetails;
    }

    public void createPanel(){
        List<String[]> bandDetails = getBandDetails();
        int size = bandDetails.size();
        this.setPreferredSize(new Dimension(173*size,230));
        for (int i = 0; i<size; i++){
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(173,230));
            if(i%2==0){panel.setBackground(SystemColor.inactiveCaption);}
            else{panel.setBackground(SystemColor.activeCaption);}
            panel.setLayout(null);

            JLabel imageLabel = new JLabel("image");
            imageLabel.setBounds(5, 0, 163, 140);
            ImageIcon img = new ImageIcon((HomePageView.class.getResource(Main.ARTIST_IMAGE_DIR+bandDetails.get(i)[1])));
            Image image = img.getImage().getScaledInstance(imageLabel.getWidth(),imageLabel.getHeight(),Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(image));
            panel.add(imageLabel);

            JLabel nameLabel = new JLabel(bandDetails.get(i)[0]);
            nameLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
            nameLabel.setBounds(0, 151, 173, 23);
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(nameLabel);

            JLabel genreLabel = new JLabel(bandDetails.get(i)[2]);
            genreLabel.setBounds(0, 182, 173, 14);
            genreLabel.setFont(new Font("Open Sans", Font.BOLD, 16));
            genreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(genreLabel);

            JButton websiteBtn = new JButton(bandDetails.get(i)[3]);
            websiteBtn.setBounds(0, 201, 173, 23);
            websiteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            websiteBtn.setOpaque(false);
            websiteBtn.setForeground(SystemColor.textHighlight);
            websiteBtn.setFont(new Font("Open Sans", Font.PLAIN, 14));
            websiteBtn.setContentAreaFilled(false);
            websiteBtn.setBorderPainted(false);
            websiteBtn.addActionListener(new ActionListener() {
                /**
                 * @author Brajesh Kumar
                 * Source: https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    String url = "https://"+websiteBtn.getText();

                    if(Desktop.isDesktopSupported()){
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            desktop.browse(new URI(url));
                        } catch (IOException | URISyntaxException f) {
                            // TODO Auto-generated catch block
                            f.printStackTrace();
                        }
                    }else{
                        Runtime runtime = Runtime.getRuntime();
                        try {
                            runtime.exec("xdg-open " + url);
                        } catch (IOException g) {
                            // TODO Auto-generated catch block
                            g.printStackTrace();
                        }
                    }
                }
            });

            panel.add(websiteBtn);


            this.add(panel);

        }
    }
}
