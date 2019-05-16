import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for populating a table with booking data retreived from the database and editing the appearance
 */
public class BookingsTableController {


    private String username = User.username;
    private JTable table;


    /**
     * Class' constructor, populates and edits the table
     * @param table
     */
    public BookingsTableController(JTable table){

        this.table = table;
        List<List<Object>> data = getBookings(username); //get all bookings of specified customer

        //populate the table if there are any bookings
        if (data.size()>1){
            Object[] columnNames = data.get(0).toArray(); //first entry are the field names from the DB
            data.remove(0);

            //Convert List<List<Object>> to Object[][]
            Object[][] bookingData = new Object[data.size()][data.get(0).size()];
            for (int i = 0; i<data.size(); i++){

                bookingData[i] = data.get(i).toArray();
            }

            //Populate the table with data and make it non-editable
            table.setModel(new DefaultTableModel(bookingData, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            });

            //Set appearance of the table
            table.getColumnModel().getColumn(0).setPreferredWidth(170);
            table.setFont(new Font("Open Sans",Font.PLAIN,16));
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(),35));
            table.getTableHeader().setFont(new Font("Open Sans",Font.BOLD,18));
            table.setRowHeight(30);

            //Create renderer
            table.setDefaultRenderer(Object.class,createRenderer());}

    }


    /**
     * Create multidimensional array of Strings for all bookings of specified customer with the first
     * object being a List<Object> of the column names
     * @param username the specified customer's username
     * @return <List<List<Object> customer's bookings
     */
    private List<List<Object>> getBookings(String username){
        String userID;

        //get all bookings from DB if the user is the admin
        if(username.equals("admin")){
            userID = "'%'"; //Special char for mySQL (anything)
        }
        //else get only specified customer's bookings
        else{userID = String.valueOf(new User().getUserId(username));}

        String query = "SELECT B.BookingNo,B.DateOFBooking 'Date' , IFNULL(E.Name,'-') 'Event', B.NoOfSeats 'Tickets'," +
                " B.TotalPrice 'Total (£)',B.Paid, B.Status FROM tbl_booking B " +
                "LEFT JOIN tbl_event E ON E.EventID = B.EventID WHERE CustomerID LIKE "+ userID +";";
        List<List<Object>> bookingData= new ArrayList<>();

        try{
            ResultSet rs = Connect.selectStm(query);
            ResultSetMetaData meta = rs.getMetaData();


            int columns = meta.getColumnCount();
            List<Object> columnNames = new ArrayList<>();
            //add column names
            for (int i= 1; i<=columns; i++){
                columnNames.add(meta.getColumnLabel(i));
            }
            bookingData.add(columnNames);

            while(rs.next()){
                String bookingNo = rs.getString("BookingNo");
                String date = rs.getString("Date");
                String event = rs.getString("Event");
                int tickets = rs.getInt("Tickets");

                String paid;
                if(rs.getInt("Paid")==1){ paid = "\u2611"; }
                else{ paid = "\u2612"; }

                float total = rs.getFloat("Total (£)");
                String status = rs.getString("Status");

                List<Object> booking = Arrays.asList(new Object[]{bookingNo,date,event,tickets,total,paid,status});
                bookingData.add(booking);
            }
        }catch(SQLException e ){e.printStackTrace();}

        return bookingData;
    }

    /**
     * Create a renderer for editing the table
     * @return TableCellRenderer
     */
    private TableCellRenderer createRenderer(){


        return new DefaultTableCellRenderer(){
         @Override
         public Component getTableCellRendererComponent(JTable table,
                                                        Object value, boolean isSelected, boolean hasFocus, int row, int col) {
             DefaultTableCellRenderer renderer1 = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);
             String status = (String)table.getModel().getValueAt(row, 6);

             renderer1.setHorizontalAlignment(SwingConstants.CENTER);

             renderer1.setText(value.toString());
             //change row color depending on the booking's Status
             if ("cancelled".equalsIgnoreCase(status)) {
                 setBackground(Color.PINK);
             } else if("confirmed".equalsIgnoreCase(status)) {
                 setBackground(new Color(164, 247, 37));
             } else {setBackground(table.getBackground());}
             return this;
         }
     };
    }

    /**
     * Change the status of the pending bookings to confirmed if they are paid
     * and refresh the table
     */
    public void updateStatus(){
        String query = "UPDATE tbl_booking SET Status = 'confirmed' WHERE Status = 'pending' AND paid = 1;";
        try{
            Connect.updateData(query);
        }catch(SQLException e){e.printStackTrace();}
        new BookingsTableController(table);
    }


}

