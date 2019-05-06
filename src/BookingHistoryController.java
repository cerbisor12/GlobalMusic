import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookingHistoryController
{
    private String username = User.username;
    private JTable table;
    private BookingsHistoryView panel;

    public BookingHistoryController(BookingsHistoryView panel){
        this.panel = panel;
        this.table = panel.table;
        List<List<Object>> data = getBookings();
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
        getNewRenderedTable(table);

    }

    private List<List<Object>> getBookings(){
        String query = "SELECT B.BookingNo, B.DateOfBooking Date, E.Name Event, B.NoOfSeats 'Tickets'," +
                " B.TotalPrice Total, B.Paid, B.Status FROM tbl_booking B, tbl_event E " +
                "WHERE CustomerID= '" + User.getData(username,"UserID") + "' " +
                "AND E.EventID=B.EventID;";
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

                String paid = "";
                if(rs.getInt("Paid")==1){ paid = "\u2611"; }
                else{ paid = "\u2612"; }

                float total = rs.getFloat("Total");
                String status = rs.getString("Status");

                List<Object> booking = Arrays.asList(new Object[]{bookingNo,date,event,tickets,total,paid,status});
                bookingData.add(booking);
            }
        }catch(SQLException f){f.printStackTrace();}
        catch(ClassNotFoundException g){
            System.out.println(g.getMessage());}

        return bookingData;
    }

    public static void setCellsAlignment(JTable table, int alignment)
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    private void getNewRenderedTable(JTable table) {
            table.setDefaultRenderer(Object.class,new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);
                String status = (String)table.getModel().getValueAt(row, 6);
                //System.out.println("Status: "+status);

                renderer.setHorizontalAlignment(SwingConstants.CENTER);

                renderer.setText(value.toString());
                if ("pending".equalsIgnoreCase(status)) {
                    //System.out.println("HELLOOOO");
                    setBackground(Color.PINK);
                } else {
                    //System.out.println("BYEEEEE");
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                return this;
            }
        });
    }
}

