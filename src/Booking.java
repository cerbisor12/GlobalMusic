import java.sql.SQLException;

public class Booking {

    private String bookingNo = "";
    private String dateOfBooking;
    private int noOfSeats;
    private int customerID;
    private int eventID;
    private int paid;
    private float price;
    private String status;

    public Booking(String bookingNo, float price, int customerID, int eventID, String dateOfBooking, String status, int paid, int noOfSeats) {

        this.bookingNo = bookingNo;
        this.price = price;
        this.customerID = customerID;
        this.eventID = eventID;
        this.dateOfBooking = dateOfBooking;
        this.status = status;
        this.paid = paid;
        this.noOfSeats = noOfSeats;

        String query = "INSERT INTO tbl_booking VALUES('" + this.bookingNo + "','" + this.dateOfBooking + "'," +
                this.noOfSeats + "," +this.customerID+","+ this.eventID + "," + this.paid + ",'"
                + this.status +"'," + this.price + ");";
        try {
            Connect.updateData(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }

    }

}
