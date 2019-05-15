import javax.swing.*;

import com.github.lgooddatepicker.components.DatePicker;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")

/**
 * This class displays the events in a result panel.
 * @author Rhadoo
 *
 */
public class ResultPanel extends JPanel{
    /**
     * This constructs the result panel.
     * @wbp.parser.constructor
     */
    public ResultPanel(boolean showFutureEvents){
    	//////////////////////////////////////
    	//for UPCOMING RESULTS
    	////////////////////////////////////////

        this.setPreferredSize(new Dimension(200,500));
        this.setBackground(Color.black);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        String query ="SELECT E.EventID, E.Name, E.Price, B.Name ArtistName, E.DateOfEvent, E.Image, V.Name Venue, V.Address" +
                " FROM tbl_venue V, tbl_event E, tbl_event_band EB, tbl_band B " +
                "WHERE E.VenueID = V.VenueID AND E.EventID = EB.EventID AND B.BandID = EB.BandID ";
        if(showFutureEvents){
        query +="AND E.DateOfEvent>=NOW() ORDER BY E.DateOfEvent";}
        else{
            query += "ORDER BY E.DateOfEvent";
        }

        createPanels(query);

    }
    
    /**
     * This constructs the result panel according to the specified search criteria 
     * entered by the user
     * @param searchCriteria input search criteria
     */
    public ResultPanel(String searchCriteria) {
    	this.setBackground(Color.black);
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String query ="SELECT E.EventID, E.Name, E.Price, B.Name ArtistName, E.DateOfEvent, E.Image, V.Name Venue, V.Address" +
                " FROM tbl_venue V, tbl_event E, tbl_event_band EB, tbl_band B " +
                "WHERE E.VenueID = V.VenueID AND E.EventID = EB.EventID AND B.BandID = EB.BandID " +
                "AND (E.Name LIKE '%"+searchCriteria+"%' OR B.Name LIKE '%"+searchCriteria+"%')" +
                "AND E.DateOfEvent>NOW()";
    	
    	createPanels(query);
    }
    
    /**
     * This constructs the search panel according to the date the user has input.
     * @param datePicker selected date by the user
     */
    public ResultPanel(DatePicker datePicker) {
    	this.setBackground(Color.black);
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        LocalDate dateFromDatePicker = datePicker.getDate();

        String query ="SELECT E.EventID, E.Name, E.Price, B.Name ArtistName, E.DateOfEvent, E.Image, V.Name Venue, V.Address" +
                " FROM tbl_venue V, tbl_event E, tbl_event_band EB, tbl_band B " +
                "WHERE E.VenueID = V.VenueID AND E.EventID = EB.EventID AND B.BandID = EB.BandID " +
                "AND DateOfEvent = "+ "\"" + dateFromDatePicker.toString() + "\"" + " ORDER BY DateofEvent";
    	
    	createPanels(query);
    }
    
    /**
     * This method returns all the events from the database.
     * @param query the mysql query
     * @return resulted events
     */
    private List<List<String>> getResults(String query){
        ResultSet rs;
        List<List<String>> results = new ArrayList<>();


        try {
            rs = Connect.selectStm(query);
            while(rs.next()){
                int ID = rs.getInt("EventID");
                String name = rs.getString("Name");
                String date = rs.getString("DateOfEvent");
                String image = rs.getString("Image");
                String venue = rs.getString("Venue");
                String venueAddress = rs.getString("Address");
                String artist = rs.getString("ArtistName");
                int price =(int) rs.getFloat("Price");
                boolean has = false;
                for (List<String> list : results){
                    if (list.get(1).equals(name)){
                        has =true;
                    }
                }
                if (has){
                    //noinspection OptionalGetWithoutIsPresent
                    int index = results.indexOf(results.stream().filter(e -> e.contains(name)).findAny().get());
                        results.get(index).add(artist);
                } else{
                    results.add(new ArrayList<>(Arrays.asList(String.valueOf(ID),name,date,image,venue,venueAddress,
                            String.valueOf(price),artist)));}

            }

        }
        catch (NullPointerException f){
            System.out.println("error"+f.getStackTrace()[0]);f.getStackTrace();
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        catch (ClassNotFoundException g){
            System.out.println(g.getMessage());
        }
        return results;
    }

    /**
     * This method puts each event into a panel.
     * @param query the mysql query
     */
    private void createPanels(String query){
        List<List<String>> results = getResults(query);
        int size= results.size();
        if (size==0){
            JLabel noResults = new JLabel("No results found :(");
            noResults.setPreferredSize(new Dimension(844, 374));
            noResults.setHorizontalTextPosition(JLabel.RIGHT);
            noResults.setVerticalTextPosition(JLabel.CENTER);
            noResults.setFont(new Font("Open Sans", Font.BOLD, 40));
            this.setBackground(SystemColor.activeCaption);
            this.add(noResults);
        }
        this.setPreferredSize(new Dimension(200,140*size));



        //Loop to create one panel for each search result
        for (int i=0;i<size;i++){
            List<String> eventDetails = results.get(i);

            JPanel panel = new JPanel();


            panel.setPreferredSize(new Dimension(200, 50));
            panel.setBounds(71, 70, 730, 139);
            panel.setLayout(null);

            JLabel imageLabel = new JLabel("image");
            imageLabel.setBounds(12, 13, 135, 109);
            ImageIcon img = new ImageIcon((Main.EVENT_IMAGE_DIR+eventDetails.get(3)));
            Image image = img.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(image));
            panel.add(imageLabel);

            JLabel nameLabel = new JLabel(eventDetails.get(1));
            nameLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
            nameLabel.setBounds(174, 13, 300, 23);
            panel.add(nameLabel);


            StringBuilder artists = new StringBuilder("Performing: ");
            int artistAmount = eventDetails.size();

            for (int b = 7; b<artistAmount;b++){
                artists.append(eventDetails.get(b)).append(", ");
            }
            JLabel bandsLabel = new JLabel(artists.toString());
            bandsLabel.setFont(new Font("Open Sans", Font.PLAIN, 12));
            bandsLabel.setBounds(185, 75, 450, 41);
            bandsLabel.setToolTipText("Click \"Book Tickets\" for more details...");
            panel.add(bandsLabel);


            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat finalformat = new SimpleDateFormat("dd/MM/yyyy");
            String newdate = "";
            try{
                Date date = format.parse(eventDetails.get(2));
                newdate = finalformat.format(date);}
            catch (ParseException e){
                System.out.println("error");
            }
            JLabel dateLabel = new JLabel("Starting: " + newdate);
            dateLabel.setFont(new Font("Open Sans", Font.PLAIN, 12));
            dateLabel.setBounds(170, 49, 120, 16);
            panel.add(dateLabel);

            JButton bookButton = new JButton("Book Tickets");
            bookButton.setForeground(SystemColor.inactiveCaption);
            bookButton.setBackground(new Color(0, 0, 128));
            bookButton.setBounds(650, 34, 135, 43);
            bookButton.setVisible(true);
            bookButton.addActionListener(new NewBookingController(Integer.parseInt(eventDetails.get(0))));
            panel.add(bookButton);

            JLabel priceTxtLabel = new JLabel("Event Price");
            priceTxtLabel.setBounds(680,40,135,43);
            priceTxtLabel.setVisible(false);
            panel.add(priceTxtLabel);

            JLabel priceLabel = new JLabel("\u00A3 " + results.get(i).get(6));
            priceLabel.setFont(new Font("Open Sans", Font.PLAIN, 18));
            priceLabel.setBounds(700, 97, 56, 16);
            panel.add(priceLabel);

            JTextArea venueLabel = new JTextArea(results.get(i).get(4) + "\nAddress: " + results.get(i).get(4));
            venueLabel.setFont(new Font("Open Sans", Font.PLAIN, 12));
            venueLabel.setBounds(350, 40, 229, 30);
            venueLabel.setEditable(false);
            panel.add(venueLabel);

            if (i%2==0){
                venueLabel.setBackground(SystemColor.activeCaption);
                panel.setBackground(SystemColor.activeCaption);}
            else{
                venueLabel.setBackground(SystemColor.inactiveCaption);
                panel.setBackground(SystemColor.inactiveCaption);}

            add(panel);

        }
    }

    public void hideBookButton(){
        for(Component panel : this.getComponents()) {
            panel.getComponentAt(650, 34).setVisible(false);
            panel.getComponentAt(680,80).setVisible(true);
        }
    }

}
