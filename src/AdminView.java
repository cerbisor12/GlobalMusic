import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;

/**
 * Class for creating the frame for the admin's window.
 *
 */
public class AdminView {

	private JFrame frame;
    private BookingsHistoryView tableConfirmBooking;
    private JPanel genInvPanel;


    /**
     * Create the application.
     */
    public AdminView() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setForeground(new Color(128, 128, 128));
        frame.setResizable(false);
        frame.setTitle("Global Music");
        frame.setBounds(100, 100, 1280, 690);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        User user = new User();

        JButton btnExitButton = new JButton("X");
        /*
         * Listener for exiting the application after user's confirmation.
         */
        btnExitButton.addActionListener(e -> {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit?", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
              System.exit(0);
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
        /*
         * Listener for minimizing the window.
         */
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


        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setBounds(22, 410, 121, 53);
        btnLogOut.setForeground(SystemColor.inactiveCaption);
        btnLogOut.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogOut.setOpaque(false);
        btnLogOut.setBorderPainted(false);
        btnLogOut.setContentAreaFilled(false);
        /*
         * Listener for logging out of the system, opens the LoginView.
         */
        btnLogOut.addActionListener(arg0 -> {
            new LoginView();
            frame.setVisible(false);
        });
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(btnLogOut);

        JLabel lblChooseUsername = new JLabel("Choose Username");
        lblChooseUsername.setBounds(48, 56, 130, 14);
        lblChooseUsername.setForeground(SystemColor.inactiveCaption);
        lblChooseUsername.setFont(new Font("Open Sans", Font.BOLD, 14));
        frame.getContentPane().add(lblChooseUsername);
        
        //Create comboBox with all customer and organization usernames for the admin to choose and edit
        JComboBox comboBoxUsername = new JComboBox(user.getAllUsernames("customer' OR Type = 'organization"));
        comboBoxUsername.addActionListener(e -> {
            User.username = comboBoxUsername.getSelectedItem().toString();
            new PopUp();//open the MyaccountView corresponding to chosen username
        });
        comboBoxUsername.setBackground(SystemColor.activeCaption);
        comboBoxUsername.setBounds(204, 55, 182, 25);
        frame.getContentPane().add(comboBoxUsername);

        JScrollPane scrollPaneViewEvent = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneViewEvent.setBounds(400, 173, 840, 374);
        scrollPaneViewEvent.setBackground(Color.BLACK);
        frame.getContentPane().add(scrollPaneViewEvent);
        //Create panel including all events and add it to scrollpane
        EventListPanel eventListPanel = new EventListPanel(false);
        eventListPanel.hideBookButton();
        scrollPaneViewEvent.setViewportView(eventListPanel);


        JButton btnConfirmAll = new JButton("Confirm All Bookings");
        btnConfirmAll.setBounds(448, 485, 230, 53);
        btnConfirmAll.setForeground(SystemColor.inactiveCaption);
        btnConfirmAll.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnConfirmAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnConfirmAll.setOpaque(false);
        btnConfirmAll.setBorderPainted(false);
        btnConfirmAll.setContentAreaFilled(false);
        btnConfirmAll.setVisible(false);
        //change status of pending bookings to confirmed if they are paid
        btnConfirmAll.addActionListener(e -> tableConfirmBooking.updateStatus());
        frame.getContentPane().add(btnConfirmAll);

        JSeparator separatorConfirmAll = new JSeparator();
        separatorConfirmAll.setBounds(448, 530, 230, 3);
        separatorConfirmAll.setBackground(SystemColor.inactiveCaption);
        separatorConfirmAll.setForeground(SystemColor.inactiveCaption);
        separatorConfirmAll.setVisible(false);
        separatorConfirmAll.setOpaque(true);
        frame.getContentPane().add(separatorConfirmAll);

        //Panel including the booking History Table, including all bookings
        tableConfirmBooking = new BookingsHistoryView();
        tableConfirmBooking.setBackground(SystemColor.inactiveCaption);
        frame.getContentPane().add(tableConfirmBooking);

        JButton btnViewEventList = new JButton("View Event List");
        /**
         * Listener for setting the event panel visible.
         */
        btnViewEventList.addActionListener(e -> {
            scrollPaneViewEvent.setVisible(true);

            tableConfirmBooking.setVisible(false);
            btnConfirmAll.setVisible(false);
            separatorConfirmAll.setVisible(false);
            genInvPanel.setVisible(false);
        });
        btnViewEventList.setBounds(27, 173, 190, 53);
        btnViewEventList.setForeground(SystemColor.inactiveCaption);
        btnViewEventList.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnViewEventList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnViewEventList.setOpaque(false);
        btnViewEventList.setBorderPainted(false);
        btnViewEventList.setContentAreaFilled(false);
        frame.getContentPane().add(btnViewEventList);


        JButton confirmBooking = new JButton("Confirm Booking");
        confirmBooking.setBounds(27, 253, 190, 53);
        confirmBooking.setForeground(SystemColor.inactiveCaption);
        confirmBooking.setFont(new Font("Open Sans", Font.PLAIN, 20));
        confirmBooking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmBooking.setOpaque(false);
        confirmBooking.setBorderPainted(false);
        confirmBooking.setContentAreaFilled(false);
        /*
         * Listener for setting the booking History panel visible.
         */
        confirmBooking.addActionListener(arg0 -> {
            scrollPaneViewEvent.setVisible(false);
            tableConfirmBooking.setVisible(true);
            btnConfirmAll.setVisible(true);
            separatorConfirmAll.setVisible(true);
            genInvPanel.setVisible(false);
        });
        frame.getContentPane().add(confirmBooking);
        confirmBooking.setVisible(true);


        //Panel for generating and saving/sending monthly invoices
        genInvPanel = new JPanel();
        genInvPanel.setOpaque(false);
        genInvPanel.setLayout(null);
        genInvPanel.setBounds(300,100,900,500);
        genInvPanel.setVisible(false);
        frame.add(genInvPanel);

        //------------Create and add components for the genInvPanel
        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0,0,900,400);
        scrollPane.setPreferredSize(new Dimension(900,400));


        CardLayout cardLayout = new CardLayout();
        Container c = new Container();
        c.setLayout(cardLayout);
        c.setPreferredSize(new Dimension(800,700));
        scrollPane.setViewportView(c);

        JButton btnPrevious = new JButton("<");
        btnPrevious.setBounds(700,400,50,50);
        btnPrevious.addActionListener(arg0->{cardLayout.previous(c);});

        JButton btnNext = new JButton(">");
        btnNext.setBounds(850,400,50,50);
        btnNext.addActionListener(arg0->{cardLayout.next(c);});

        JButton saveAndSend = new JButton("Save and Send Invoices");
        saveAndSend.setBounds(400,450,250,50);
        saveAndSend.setForeground(SystemColor.inactiveCaption);
        saveAndSend.setFont(new Font("Open Sans", Font.PLAIN, 20));
        saveAndSend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveAndSend.setOpaque(false);
        saveAndSend.setBorderPainted(false);
        saveAndSend.setContentAreaFilled(false);
        saveAndSend.addActionListener(new ActionListener() {
            @Override
            /*
            For every card(=invoice) save Invoice data to DB, image to directory
            and finally send them to organizations
             */
            public void actionPerformed(ActionEvent e) {
                for (Component card : c.getComponents()) {
                    if (card instanceof InvoicePanel) {
                        ((InvoicePanel) card).saveInvoice();
                        String fullname = ((InvoicePanel) card).getFullName();
                        String email = ((InvoicePanel) card).getEmail();
                        String imageName = ((InvoicePanel) card).getInvoiceFileName();
                        new SendMail(null, fullname, email).sendMonthlyInvoice(imageName);
                    }
                }
                JOptionPane.showMessageDialog(null, "Invoices sent successfully");
            }
        });
        JLabel browseInvoices = new JLabel("Browse");
        browseInvoices.setBounds(750,400,100,50);
        browseInvoices.setForeground(SystemColor.inactiveCaption);
        browseInvoices.setFont(new Font("Open Sans",Font.BOLD,22));
        browseInvoices.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator saveAndSendSeparator = new JSeparator();
        saveAndSendSeparator.setBounds(400, 485, 250, 3);
        saveAndSendSeparator.setBackground(SystemColor.inactiveCaption);
        saveAndSendSeparator.setForeground(SystemColor.inactiveCaption);
        saveAndSendSeparator.setOpaque(true);

        genInvPanel.add(browseInvoices);
        genInvPanel.add(btnPrevious);
        genInvPanel.add(btnNext);
        genInvPanel.add(scrollPane);
        genInvPanel.add(saveAndSend);
        genInvPanel.add(saveAndSendSeparator);
        //------------------------------------------------------------------------

        frame.add(genInvPanel);

        JButton btnGenerateInvoice = new JButton("Generate Invoice");
        /**
         * Listener for generating invoices.
         */

        btnGenerateInvoice.setBounds(27, 332, 190, 53);
        btnGenerateInvoice.setForeground(SystemColor.inactiveCaption);
        btnGenerateInvoice.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnGenerateInvoice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGenerateInvoice.setOpaque(false);
        btnGenerateInvoice.setBorderPainted(false);
        btnGenerateInvoice.setContentAreaFilled(false);
        //Create one monthly invoice for every organization user and add them to cardLayout
        btnGenerateInvoice.addActionListener(arg0->{
            //view only the genInvPanel
            tableConfirmBooking.setVisible(false);
            btnConfirmAll.setVisible(false);
            separatorConfirmAll.setVisible(false);
            scrollPaneViewEvent.setVisible(false);
            genInvPanel.setVisible(true);

            //get all organizations
            String[] organizations = user.getAllUsernames("organization");
            int counter = 0; //used for creating accurate invoice numbers
            //get bookings of latest month for every organization
            for(String username : organizations){
                //get all customer info
                List<String> customerInfo = user.detailsList(username);
                //get all bookings
                List<List<String>> bookings = new Booking().getBookingsPerMonth(username);
                //create organization invoice if any bookings were made
                if (bookings.size()>0){
                c.add(username,new InvoicePanel(bookings,customerInfo,counter++));}

                //if no invoices were created, show msg and disable the save button
                if(c.getComponents().length == 0){
                    c.add(new JLabel("No Pending Invoices"));
                    saveAndSend.setEnabled(false);}

            }
        });
        frame.getContentPane().add(btnGenerateInvoice);


        //Separators for buttons and background images--------

        JSeparator separatorViewEvent = new JSeparator();
        separatorViewEvent.setBounds(41, 212, 159, 3);
        separatorViewEvent.setBackground(SystemColor.inactiveCaption);
        separatorViewEvent.setForeground(SystemColor.inactiveCaption);
        separatorViewEvent.setOpaque(true);
        frame.getContentPane().add(separatorViewEvent);

        JSeparator separatorConfirmBooking = new JSeparator();
        separatorConfirmBooking.setBounds(41, 291, 159, 3);
        separatorConfirmBooking.setBackground(SystemColor.inactiveCaption);
        separatorConfirmBooking.setForeground(SystemColor.inactiveCaption);
        separatorConfirmBooking.setOpaque(true);
        frame.getContentPane().add(separatorConfirmBooking);

        JSeparator separatorGenerateInvoice = new JSeparator();
        separatorGenerateInvoice.setBounds(41, 370, 165, 3);
        separatorGenerateInvoice.setBackground(SystemColor.inactiveCaption);
        separatorGenerateInvoice.setForeground(SystemColor.inactiveCaption);
        separatorGenerateInvoice.setOpaque(true);
        frame.getContentPane().add(separatorGenerateInvoice);

        JSeparator separatorLogOut = new JSeparator();
        separatorLogOut.setBounds(42, 448, 87, 3);
        separatorLogOut.setBackground(SystemColor.inactiveCaption);
        separatorLogOut.setForeground(SystemColor.inactiveCaption);
        separatorLogOut.setOpaque(true);
        frame.getContentPane().add(separatorLogOut);
        
        
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(Main.IMAGE_DIR+"Logo.jpg"));
        lblLogo.setBounds(186, 583, 200, 96);
        frame.getContentPane().add(lblLogo);


        JLabel lblImageLabel = new JLabel("Image");
        lblImageLabel.setForeground(Color.BLACK);
        lblImageLabel.setIcon(new ImageIcon(Main.IMAGE_DIR+"Silhouette-Rock-Concert-Wallpaper1.jpg"));
        lblImageLabel.setBounds(0, 0, 1297, 693);
        frame.getContentPane().add(lblImageLabel);
        
        
   

    }
}
