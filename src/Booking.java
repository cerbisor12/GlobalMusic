import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for the creation of Booking objects and writing into database.
 *
 */
public class Booking {

    /**
     * Class' constructor with query for inserting data into database included.
     * @param bookingNo booking ID
     * @param price booking total price      
     * @param customerID customer's ID
     * @param eventID booking's event id
     * @param dateOfBooking booking's date
     * @param status cancelled, confirmed, pending etc
     * @param paid if paid or not.
     * @param noOfSeats number of seats bought
     */
    public Booking(String bookingNo, float price, int customerID, int eventID, String dateOfBooking, String status, int paid, int noOfSeats) {

        String query = "INSERT INTO tbl_booking VALUES('" + bookingNo + "','" + dateOfBooking + "'," +
                noOfSeats + "," + customerID +","+ eventID + "," + paid + ",'"
                + status +"'," + price + ");";
        try {
            Connect.updateData(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Booking(){}

    /**
     * method for updating event's status.
     * @param eventID event's ID
     * @param status new status;
     */
    public void updateStatus(int eventID, String status){
        String query = "UPDATE tbl_booking SET Status= '"+ status + "',EventID= null WHERE EventID = "+ eventID +";";

        try {
            Connect.updateData(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get customer's detail based on certain event.
     * @param eventID the event's id
     * @return ArrayList of strings filled with email, title and name of the customer.
     */
    public List<List<String>> getCustomerInfo(int eventID){
        String query = "SELECT U.LName, U.Title, U.Email FROM tbl_user U, tbl_booking B WHERE B.EventID = " +eventID +
                " AND B.CustomerID = U.UserID;";
        List<List<String>> customerInfo = new ArrayList<>();

        try{
            ResultSet rs = Connect.selectStm(query);

            while(rs.next()){
                String email = rs.getString("Email");
                String title = rs.getString("Title");
                String lName = rs.getString("LName");
                List<String> cust = Arrays.asList(email,title,lName);
                customerInfo.add(cust);
            }
        }catch(SQLException | ClassNotFoundException e){e.printStackTrace();}

        return customerInfo;

    }
     public List<List<String>> getBookingsperMonth(String username){
        List<List<String>> bookings = new ArrayList<>();
        int month = LocalDate.now().getMonthValue();
        String query = "SELECT B.NoOfSeats, B.BookingNo, E.Name, B.TotalPrice FROM tbl_booking B, tbl_event E, tbl_user U" +
                " WHERE U.username = '" +username + "' AND U.UserID = B.CustomerID AND E.EventID = B.EventID " +
                " AND B.Paid = 0 AND MONTH(B.DateOFBooking) = "+month;
        try{
            ResultSet rs = Connect.selectStm(query);
            while(rs.next()){
                String tickets = String.valueOf(rs.getInt(1));
                String bookingNo = rs.getString(2);
                String event = rs.getString(3);
                String total = String.valueOf(rs.getFloat(4));

                List<String> booking = Arrays.asList(tickets,bookingNo,event,total);
                bookings.add(booking);
            }
        }catch(SQLException | ClassNotFoundException e){e.printStackTrace();}

        return bookings;
     }


}
