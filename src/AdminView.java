import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

/**
 * Class for creating the frame for the admin's window.
 * @author x64
 *
 */
public class AdminView {

	private JFrame frame;
    private JTable tableConfirmBooking;


    /**
     * Launch the application.
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                AdminView window = new AdminView();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

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
        /**
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
        /**
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
        /**
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
        
        /**
         * Combobox populated with all users names for selection.
         */
        JComboBox comboBoxUsername = new JComboBox(user.getAllUsernames());
        comboBoxUsername.addActionListener(e -> {
            User.username = comboBoxUsername.getSelectedItem().toString();
            new PopUp();
        });
        comboBoxUsername.setBackground(SystemColor.activeCaption);
        comboBoxUsername.setBounds(204, 55, 182, 25);
        frame.getContentPane().add(comboBoxUsername);

        JScrollPane scrollPaneViewEvent = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneViewEvent.setBounds(400, 173, 840, 374);
        scrollPaneViewEvent.setBackground(Color.BLACK);
        frame.getContentPane().add(scrollPaneViewEvent);
        ResultPanel resultPanel = new ResultPanel(false);
        resultPanel.hideBookButton();
        scrollPaneViewEvent.setViewportView(resultPanel);


        JButton btnConfirmAll = new JButton("Confirm All Bookings");
        btnConfirmAll.setBounds(448, 485, 230, 53);
        btnConfirmAll.setForeground(SystemColor.inactiveCaption);
        btnConfirmAll.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnConfirmAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnConfirmAll.setOpaque(false);
        btnConfirmAll.setBorderPainted(false);
        btnConfirmAll.setContentAreaFilled(false);
        btnConfirmAll.setVisible(false);
        btnConfirmAll.addActionListener(e -> BookingsTableController.updateStatus(tableConfirmBooking));
        frame.getContentPane().add(btnConfirmAll);

        JSeparator separatorConfirmAll = new JSeparator();
        separatorConfirmAll.setBounds(448, 530, 230, 3);
        separatorConfirmAll.setBackground(SystemColor.inactiveCaption);
        separatorConfirmAll.setForeground(SystemColor.inactiveCaption);
        separatorConfirmAll.setVisible(false);
        separatorConfirmAll.setOpaque(true);
        frame.getContentPane().add(separatorConfirmAll);

        JScrollPane scrollPaneConfirmBooking = new JScrollPane();
        scrollPaneConfirmBooking.setBounds(448, 173, 750, 300);
        frame.getContentPane().add(scrollPaneConfirmBooking);
        scrollPaneConfirmBooking.setVisible(false);
        
        tableConfirmBooking = new JTable();
        new BookingsTableController(tableConfirmBooking);
        tableConfirmBooking.setBackground(SystemColor.inactiveCaption);
        scrollPaneConfirmBooking.setViewportView(tableConfirmBooking);

        JButton btnViewEventList = new JButton("View Event List");
        /**
         * Listener for setting the event panel visible.
         */
        btnViewEventList.addActionListener(e -> {
            scrollPaneViewEvent.setVisible(true);
            scrollPaneConfirmBooking.setVisible(false);
            btnConfirmAll.setVisible(false);
            separatorConfirmAll.setVisible(false);
        });
        btnViewEventList.setBounds(27, 173, 190, 53);
        btnViewEventList.setForeground(SystemColor.inactiveCaption);
        btnViewEventList.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnViewEventList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnViewEventList.setOpaque(false);
        btnViewEventList.setBorderPainted(false);
        btnViewEventList.setContentAreaFilled(false);
        frame.getContentPane().add(btnViewEventList);


        JButton btnConfirmBooking = new JButton("Confirm Booking");
        btnConfirmBooking.setBounds(27, 253, 190, 53);
        btnConfirmBooking.setForeground(SystemColor.inactiveCaption);
        btnConfirmBooking.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnConfirmBooking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnConfirmBooking.setOpaque(false);
        btnConfirmBooking.setBorderPainted(false);
        btnConfirmBooking.setContentAreaFilled(false);
        /**
         * Listener for setting the confirm booking panel visible.
         */
        btnConfirmBooking.addActionListener(arg0 -> {
            scrollPaneViewEvent.setVisible(false);
            scrollPaneConfirmBooking.setVisible(true);
            btnConfirmAll.setVisible(true);
            separatorConfirmAll.setVisible(true);
        });
        frame.getContentPane().add(btnConfirmBooking);
        btnConfirmBooking.setVisible(true);


        JButton btnGenerateInvoice = new JButton("Generate Invoice");
        /**
         * Listener for generating invoices.
         */
        btnGenerateInvoice.addActionListener(e -> {
            scrollPaneViewEvent.setVisible(false);
            scrollPaneConfirmBooking.setVisible(false);
            btnConfirmAll.setVisible(false);
            separatorConfirmAll.setVisible(false);
        });
        btnGenerateInvoice.setBounds(27, 332, 190, 53);
        btnGenerateInvoice.setForeground(SystemColor.inactiveCaption);
        btnGenerateInvoice.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnGenerateInvoice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGenerateInvoice.setOpaque(false);
        btnGenerateInvoice.setBorderPainted(false);
        btnGenerateInvoice.setContentAreaFilled(false);
        frame.getContentPane().add(btnGenerateInvoice);



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
