import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.*;

public class NewBookingView {

    int availableTickets;

    JLabel lblFestivalImage, lblName, lblDate, lblDuration, lblAvailableTickets, lblPrice, lblPriceStudent, lblCorpPriceValue;
    JTextArea txtVenue;

    JButton btnProceedToBooking;

    JLabel lblAmount, lblTotalTickets, lblNotEnoughTickets;

    JScrollPane bandPanel;

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NewBookingView window = new NewBookingView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public NewBookingView() {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Global Music");
        frame.setBounds(100, 100, 1280, 690);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);


        JButton btnExitButton = new JButton("X");
        btnExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        btnExitButton.setFont(new Font("Open Sans", Font.PLAIN, 25));
        btnExitButton.setForeground(SystemColor.inactiveCaption);
        btnExitButton.setBounds(1205, 13, 63, 53);
        btnExitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExitButton.setOpaque(false);
        btnExitButton.setBorderPainted(false);
        btnExitButton.setContentAreaFilled(false);
        frame.getContentPane().add(btnExitButton);


        JButton minimizeButton = new JButton("___");
        minimizeButton.setForeground(SystemColor.inactiveCaption);
        minimizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        });
        minimizeButton.setOpaque(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        minimizeButton.setBounds(1154, 20, 63, 38);
        frame.getContentPane().add(minimizeButton);

        lblFestivalImage = new JLabel("Festival Image");
        lblFestivalImage.setBounds(33, 74, 200, 237);
        frame.getContentPane().add(lblFestivalImage);

        JLabel lblFeaturingArtists = new JLabel("Featuring Artists");
        lblFeaturingArtists.setForeground(SystemColor.activeCaption);
        lblFeaturingArtists.setFont(new Font("Open Sans", Font.BOLD, 16));
        lblFeaturingArtists.setBounds(389, 29, 209, 29);
        frame.getContentPane().add(lblFeaturingArtists);

        bandPanel = new JScrollPane();
        bandPanel.setBackground(SystemColor.RED);
        bandPanel.setBounds(385, 74, 768, 248);
        frame.getContentPane().add(bandPanel);


        lblName = new JLabel("Name");
        lblName.setBounds(46, 372, 200, 20);
        lblName.setForeground(SystemColor.inactiveCaption);
        lblName.setFont(new Font("Open Sans", Font.BOLD, 20));
        frame.getContentPane().add(lblName);


        lblDate = new JLabel("Date");
        lblDate.setBounds(46, 414, 200, 14);
        lblDate.setForeground(SystemColor.inactiveCaption);
        lblDate.setFont(new Font("Open Sans", Font.BOLD, 14));
        frame.getContentPane().add(lblDate);


        lblDuration = new JLabel("Duration");
        lblDuration.setBounds(46, 454, 200, 14);
        lblDuration.setForeground(SystemColor.inactiveCaption);
        lblDuration.setFont(new Font("Open Sans", Font.BOLD, 14));
        frame.getContentPane().add(lblDuration);


        txtVenue = new JTextArea();
        txtVenue.setOpaque(false);
        txtVenue.setForeground(SystemColor.inactiveCaption);
        txtVenue.setFont(new Font("Open Sans", Font.BOLD, 14));
        txtVenue.setLineWrap(true);
        txtVenue.setText("Venue      Address      ");
        txtVenue.setBounds(46, 494, 200, 40);
        frame.getContentPane().add(txtVenue);


        lblAvailableTickets = new JLabel("Available Tickets");
        lblAvailableTickets.setBounds(46, 554, 250, 20);
        lblAvailableTickets.setForeground(SystemColor.inactiveCaption);
        lblAvailableTickets.setFont(new Font("Open Sans", Font.BOLD, 20));
        frame.getContentPane().add(lblAvailableTickets);


        JLabel lblFullPriceTickets = new JLabel("Full Price Tickets");
        lblFullPriceTickets.setBounds(385, 372, 125, 14);
        lblFullPriceTickets.setForeground(SystemColor.inactiveCaption);
        lblFullPriceTickets.setFont(new Font("Open Sans", Font.BOLD, 14));
        frame.getContentPane().add(lblFullPriceTickets);

        JLabel lblPoundSymbol = new JLabel("\u00A3");
        lblPoundSymbol.setBounds(632, 345, 20, 20);
        lblPoundSymbol.setForeground(SystemColor.inactiveCaption);
        lblPoundSymbol.setFont(new Font("Open Sans", Font.BOLD, 18));
        frame.getContentPane().add(lblPoundSymbol);

        lblPrice = new JLabel("Price");
        lblPrice.setBounds(624, 372, 46, 14);
        lblPrice.setForeground(SystemColor.inactiveCaption);
        lblPrice.setFont(new Font("Open Sans", Font.BOLD, 14));
        frame.getContentPane().add(lblPrice);


        JLabel lblStudentDiscountPrice = new JLabel("Student Discount Price");
        lblStudentDiscountPrice.setBounds(389, 430, 171, 14);
        lblStudentDiscountPrice.setForeground(SystemColor.inactiveCaption);
        lblStudentDiscountPrice.setFont(new Font("Open Sans", Font.BOLD, 14));
        frame.getContentPane().add(lblStudentDiscountPrice);


        lblPriceStudent = new JLabel("Price");
        lblPriceStudent.setBounds(624, 430, 46, 14);
        lblPriceStudent.setForeground(SystemColor.inactiveCaption);
        lblPriceStudent.setFont(new Font("Open Sans", Font.BOLD, 14));
        frame.getContentPane().add(lblPriceStudent);


        JLabel lblStudentIdNeeded = new JLabel("Student ID Needed At Location");
        lblStudentIdNeeded.setForeground(Color.RED);
        lblStudentIdNeeded.setBounds(389, 452, 179, 14);
        frame.getContentPane().add(lblStudentIdNeeded);

        JLabel lblNoOfTickets = new JLabel("No of Tickets");
        lblNoOfTickets.setBounds(763, 345, 100, 20);
        lblNoOfTickets.setForeground(SystemColor.inactiveCaption);
        lblNoOfTickets.setFont(new Font("Open Sans", Font.BOLD, 16));
        frame.getContentPane().add(lblNoOfTickets);


        Integer[] intList = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        JComboBox<Integer> comboBoxFullPrice = new JComboBox<Integer>(intList);
        comboBoxFullPrice.setBackground(SystemColor.activeCaption);
        comboBoxFullPrice.setBounds(773, 371, 84, 20);
        frame.getContentPane().add(comboBoxFullPrice);

        JComboBox<Integer> comboBoxStudentPrice = new JComboBox<Integer>(intList);
        comboBoxStudentPrice.setBackground(SystemColor.activeCaption);
        comboBoxStudentPrice.setBounds(773, 427, 84, 20);
        frame.getContentPane().add(comboBoxStudentPrice);

        JComboBox<Integer> comboBoxCorporatePrice = new JComboBox<Integer>(intList);
        comboBoxCorporatePrice.setBackground(SystemColor.activeCaption);
        comboBoxCorporatePrice.setBounds(773, 495, 84, 20);
        //frame.getContentPane().add(comboBoxCorporatePrice);


        ActionListener updateTotal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fullPriceTickets = (int) comboBoxFullPrice.getSelectedItem();
                int studentTickets = (int) comboBoxStudentPrice.getSelectedItem();
                int corporateTickets = (int) comboBoxCorporatePrice.getSelectedItem();
                int totalTickets = fullPriceTickets + studentTickets + corporateTickets;

                float totalAmount = fullPriceTickets * Float.parseFloat(lblPrice.getText()) +
                        studentTickets * Float.parseFloat(lblPriceStudent.getText()) +
                        corporateTickets * Float.parseFloat(lblCorpPriceValue.getText());

                lblAmount.setText(String.valueOf(totalAmount));
                lblTotalTickets.setText(String.valueOf(totalTickets));

                if (totalTickets > availableTickets) {
                    btnProceedToBooking.setEnabled(false);
                    lblNotEnoughTickets.setVisible(true);
                } else if (totalTickets == 0) {
                    btnProceedToBooking.setEnabled(false);
                    lblNotEnoughTickets.setVisible(false);
                } else {
                    btnProceedToBooking.setEnabled(true);
                    lblNotEnoughTickets.setVisible(false);
                }
            }
        };

        comboBoxCorporatePrice.addActionListener(updateTotal);
        comboBoxFullPrice.addActionListener(updateTotal);
        comboBoxStudentPrice.addActionListener(updateTotal);

        JLabel lblCorporateTicketPrice = new JLabel("Corporate Ticket Price");
        lblCorporateTicketPrice.setBounds(389, 498, 171, 14);
        lblCorporateTicketPrice.setForeground(SystemColor.inactiveCaption);
        lblCorporateTicketPrice.setFont(new Font("Open Sans", Font.BOLD, 14));
        //frame.getContentPane().add(lblCorporateTicketPrice);


        lblCorpPriceValue = new JLabel("Price");
        lblCorpPriceValue.setBounds(624, 498, 46, 14);
        lblCorpPriceValue.setForeground(SystemColor.inactiveCaption);
        lblCorpPriceValue.setFont(new Font("Open Sans", Font.BOLD, 14));
        //frame.getContentPane().add(lblCorpPriceValue);

        if (User.getData(User.username, "Type").equalsIgnoreCase("organization")) {
            frame.getContentPane().add(lblCorpPriceValue);
            frame.getContentPane().add(lblCorporateTicketPrice);
            frame.getContentPane().add(comboBoxCorporatePrice);
        }


        JLabel lblTotals = new JLabel("Totals:");
        lblTotals.setBounds(549, 554, 75, 20);
        lblTotals.setForeground(SystemColor.inactiveCaption);
        lblTotals.setFont(new Font("Open Sans", Font.BOLD, 20));
        frame.getContentPane().add(lblTotals);

        lblAmount = new JLabel("0.0");
        lblAmount.setBounds(628, 554, 97, 20);
        lblAmount.setForeground(SystemColor.inactiveCaption);
        lblAmount.setFont(new Font("Open Sans", Font.BOLD, 20));
        frame.getContentPane().add(lblAmount);


        lblTotalTickets = new JLabel("0");
        lblTotalTickets.setBounds(773, 554, 84, 20);
        lblTotalTickets.setForeground(SystemColor.inactiveCaption);
        lblTotalTickets.setFont(new Font("Open Sans", Font.BOLD, 20));
        frame.getContentPane().add(lblTotalTickets);

        JLabel lblVAT = new JLabel("VAT(20%) is included");
        lblVAT.setBounds(389, 556, 200, 14);
        lblVAT.setForeground(SystemColor.inactiveCaption);
        lblVAT.setFont(new Font("Open Sans", Font.PLAIN, 14));
        frame.getContentPane().add(lblVAT);

        lblNotEnoughTickets = new JLabel("There aren't enough tickets available :(");
        lblNotEnoughTickets.setBounds(600, 574, 300, 14);
        lblNotEnoughTickets.setFont(new Font("Open Sans", Font.BOLD, 12));
        lblNotEnoughTickets.setForeground(Color.RED);
        lblNotEnoughTickets.setVisible(false);
        frame.getContentPane().add(lblNotEnoughTickets);


        btnProceedToBooking = new JButton("Proceed To Booking");
        btnProceedToBooking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnProceedToBooking.setBounds(715, 615, 250, 23);
        btnProceedToBooking.setForeground(SystemColor.inactiveCaption);
        btnProceedToBooking.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnProceedToBooking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnProceedToBooking.setOpaque(false);
        btnProceedToBooking.setBorderPainted(false);
        btnProceedToBooking.setContentAreaFilled(false);
        btnProceedToBooking.setEnabled(false);
        btnProceedToBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookingNo = User.username + String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
                float price = Float.parseFloat(lblAmount.getText());
                int customerID = User.getUserId(User.username);
                int eventID = Event.getEventId(lblName.getText());

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(new Date());

                String status;
                int paid;
                int noOfSeats = Integer.parseInt(lblTotalTickets.getText());

                String msg = "Total Tickets: " + lblTotalTickets.getText() +
                        "\nAmount to be paid: " + lblAmount.getText() + "\u00A3\n";

                String paymentMethod = User.getData(User.username, "PaymentMethod");
                if (paymentMethod.equalsIgnoreCase("On Booking")) {
                    msg += "Card No: " + User.getData(User.username, "CardNo") +
                            " Card CVV: " + User.getData(User.username, "CVVCode");
                    status = "pending";
                    paid = 1;
                } else {
                    msg += "This will be added to your monthly invoice";
                    status = "confirmed";
                    paid = 0;
                }


                int confirmBooking = JOptionPane.showConfirmDialog(null, msg, "Confirm Booking?", JOptionPane.OK_CANCEL_OPTION);
                if (confirmBooking == 0) {
                    new Booking(bookingNo, price, customerID, eventID, date, status, paid, noOfSeats);
                    JOptionPane.showMessageDialog(null, "Booking Complete!!");
                }
            }
        });
        frame.getContentPane().add(btnProceedToBooking);


        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        btnCancel.setOpaque(false);
        btnCancel.setForeground(SystemColor.inactiveCaption);
        btnCancel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancel.setContentAreaFilled(false);
        btnCancel.setBorderPainted(false);
        btnCancel.setBounds(489, 615, 100, 23);
        frame.getContentPane().add(btnCancel);


        JSeparator separatorCancel = new JSeparator();
        separatorCancel.setBounds(489, 638, 92, 3);
        separatorCancel.setBackground(SystemColor.inactiveCaption);
        separatorCancel.setForeground(SystemColor.inactiveCaption);
        separatorCancel.setOpaque(true);
        frame.getContentPane().add(separatorCancel);


        JSeparator separatorProceed = new JSeparator();
        separatorProceed.setBounds(733, 638, 207, 3);
        separatorProceed.setBackground(SystemColor.inactiveCaption);
        separatorProceed.setForeground(SystemColor.inactiveCaption);
        separatorProceed.setOpaque(true);
        frame.getContentPane().add(separatorProceed);


        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(LoginView.class.getResource("Images/Logo.jpg")));
        lblLogo.setBounds(186, 583, 200, 96);
        frame.getContentPane().add(lblLogo);


        JLabel lblImageLabel = new JLabel("Image");
        lblImageLabel.setFont(new Font("Open Sans", Font.PLAIN, 11));
        lblImageLabel.setForeground(Color.BLACK);
        lblImageLabel.setIcon(new ImageIcon(LoginView.class.getResource("Images/Silhouette-Rock-Concert-Wallpaper1.jpg")));
        lblImageLabel.setBounds(0, 0, 1297, 693);
        frame.getContentPane().add(lblImageLabel);

        frame.setVisible(true);


    }
//        public List<Object> getEventDetails(int eventID){
//	        List<Object> eventDetails = new ArrayList<>();
//	        String query = "SELECT * from tbl_event WHERE EventID="+eventID+";";
//	        try{
//                ResultSet rs = Connect.selectStm(query);
//                rs.next();
//
//                eventDetails.add(rs.getString("Name"));
//                float eventPrice = rs.getFloat("Price");
//                eventDetails.add(eventPrice);
//                String eventDate = rs.getString("DateOFEvent");
//                String eventImage = rs.getString("Image");
//                int eventDuration = rs.getInt("Duration");
//
//        }
//	}
}
