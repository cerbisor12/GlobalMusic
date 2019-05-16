import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class takes data from the event table according to the event ID and populates 
 * all the fields from the NewBookingView. 
 *
 */
class NewBookingController implements ActionListener {

    private int eventID;

    NewBookingController(int eventID){
        this.eventID = eventID;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NewBookingView newBooking = new NewBookingView();
        setEventDetails(newBooking);
        newBooking.bandPanel.setViewportView(new BandDetailsPanel(eventID));
    }
    
    /**
     * Populate the NewBookingView textfields with event details
     * @param newBooking NewBookingView
     */
    private void setEventDetails(NewBookingView newBooking){
        String query = "SELECT V.Name venueName,V.Address, V.Capacity, E.*, SUM(NoOfSeats) booked " +
                "FROM tbl_venue V,tbl_booking B,tbl_event E " +
                "WHERE E.EventID= "+ eventID +
                " AND E.VenueID = V.VenueID " +
                "AND B.EventID = E.EventID;";
        try{
            ResultSet rs = Connect.selectStm(query);
            rs.next();

            ImageIcon img = new ImageIcon((Main.EVENT_IMAGE_DIR+rs.getString("Image")));
            int width = newBooking.lblFestivalImage.getWidth();
            int height = newBooking.lblFestivalImage.getHeight();
            Image image = img.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
            newBooking.lblFestivalImage.setIcon(new ImageIcon(image));

            newBooking.lblName.setText(rs.getString("Name"));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat finalFormat = new SimpleDateFormat("dd/MM/yyyy");
            String newDate = "";
            try{
                Date date = format.parse(rs.getString("DateOfEvent"));
                newDate = finalFormat.format(date);}
            catch (ParseException e){
                System.out.println("error");
            }
            newBooking.lblDate.setText(newDate);

            newBooking.lblDuration.setText("Duration: "+ rs.getInt("Duration") + " days");

            String venue = rs.getString("venueName")+ "\n" + rs.getString("Address");
            newBooking.txtVenue.setText(venue);

            newBooking.availableTickets = rs.getInt("Capacity")-rs.getInt("booked");
            newBooking.lblAvailableTickets.setText("Available Tickets: " + newBooking.availableTickets);

            float price = rs.getFloat("Price");
            double studentPrice = price - 0.15*price;
            double corporatePrice = price - 0.25*price;
            newBooking.lblPrice.setText(String.valueOf(price));
            newBooking.lblPriceStudent.setText(String.valueOf(studentPrice));
            newBooking.lblCorpPriceValue.setText(String.valueOf(corporatePrice));

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
