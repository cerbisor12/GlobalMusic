import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.*;

/**
 * Class for viewing existing bookings.
 * @author x64
 *
 */
public class BookingsHistoryView extends JPanel {


	JTable table;
    /*
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
        //new BookingsTableController(table);
        table.setBackground(SystemColor.inactiveCaption);
        scrollPane.setViewportView(table);

        JLabel bookingsLabel = new JLabel("Booking History");
        bookingsLabel.setBounds(210, 68, 190, 38);
        bookingsLabel.setForeground(SystemColor.inactiveCaption);
        bookingsLabel.setFont(new Font("Open Sans", Font.PLAIN, 25));
        this.add(bookingsLabel);

          
	}
}