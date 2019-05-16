import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.*;

/**
 * JPanel that includes a scrolled table for viewing bookings
 *
 *
 */
public class BookingsHistoryView extends JPanel {

    BookingsTableController controller;
    JTable table;
    /**
	 * Create the window
	 */
	public BookingsHistoryView() {

        this.setBounds(250,50,1000,550);
        this.setOpaque(false);
        this.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(210, 136, 790, 284);
        this.add(scrollPane);

        table = new JTable();
        //Set the data and edit the table
        controller =new BookingsTableController(table);
        table.setBackground(SystemColor.inactiveCaption);
        scrollPane.setViewportView(table);

        JLabel bookingsLabel = new JLabel("Booking History");
        bookingsLabel.setBounds(210, 68, 190, 38);
        bookingsLabel.setForeground(SystemColor.inactiveCaption);
        bookingsLabel.setFont(new Font("Open Sans", Font.PLAIN, 25));
        this.add(bookingsLabel);

          
	}

    /**
     * Used for updating the data after change(e.g new booking added)
     */
	public void refreshTableData(){
	    new BookingsTableController(table);
    }

    /**
     * Used for updating the status(pending to confirmed..) of the bookings
     */
    public void updateStatus(){controller.updateStatus();}
}