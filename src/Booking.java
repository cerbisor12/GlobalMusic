import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for the creation of Booking objects and writing into database.
 * @author x64
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

}
