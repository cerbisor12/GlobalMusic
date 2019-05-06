import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;

public class AdminView {

	private JFrame frame;
    private JTable tableConfirmBooking;


    /**
     * Launch the application.
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView window = new AdminView();
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
        minimizeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
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


        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setBounds(22, 410, 121, 53);
        btnLogOut.setForeground(SystemColor.inactiveCaption);
        btnLogOut.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogOut.setOpaque(false);
        btnLogOut.setBorderPainted(false);
        btnLogOut.setContentAreaFilled(false);
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new LoginView();
                frame.setVisible(false);
            }
        });
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(btnLogOut);

        JLabel lblChooseUsername = new JLabel("Choose Username");
        lblChooseUsername.setBounds(48, 56, 130, 14);
        lblChooseUsername.setForeground(SystemColor.inactiveCaption);
        lblChooseUsername.setFont(new Font("Open Sans", Font.BOLD, 14));
        frame.getContentPane().add(lblChooseUsername);
        
        JComboBox<String> comboBoxUsername = new JComboBox<String>(User.userList());
        comboBoxUsername.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		User.username = comboBoxUsername.getSelectedItem().toString();
        		new PopUp();
        	}
        });
        comboBoxUsername.setBackground(SystemColor.activeCaption);
        comboBoxUsername.setBounds(204, 55, 182, 25);
        frame.getContentPane().add(comboBoxUsername);
        
        
        JScrollPane scrollPaneViewEvent = new JScrollPane();
        scrollPaneViewEvent.setBounds(548, 173, 550, 222);
        frame.getContentPane().add(scrollPaneViewEvent);
        scrollPaneViewEvent.setVisible(false);
        
        
        JPanel panelViewEvent = new JPanel();
        scrollPaneViewEvent.setViewportView(panelViewEvent);
        panelViewEvent.setLayout(new BoxLayout(panelViewEvent, BoxLayout.Y_AXIS));
        
        JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(50,50));
        panelViewEvent.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblPanel_1 = new JLabel("New label");
        lblPanel_1.setBounds(0, 0, 150, 55);
        panel_1.add(lblPanel_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setPreferredSize(new Dimension(50,50));
        panelViewEvent.add(panel_2);
        panel_2.setLayout(null);
        
        JLabel lblPanel_2 = new JLabel("New label");
        lblPanel_2.setBounds(0, 0, 150, 55);
        panel_2.add(lblPanel_2);
        
        JPanel panel_3 = new JPanel();
        panel_3.setPreferredSize(new Dimension(50,50));
        panelViewEvent.add(panel_3);
        panel_3.setLayout(null);
        
        JLabel lblPanel_3 = new JLabel("New label");
        lblPanel_3.setBounds(0, 0, 150, 55);
        panel_3.add(lblPanel_3);
        
        
        
        JScrollPane scrollPaneConfirmBooking = new JScrollPane();
        scrollPaneConfirmBooking.setBounds(548, 173, 550, 222);
        frame.getContentPane().add(scrollPaneConfirmBooking);
        scrollPaneConfirmBooking.setVisible(false);
        
        tableConfirmBooking = new JTable();
        tableConfirmBooking.setBackground(SystemColor.inactiveCaption);
        tableConfirmBooking.setForeground(SystemColor.inactiveCaption);
        tableConfirmBooking.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"New column", "New column", "New column", "New column", "New column", "New column"
        	}
        ));
        scrollPaneConfirmBooking.setViewportView(tableConfirmBooking);

        JButton btnViewEventList = new JButton("View Event List");
        btnViewEventList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPaneViewEvent.setVisible(true);
                scrollPaneConfirmBooking.setVisible(false);
            }
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
        btnConfirmBooking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                scrollPaneViewEvent.setVisible(false);
                scrollPaneConfirmBooking.setVisible(true);
            }
        });
        frame.getContentPane().add(btnConfirmBooking);
        btnConfirmBooking.setVisible(true);


        JButton btnGenerateInvoice = new JButton("Generate Invoice");
        btnGenerateInvoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPaneViewEvent.setVisible(false);
                scrollPaneConfirmBooking.setVisible(false);
            }
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
        lblLogo.setIcon(new ImageIcon(LoginView.class.getResource("Images/Logo.jpg")));
        lblLogo.setBounds(186, 583, 200, 96);
        frame.getContentPane().add(lblLogo);


        JLabel lblImageLabel = new JLabel("Image");
        lblImageLabel.setForeground(Color.BLACK);
        lblImageLabel.setIcon(new ImageIcon(LoginView.class.getResource("Images/Silhouette-Rock-Concert-Wallpaper1.jpg")));
        lblImageLabel.setBounds(0, 0, 1297, 693);
        frame.getContentPane().add(lblImageLabel);
        
        
   

    }
}
