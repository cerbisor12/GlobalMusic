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
 * Class for populating the BookingHistoryView table and making the panel.
 *
 */
public class BookingsTableController {


    private String username = User.username;


    
    public BookingsTableController(JTable table){

        List<List<Object>> data = getBookings(username);
        if (data.size()>1){
            Object[] columnNames = data.get(0).toArray();
            data.remove(0);
            Object[][] bookingData = new Object[data.size()][data.get(0).size()];
            for (int i = 0; i<data.size(); i++){
                bookingData[i] = data.get(i).toArray();
            }

            table.setModel(new DefaultTableModel(bookingData, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            });
            table.getColumnModel().getColumn(0).setPreferredWidth(170);
            table.setFont(new Font("Open Sans",Font.PLAIN,16));

            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(),35));
            table.getTableHeader().setFont(new Font("Open Sans",Font.BOLD,18));
            table.setRowHeight(30);
            //setCellsAlignment(table,SwingConstants.CENTER);
            table.setDefaultRenderer(Object.class,createRenderer());}

    }

    private List<List<Object>> getBookings(String username){
        String userID;
        if(username.equals("admin")){
            userID = "'%'";
        }else{userID = String.valueOf(new User().getUserId(username));}
        String query = "SELECT B.BookingNo,B.DateOFBooking 'Date' , IFNULL(E.Name,'-') 'Event', B.NoOfSeats 'Tickets'," +
                " B.TotalPrice 'Total (£)',B.Paid, B.Status FROM tbl_booking B " +
                "LEFT JOIN tbl_event E ON E.EventID = B.EventID WHERE CustomerID LIKE "+ userID +";";
        List<List<Object>> bookingData= new ArrayList<>();

        try{
            ResultSet rs = Connect.selectStm(query);
            ResultSetMetaData meta = rs.getMetaData();


            int columns = meta.getColumnCount();
            List<Object> columnNames = new ArrayList<>();
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
        }catch(SQLException f){f.printStackTrace();}
        catch(ClassNotFoundException g){
            System.out.println(g.getMessage());}

        return bookingData;
    }


    private TableCellRenderer createRenderer(){


        return new DefaultTableCellRenderer(){
         @Override
         public Component getTableCellRendererComponent(JTable table,
                                                        Object value, boolean isSelected, boolean hasFocus, int row, int col) {
             DefaultTableCellRenderer renderer1 = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);
             String status = (String)table.getModel().getValueAt(row, 6);
             //System.out.println("Status: "+status);

             renderer1.setHorizontalAlignment(SwingConstants.CENTER);

             renderer1.setText(value.toString());
             if ("cancelled".equalsIgnoreCase(status)) {
                 setBackground(Color.PINK);
             } else if("confirmed".equalsIgnoreCase(status)) {
                 setBackground(new Color(164, 247, 37));
             } else {setBackground(table.getBackground());}
             return this;
         }
     };
    }

    public static void updateStatus(JTable table){
        String query = "UPDATE tbl_booking SET Status = 'confirmed' WHERE Status = 'pending' AND paid = 1;";
        try{
            Connect.updateData(query);
        }catch(SQLException | ClassNotFoundException e){e.printStackTrace();}
        new BookingsTableController(table);
    }


}

