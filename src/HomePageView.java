import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import javax.swing.border.MatteBorder;

public class HomePageView {

	JButton searchButton;
    JFrame frame;
    JPanel newPanel;
    //private String username;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                	HomePageView window = new HomePageView();
//                    window.frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        }
//    

    /**
     * Create the application.
     */
    public HomePageView() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Global Music");
        frame.setBounds(100, 100, 1280, 690);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        Container c = new Container();
        c.setBounds(250,50,1000,550);
        c.setLayout(new CardLayout());
        c.add("Search",new SearchEventsPanel());
        c.add("History",new BookingsHistoryView());
        c.add("MyAccount", new MyAccountView());
        c.add("Password",new ChangePasswordView());
        ((CardLayout)c.getLayout()).first(c);
        frame.add(c);


        searchButton = new JButton("Search events");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomePageView();
                frame.setVisible(false);
            }
        });
        searchButton.setForeground(SystemColor.inactiveCaption);
        searchButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
        searchButton.setBounds(27, 111, 200, 53);
        searchButton.setOpaque(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setToolTipText("See upcoming or search for your favorite events");
        searchButton.setBorderPainted(false);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setEnabled(false);
        frame.getContentPane().add(searchButton);

        JButton myAccountButton = new JButton("My Account");
        myAccountButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                ((CardLayout)c.getLayout()).show(c,"MyAccount");
        	}
        });
        myAccountButton.setForeground(SystemColor.inactiveCaption);
        myAccountButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
        myAccountButton.setOpaque(false);
        myAccountButton.setContentAreaFilled(false);
        myAccountButton.setBorderPainted(false);
        myAccountButton.setBounds(27, 189, 200, 53);
        myAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myAccountButton.setToolTipText("Edit your personal details");
        frame.getContentPane().add(myAccountButton);
        
        JButton changePassButton = new JButton("Change Password");
        changePassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)c.getLayout()).show(c,"Password");
            }
        });
        changePassButton.setForeground(SystemColor.inactiveCaption);
        changePassButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
        changePassButton.setOpaque(false);
        changePassButton.setContentAreaFilled(false);
        changePassButton.setBorderPainted(false);
        changePassButton.setBounds(27, 268, 200, 53);
        changePassButton.setToolTipText("Update security details");
        changePassButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(changePassButton);

        JButton bookingsButton = new JButton("My bookings");
        bookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)c.getLayout()).show(c,"History");
            }
        });
        bookingsButton.setForeground(SystemColor.inactiveCaption);
        bookingsButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
        bookingsButton.setOpaque(false);
        bookingsButton.setContentAreaFilled(false);
        bookingsButton.setBorderPainted(false);
        bookingsButton.setBounds(27, 346, 200, 53);
        bookingsButton.setToolTipText("View your booked events");
        bookingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(bookingsButton);
        
        JButton logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new LoginView();
        		frame.setVisible(false);
        	}
        });
        logOutButton.setBounds(27, 426, 200, 53);
        logOutButton.setForeground(SystemColor.inactiveCaption);
        logOutButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
        logOutButton.setOpaque(false);
        logOutButton.setContentAreaFilled(false);
        logOutButton.setBorderPainted(false);
        logOutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(logOutButton);
        

        
        
       
        JButton exitButton = new JButton("X");
        exitButton.setForeground(SystemColor.inactiveCaption);
        exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                  System.exit(0);
                }
            }
        });
        exitButton.setFont(new Font("Open Sans", Font.PLAIN, 25));
        exitButton.setBorderPainted(false);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBounds(1205, 13, 63, 53);
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(exitButton);

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

        JSeparator separator = new JSeparator();
        separator.setBackground(SystemColor.inactiveCaption);
        separator.setOpaque(true);
        separator.setBounds(60, 148, 130, 3);
        frame.getContentPane().add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(SystemColor.inactiveCaption);
        separator_1.setOpaque(true);
        separator_1.setBounds(66, 227, 120, 3);
        frame.getContentPane().add(separator_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBackground(SystemColor.inactiveCaption);
        separator_2.setOpaque(true);
        separator_2.setBounds(35, 306, 190, 3);
        frame.getContentPane().add(separator_2);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setOpaque(true);
        separator_3.setBackground(SystemColor.inactiveCaption);
        separator_3.setBounds(66, 384, 120, 3);
        frame.getContentPane().add(separator_3);
        
        JSeparator separator_4 = new JSeparator();
        separator_4.setBackground(SystemColor.inactiveCaption);
        separator_4.setOpaque(true);
        separator_4.setBounds(76, 465, 100, 3);
        frame.getContentPane().add(separator_4);
        
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(LoginView.class.getResource("Images/Logo.jpg")));
        lblLogo.setBounds(186, 583, 200, 96);
        frame.getContentPane().add(lblLogo);
        //frame.getRootPane().setDefaultButton(searchButton1);
        
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(HomePageView.class.getResource("Images/Silhouette-Rock-Concert-Wallpaper1.jpg")));
        backgroundLabel.setBounds(0, 0, 1280, 690);
        frame.getContentPane().add(backgroundLabel);
    }
}