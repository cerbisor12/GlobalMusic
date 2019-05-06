import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JList;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class EventOrganizerView {

	 private JFrame frame;
	 private JTextField textEventName;
	 private JTextField textDuration;
	 private JTextField textFieldPrice;
	 private JTextField venueNameTextField;
	 private JTextField venueAddressTextField;
	 private JTextField venueCapacityTextField;
	 private ArrayList<String> bandsList;
	 static JComboBox<String> venueComboBox;
	 static JList allPerformersList;
	 private String imageName = "";
	 private JLabel lblImageName;
	
	    /**
	     * Launch the application.
	     */
	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    EventOrganizerView window = new EventOrganizerView();
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
	    public EventOrganizerView() {
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
	        
	        
	        JButton btnEditEvent = new JButton("Edit/Cancel Event");
	        btnEditEvent.setBounds(10, 164, 202, 60);
	        btnEditEvent.setForeground(SystemColor.inactiveCaption);
	        btnEditEvent.setFont(new Font("Open Sans", Font.PLAIN, 20));
	        btnEditEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnEditEvent.setOpaque(false);
	        btnEditEvent.setBorderPainted(false);
	        btnEditEvent.setContentAreaFilled(false);
	        btnEditEvent.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	new EditEventView();
	            	frame.setVisible(false);	
	            }
	        });
	        frame.getContentPane().add(btnEditEvent);
	        btnEditEvent.setVisible(true);
	        
	        
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

	        JSeparator separator_1 = new JSeparator();
	        separator_1.setOpaque(true);
	        separator_1.setBounds(23, 207, 178, 3);
	        separator_1.setBackground(SystemColor.inactiveCaption);
	        separator_1.setForeground(SystemColor.inactiveCaption);
	        frame.getContentPane().add(separator_1);

	        JSeparator separator_2 = new JSeparator();
	        separator_2.setBounds(35, 301, 108, 3);
	        separator_2.setBackground(SystemColor.inactiveCaption);
	        separator_2.setForeground(SystemColor.inactiveCaption);
	        separator_2.setOpaque(true);
	        frame.getContentPane().add(separator_2);
	        
	        
	        JLabel lblEventName = new JLabel("Event Name");
	        lblEventName.setBounds(496, 187, 99, 21);
	        lblEventName.setForeground(SystemColor.inactiveCaption);
	        lblEventName.setFont(new Font("Open Sans", Font.BOLD, 13));
	        frame.getContentPane().add(lblEventName);
	        
	        
	        
	        textEventName = new JTextField();
	        textEventName.setBounds(605, 188, 168, 20);
	        textEventName.setBackground(SystemColor.activeCaption);
	        textEventName.setBorder(new MatteBorder(2, 2, 2, 2, (Color) SystemColor.activeCaption));
	        frame.getContentPane().add(textEventName);
	        textEventName.setColumns(10);
	        
	        DatePickerSettings dateSettings = new DatePickerSettings();
	        dateSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
	        dateSettings.setAllowKeyboardEditing(false);
	        dateSettings.setAllowEmptyDates(false);

	        DatePicker datePicker = new DatePicker(dateSettings);
	        dateSettings.setDateRangeLimits(LocalDate.now(),null);
	        datePicker.getComponentDateTextField().setBackground(SystemColor.activeCaption);
	        datePicker.getComponentToggleCalendarButton().setText("Date");
	        datePicker.setBounds(604, 250, 168, 22);
	        frame.getContentPane().add(datePicker);
	        
	        
	        JLabel lblDate = new JLabel("Date");
	        lblDate.setForeground(SystemColor.inactiveCaption);
	        lblDate.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblDate.setBounds(496, 250, 99, 21);
	        frame.getContentPane().add(lblDate);
	        
	        
	        JLabel lblDuration = new JLabel("Duration");
	        lblDuration.setForeground(SystemColor.inactiveCaption);
	        lblDuration.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblDuration.setBounds(496, 312, 99, 21);
	        frame.getContentPane().add(lblDuration);
	        
	        JLabel durationFormatLabel = new JLabel("Invalid Number");
	        durationFormatLabel.setForeground(Color.RED);
	        durationFormatLabel.setVisible(false);
	        durationFormatLabel.setBounds(605, 337, 139, 16);
	        frame.getContentPane().add(durationFormatLabel);
	        
	        
	        textDuration = new JTextField();
	        textDuration.setColumns(10);
	        textDuration.setBorder(new MatteBorder(2, 2, 2, 2, (Color) SystemColor.activeCaption));
	        textDuration.setBackground(SystemColor.activeCaption);
	        textDuration.setBounds(605, 313, 168, 20);
	        textDuration.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					try {
						Integer.parseInt(textDuration.getText());
						durationFormatLabel.setVisible(false);
					}catch(NumberFormatException n) {
						textDuration.setText("");
						durationFormatLabel.setVisible(true);
					}
				}
			});
	        frame.getContentPane().add(textDuration);
	        
	        
	        JLabel lblVenue = new JLabel("Venue");
	        lblVenue.setForeground(SystemColor.inactiveCaption);
	        lblVenue.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblVenue.setBounds(878, 187, 99, 21);
	        frame.getContentPane().add(lblVenue);
	        
	        
	        venueComboBox = new JComboBox<String>();
	        ArrayList venuesList = Venue.getVenueList();
	        venuesList.add(0, "-Add Venue-");
	        venueComboBox.setModel(new DefaultComboBoxModel(venuesList.toArray()));
	        venueComboBox.setBounds(987, 188, 159, 20);
	        venueComboBox.setEditable(false);
	        venueComboBox.setBackground(SystemColor.activeCaption);
	        venueComboBox.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		Object selected = venueComboBox.getSelectedItem();
	        		if (selected.toString().equals("-Add Venue-"))
	        			new NewVenueView();
	        	}
	        });
	        frame.getContentPane().add(venueComboBox);
	        
	        
	        
	        JLabel priceFormatLabel = new JLabel("Invalid Number");
	        priceFormatLabel.setForeground(Color.RED);
	        priceFormatLabel.setVisible(false);
	        priceFormatLabel.setBounds(987, 271, 89, 16);
	        frame.getContentPane().add(priceFormatLabel);
	        
	        
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
	        textFieldPrice.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) { }
				@Override
				public void focusLost(FocusEvent e) {
						try {
							Float.parseFloat(textFieldPrice.getText());
							priceFormatLabel.setVisible(false);
						}catch(NumberFormatException n) {
							textFieldPrice.setText("");
							priceFormatLabel.setVisible(true);
						}
					}
				});
	        frame.getContentPane().add(textFieldPrice);
	        
	        
	        JLabel lblUploadImage = new JLabel("Upload Image");
	        lblUploadImage.setForeground(SystemColor.inactiveCaption);
	        lblUploadImage.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblUploadImage.setBounds(878, 316, 99, 21);
	        frame.getContentPane().add(lblUploadImage);

			lblImageName = new JLabel("");
			lblImageName.setBounds(987, 335, 159, 23);
			lblImageName.setForeground(SystemColor.inactiveCaption);
			lblImageName.setFont(new Font("Open Sans", Font.PLAIN, 12));
			frame.getContentPane().add(lblImageName);
	        
	        
	        JButton btnUploadButton = new JButton("");
	        btnUploadButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileFilter(new FileNameExtensionFilter("Images","jpg","png","jpeg"));

					try {
						if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
							File file = fileChooser.getSelectedFile();
							imageName = file.getName();
							lblImageName.setText(imageName);

							Files.copy(file.toPath(),Paths.get(System.getProperty("user.dir")+"/src/"+Main.EVENT_IMAGE_DIR+file.getName()),
									java.nio.file.StandardCopyOption.REPLACE_EXISTING,
									java.nio.file.StandardCopyOption.COPY_ATTRIBUTES,
									java.nio.file.LinkOption.NOFOLLOW_LINKS );
						}else {
							imageName = "No file selected!";
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
	        
	        
	        JLabel lblPerformers = new JLabel("Available Performers");
	        lblPerformers.setFont(new Font("Dialog", Font.BOLD, 18));
	        lblPerformers.setForeground(SystemColor.inactiveCaption);
	        lblPerformers.setBounds(605, 373, 189, 21);
	        frame.getContentPane().add(lblPerformers);
	        
	        
	        JSeparator separator_4 = new JSeparator();
	        separator_4.setOpaque(true);
	        separator_4.setForeground(SystemColor.inactiveCaption);
	        separator_4.setBackground(SystemColor.inactiveCaption);
	        separator_4.setBounds(1020, 588, 110, 3);
	        frame.getContentPane().add(separator_4);
	        separator_4.setVisible(true);
	        
	        JLabel lblAddNewEvent = new JLabel("Add New Event");
	        lblAddNewEvent.setForeground(SystemColor.inactiveCaption);
	        lblAddNewEvent.setFont(new Font("Open Sans", Font.BOLD, 18));
	        lblAddNewEvent.setBounds(788, 86, 189, 21);
	        frame.getContentPane().add(lblAddNewEvent);
	        
	       
	        JLabel lblLogo = new JLabel("");
	        lblLogo.setIcon(new ImageIcon(LoginView.class.getResource("Images/Logo.jpg")));
	        lblLogo.setBounds(186, 583, 200, 96);
	        frame.getContentPane().add(lblLogo);
	        
	        ArrayList<String> bandsAvailable = Band.getBands();
	        
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(605, 407, 189, 140);
	        frame.getContentPane().add(scrollPane);
	        
	        DefaultListModel allPerformersModel = new DefaultListModel();
	        for(int i = 0; i < bandsAvailable.size(); i++) {
	        	allPerformersModel.addElement(bandsAvailable.get(i));
	        }
	        
	        allPerformersList = new JList(allPerformersModel);
	        scrollPane.setViewportView(allPerformersList);
	        allPerformersList.setBackground(SystemColor.activeCaption);
	        
	        JScrollPane scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBounds(957, 407, 189, 140);
	        frame.getContentPane().add(scrollPane_1);
	        
	        DefaultListModel addedPerformersModel = new DefaultListModel();
	        
	        JList addedPerfList = new JList(addedPerformersModel);
	        addedPerfList.setBackground(SystemColor.activeCaption);
	        scrollPane_1.setViewportView(addedPerfList);
	        
	        JLabel performersAddedLabel = new JLabel("Added Performers");
	        performersAddedLabel.setBounds(957, 377, 169, 16);
	        performersAddedLabel.setFont(new Font("Open Sans", Font.BOLD, 18));
	        performersAddedLabel.setForeground(SystemColor.inactiveCaption);
	        frame.getContentPane().add(performersAddedLabel);
	        
	        JButton addPerformerToEvent = new JButton("Add to Event ->");
	        addPerformerToEvent.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		String addedBand = (String) allPerformersList.getSelectedValue();
	        		if(addedPerformersModel.contains(addedBand))
	        			JOptionPane.showMessageDialog(null,"Band already added.");
	        		else
	        			addedPerformersModel.addElement(addedBand);
	        		
	        	}
	        });
	        addPerformerToEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        addPerformerToEvent.setFont(new Font("Dialog", Font.PLAIN, 18));
	        addPerformerToEvent.setBounds(793, 467, 167, 25);
	        addPerformerToEvent.setContentAreaFilled(false);
	        addPerformerToEvent.setOpaque(false);
	        addPerformerToEvent.setBorderPainted(false);
	        addPerformerToEvent.setForeground(SystemColor.inactiveCaption);
	        frame.getContentPane().add(addPerformerToEvent);
	        
	        JSeparator separator = new JSeparator();
	        separator.setBackground(SystemColor.inactiveCaption);
	        separator.setForeground(SystemColor.inactiveCaption);
	        separator.setOpaque(true);
	        separator.setBounds(805, 490, 140, 3);
	        frame.getContentPane().add(separator);
	        
	        JButton addNewPerfButton = new JButton("Add Performer");
	        addNewPerfButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new NewBandView();
	        	}
	        });
	        addNewPerfButton.setBounds(439, 467, 154, 25);
	        addNewPerfButton.setOpaque(false);
	        addNewPerfButton.setContentAreaFilled(false);
	        addNewPerfButton.setBorderPainted(false);
	        addNewPerfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        addNewPerfButton.setFont(new Font("Dialog", Font.PLAIN, 18));
	        addNewPerfButton.setForeground(SystemColor.inactiveCaption);
	        frame.getContentPane().add(addNewPerfButton);
	        
	        JSeparator separator_3 = new JSeparator();
	        separator_3.setOpaque(true);
	        separator_3.setBackground(SystemColor.inactiveCaption);
	        separator_3.setBounds(448, 490, 135, 3);
	        frame.getContentPane().add(separator_3);
	        
	        JButton btnAddEventButton = new JButton("Add Event");
	        btnAddEventButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		LocalDate eventDate = datePicker.getDate();
	        		new Event(textEventName.getText().replace("'", "''"),Float.parseFloat(textFieldPrice.getText()),User.getUserId(User.username),
	        				Venue.getVenueId(venueComboBox.getSelectedItem().toString()),eventDate.toString(),imageName,Integer.parseInt(textDuration.getText()));
	        		int EventID = Event.getEventId(textEventName.getText().replace("'","''"));
	        		for (int i = 0; i < addedPerfList.getModel().getSize(); i++) {
	        			String query = "INSERT INTO tbl_event_band VALUES("+EventID+"," + Band.getPerfID(addedPerfList.getModel().getElementAt(i).toString().replace("'","''"))+ ");";
	        			try {
	        	            Connect.updateData(query);
	        	        } catch (SQLException e) {
	        	            e.printStackTrace();
	        	        } catch (ClassNotFoundException e1) {
	        	            e1.printStackTrace();
	        	        }//BASICALLY THIS IS THE CODE BUT RIGHT NOW IT THROWS SQL EXCEPTIONS BECAUSE OF THE CONSTRAINTS 
	        		}
	        	}
	        });
	        btnAddEventButton.setOpaque(false);
	        btnAddEventButton.setForeground(SystemColor.inactiveCaption);
	        btnAddEventButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
	        btnAddEventButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnAddEventButton.setContentAreaFilled(false);
	        btnAddEventButton.setBorderPainted(false);
	        btnAddEventButton.setBounds(1005, 551, 141, 53);
	        frame.getContentPane().add(btnAddEventButton);
	        
	        
	        JLabel lblImageLabel = new JLabel("Image");
	        lblImageLabel.setOpaque(true);
	        lblImageLabel.setBackground(SystemColor.inactiveCaption);
	        lblImageLabel.setForeground(SystemColor.inactiveCaption);
	        lblImageLabel.setIcon(new ImageIcon(LoginView.class.getResource("Images/Silhouette-Rock-Concert-Wallpaper1.jpg")));
	        lblImageLabel.setBounds(0, 0, 1280, 690);
	        frame.getContentPane().add(lblImageLabel);
	    }
}
