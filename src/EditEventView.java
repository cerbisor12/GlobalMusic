import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class EditEventView {

	 private JFrame frame;
	 private JTextField textDuration;
	 private JTextField textDate;
	 private JTextField textFieldPrice;

	    /**
	     * Launch the application.
	     */
	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    EditEventView window = new EditEventView();
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
	    public EditEventView() {
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
	        
	    
	        
	        JButton btnAddNewEvent = new JButton("Add New Event");
	        btnAddNewEvent.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                new EventOrganizerView();
	                frame.setVisible(false);
	            }
	        });
	        btnAddNewEvent.setBounds(22, 171, 190, 53);
	        btnAddNewEvent.setForeground(SystemColor.inactiveCaption);
	        btnAddNewEvent.setFont(new Font("Open Sans", Font.PLAIN, 20));
	        btnAddNewEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnAddNewEvent.setOpaque(false);
	        btnAddNewEvent.setBorderPainted(false);
	        btnAddNewEvent.setContentAreaFilled(false);
	        frame.getContentPane().add(btnAddNewEvent);
	        
	        
	        JButton btnLogOut = new JButton("Log Out");
	        btnLogOut.setBounds(22, 264, 121, 53);
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
	        
	        
	        JSeparator separator = new JSeparator();
	        separator.setBounds(41, 212, 159, 3);
	        separator.setBackground(SystemColor.inactiveCaption);
	        separator.setForeground(SystemColor.inactiveCaption);
	        separator.setOpaque(true);
	        frame.getContentPane().add(separator);

	        JSeparator separator_2 = new JSeparator();
	        separator_2.setBounds(41, 303, 89, 3);
	        separator_2.setBackground(SystemColor.inactiveCaption);
	        separator_2.setForeground(SystemColor.inactiveCaption);
	        separator_2.setOpaque(true);
	        frame.getContentPane().add(separator_2);
	        
	        
	        JLabel lblEventName = new JLabel("Event Name");
	        lblEventName.setBounds(496, 187, 99, 21);
	        lblEventName.setForeground(SystemColor.inactiveCaption);
	        lblEventName.setFont(new Font("Open Sans", Font.BOLD, 13));
	        frame.getContentPane().add(lblEventName);
	        
	        
	        JComboBox<String> comboBoxEventName = new JComboBox<String>();
	        comboBoxEventName.setBackground(SystemColor.activeCaption);
	        comboBoxEventName.setBounds(605, 191, 159, 20);
	        frame.getContentPane().add(comboBoxEventName);
	        
	        
	        JLabel lblDate = new JLabel("Date");
	        lblDate.setForeground(SystemColor.inactiveCaption);
	        lblDate.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblDate.setBounds(496, 250, 99, 21);
	        frame.getContentPane().add(lblDate);
	        
	        textDate = new JTextField();
	        textDate.setColumns(10);
	        textDate.setBorder(new MatteBorder(2, 2, 2, 2, (Color) SystemColor.activeCaption));
	        textDate.setBackground(SystemColor.activeCaption);
	        textDate.setBounds(605, 251, 159, 20);
	        frame.getContentPane().add(textDate);
	        
	        
	        JLabel lblDuration = new JLabel("Duration");
	        lblDuration.setForeground(SystemColor.inactiveCaption);
	        lblDuration.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblDuration.setBounds(496, 312, 99, 21);
	        frame.getContentPane().add(lblDuration);
	        
	        
	        textDuration = new JTextField();
	        textDuration.setColumns(10);
	        textDuration.setBorder(new MatteBorder(2, 2, 2, 2, (Color) SystemColor.activeCaption));
	        textDuration.setBackground(SystemColor.activeCaption);
	        textDuration.setBounds(605, 313, 159, 20);
	        frame.getContentPane().add(textDuration);
	        
	        
	        JLabel lblVenue = new JLabel("Venue");
	        lblVenue.setForeground(SystemColor.inactiveCaption);
	        lblVenue.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblVenue.setBounds(878, 187, 99, 21);
	        frame.getContentPane().add(lblVenue);
	        
	        
	        JComboBox<String> comboBoxVenue = new JComboBox<String>();
	        comboBoxVenue.setBounds(987, 188, 159, 20);
	        comboBoxVenue.setBackground(SystemColor.activeCaption);
	        frame.getContentPane().add(comboBoxVenue);
	        
	        
	        JLabel lblPrice = new JLabel("Price");
	        lblPrice.setForeground(SystemColor.inactiveCaption);
	        lblPrice.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblPrice.setBounds(878, 250, 99, 21);
	        frame.getContentPane().add(lblPrice);
	        
	        
	        textFieldPrice = new JTextField();
	        textFieldPrice.setColumns(10);
	        textFieldPrice.setBorder(new MatteBorder(2, 2, 2, 2, (Color) SystemColor.activeCaption));
	        textFieldPrice.setBackground(SystemColor.activeCaption);
	        textFieldPrice.setBounds(987, 251, 159, 20);
	        frame.getContentPane().add(textFieldPrice);
	        
	        
	        JLabel lblUploadImage = new JLabel("Upload Image");
	        lblUploadImage.setForeground(SystemColor.inactiveCaption);
	        lblUploadImage.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblUploadImage.setBounds(878, 316, 99, 21);
	        frame.getContentPane().add(lblUploadImage);
	        
	        
	        JButton btnUploadButton = new JButton("");
	        btnUploadButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileFilter(new FileNameExtensionFilter("JPG images","jpg"));
					StringBuilder sb = new StringBuilder();

					try {
						if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
							java.io.File file = fileChooser.getSelectedFile();
							Scanner input = new Scanner(file);
							
							while (input.hasNext()) {
								sb.append(input.nextLine());
								sb.append("\n");
							}
							input.close();
						}
						else {
							sb.append("No file selected.");
						}
					}catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			});
	        btnUploadButton.setBorderPainted(false);
	        btnUploadButton.setIcon(new ImageIcon(LoginView.class.getResource("Images/UploadButton.jpg")));
	        btnUploadButton.setBounds(987, 312, 159, 23);
	        btnUploadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        frame.getContentPane().add(btnUploadButton);
	        
	        
	        JLabel lblPerformers = new JLabel("Performers");
	        lblPerformers.setFont(new Font("Open Sans", Font.BOLD, 18));
	        lblPerformers.setForeground(SystemColor.inactiveCaption);
	        lblPerformers.setBounds(605, 373, 128, 21);
	        frame.getContentPane().add(lblPerformers);
	        
	        
	        JLabel lblBand_1 = new JLabel("Band 1");
	        lblBand_1.setForeground(SystemColor.inactiveCaption);
	        lblBand_1.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblBand_1.setBounds(496, 437, 99, 21);
	        frame.getContentPane().add(lblBand_1);
	        
	        
	        JComboBox<String> comboBoxBand_1 = new JComboBox<String>();
	        comboBoxBand_1.setBounds(605, 438, 159, 20);
	        comboBoxBand_1.setBackground(SystemColor.activeCaption);
	        frame.getContentPane().add(comboBoxBand_1);
	        
	        
	        JLabel lblBand_2 = new JLabel("Band 2");
	        lblBand_2.setForeground(SystemColor.inactiveCaption);
	        lblBand_2.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblBand_2.setBounds(878, 437, 99, 21);
	        frame.getContentPane().add(lblBand_2);
	        
	        
	        JComboBox<String> comboBoxBand_2 = new JComboBox<String>();
	        comboBoxBand_2.setBackground(SystemColor.activeCaption);
	        comboBoxBand_2.setBounds(987, 438, 159, 20);
	        frame.getContentPane().add(comboBoxBand_2);
	        
	        
	        
	        JLabel lblBand_3 = new JLabel("Band 3");
	        lblBand_3.setForeground(SystemColor.inactiveCaption);
	        lblBand_3.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblBand_3.setBounds(496, 499, 99, 21);
	        frame.getContentPane().add(lblBand_3);
	        
	        
	        JComboBox<String> comboBoxBand_3 = new JComboBox<String>();
	        comboBoxBand_3.setBackground(SystemColor.activeCaption);
	        comboBoxBand_3.setBounds(605, 500, 159, 20);
	        frame.getContentPane().add(comboBoxBand_3);
	        
	        
	        JLabel lblBand_4 = new JLabel("Band 4");
	        lblBand_4.setForeground(SystemColor.inactiveCaption);
	        lblBand_4.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblBand_4.setBounds(878, 499, 99, 21);
	        frame.getContentPane().add(lblBand_4);
	        
	        
	        JComboBox<String> comboBoxBand_4 = new JComboBox<String>();
	        comboBoxBand_4.setBackground(SystemColor.activeCaption);
	        comboBoxBand_4.setBounds(987, 500, 159, 20);
	        frame.getContentPane().add(comboBoxBand_4);
	        
	        
	        JButton btnSaveButton = new JButton("Save Changes");
	        btnSaveButton.setOpaque(false);
	        btnSaveButton.setForeground(SystemColor.inactiveCaption);
	        btnSaveButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
	        btnSaveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnSaveButton.setContentAreaFilled(false);
	        btnSaveButton.setBorderPainted(false);
	        btnSaveButton.setBounds(967, 554, 179, 53);
	        frame.getContentPane().add(btnSaveButton);
	        
	        
	        JLabel lblSavecancelEvent = new JLabel("Edit/Cancel Event");
	        lblSavecancelEvent.setForeground(SystemColor.inactiveCaption);
	        lblSavecancelEvent.setFont(new Font("Open Sans", Font.BOLD, 18));
	        lblSavecancelEvent.setBounds(776, 86, 215, 21);
	        frame.getContentPane().add(lblSavecancelEvent);
	        
	        JComboBox<String> comboBox = new JComboBox<String>();
	        comboBox.setBackground(SystemColor.activeCaption);
	        comboBox.setBounds(605, 191, 159, 20);
	        frame.getContentPane().add(comboBox);
	        
	        JSeparator separatorSave = new JSeparator();
	        separatorSave.setBounds(987, 592, 142, 3);
	        separatorSave.setBackground(SystemColor.inactiveCaption);
	        separatorSave.setForeground(SystemColor.inactiveCaption);
	        separatorSave.setOpaque(true);
	        frame.getContentPane().add(separatorSave);
	        
	        
	        JButton btnCancel = new JButton("Cancel Event");
	        btnCancel.setBounds(623, 569, 159, 23);
	        btnCancel.setForeground(SystemColor.inactiveCaption);
	        btnCancel.setFont(new Font("Open Sans", Font.PLAIN, 20));
	        btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnCancel.setContentAreaFilled(false);
	        btnCancel.setBorderPainted(false);
	        frame.getContentPane().add(btnCancel);
	        
	        
	        JSeparator separatorCancel = new JSeparator();
	        separatorCancel.setBounds(633, 592, 142, 3);
	        separatorCancel.setBackground(SystemColor.inactiveCaption);
	        separatorCancel.setForeground(SystemColor.inactiveCaption);
	        separatorCancel.setOpaque(true);
	        frame.getContentPane().add(separatorCancel);
	        
	        
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
