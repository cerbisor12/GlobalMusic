import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
        table.setBackground(SystemColor.inactiveCaption);
        new BookingHistoryController(this);
        scrollPane.setViewportView(table);

        JLabel bookingsLabel = new JLabel("Booking History");
        bookingsLabel.setBounds(210, 68, 190, 38);
        bookingsLabel.setForeground(SystemColor.inactiveCaption);
        bookingsLabel.setFont(new Font("Open Sans", Font.PLAIN, 25));
        this.add(bookingsLabel);

          
	}
}