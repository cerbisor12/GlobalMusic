import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EditEventView {

	 private JFrame frame;
	 private JTextField textDuration;
	 private JTextField textDate;
	 private JTextField textFieldPrice;
	 static JList allPerformersList, addedPerfList;
	 private JComboBox<String> comboBoxVenue;
	 private DatePicker datePicker;
	 private JLabel lblImgName;

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


	        JLabel lblInstruction = new JLabel("Choose an event to edit");
			lblInstruction.setBounds(595, 160, 350, 21);
			lblInstruction.setForeground(SystemColor.inactiveCaption);
			lblInstruction.setFont(new Font("Open Sans", Font.BOLD, 16));
			frame.getContentPane().add(lblInstruction);
	        
	        JLabel lblEventName = new JLabel("Event Name");
	        lblEventName.setBounds(496, 187, 99, 21);
	        lblEventName.setForeground(SystemColor.inactiveCaption);
	        lblEventName.setFont(new Font("Open Sans", Font.BOLD, 13));
	        frame.getContentPane().add(lblEventName);
	        
	        
	        JComboBox<String> comboBoxEventName = new JComboBox<String>();
	        comboBoxEventName.setBackground(SystemColor.activeCaption);
	        comboBoxEventName.setBounds(605, 191, 159, 20);
	        comboBoxEventName.setModel(new DefaultComboBoxModel(Event.getEventsList(User.getUserId(User.username)).toArray()));
	        comboBoxEventName.setSelectedIndex(-1);
	        //comboBoxEventName.setEditable(false);
	        frame.getContentPane().add(comboBoxEventName);
	        
	        
	        JLabel lblDate = new JLabel("Date");
	        lblDate.setForeground(SystemColor.inactiveCaption);
	        lblDate.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblDate.setBounds(496, 250, 99, 21);
	        frame.getContentPane().add(lblDate);

			DatePickerSettings dateSettings = new DatePickerSettings();
			dateSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
			dateSettings.setAllowKeyboardEditing(false);
			dateSettings.setAllowEmptyDates(false);

			datePicker = new DatePicker(dateSettings);
			dateSettings.setDateRangeLimits(LocalDate.now(),null);
			datePicker.getComponentDateTextField().setBackground(SystemColor.activeCaption);
			datePicker.getComponentToggleCalendarButton().setText("Date");
			datePicker.setBounds(604, 250, 168, 22);
			frame.getContentPane().add(datePicker);
	        
	        
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
	        
	        
	        comboBoxVenue = new JComboBox<String>();
			ArrayList venuesList = Venue.getVenueList();
			venuesList.add(0, "-Add Venue-");
	        comboBoxVenue.setBounds(987, 188, 159, 20);
	        comboBoxVenue.setBackground(SystemColor.activeCaption);
	        comboBoxVenue.setModel(new DefaultComboBoxModel(venuesList.toArray()));
	        comboBoxVenue.setSelectedIndex(-1);
	        comboBoxVenue.setEnabled(false);
	        comboBoxVenue.setEditable(false);
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

            lblImgName = new JLabel("");
            lblImgName.setBounds(987,335,159,20);
            lblImgName.setForeground(SystemColor.inactiveCaption);
            lblImgName.setFont(new Font("Open Sans",Font.PLAIN,14));
            frame.getContentPane().add(lblImgName);
	        
	        JButton btnUploadButton = new JButton("");
	        btnUploadButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Images","jpg","png","jpeg"));

                    try {
                        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                            File file = fileChooser.getSelectedFile();
                            lblImgName.setText(file.getName());

                            Files.copy(file.toPath(), Paths.get(System.getProperty("user.dir")+"/src/"+Main.EVENT_IMAGE_DIR+file.getName()),
                                    java.nio.file.StandardCopyOption.REPLACE_EXISTING,
                                    java.nio.file.StandardCopyOption.COPY_ATTRIBUTES,
                                    java.nio.file.LinkOption.NOFOLLOW_LINKS );
                        }else {
                            if(lblImgName.getText().equals("")){
                            lblImgName.setText("No file selected!");}
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

			JLabel lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(LoginView.class.getResource("Images/Logo.jpg")));
			lblLogo.setBounds(186, 583, 200, 96);
			frame.getContentPane().add(lblLogo);

			ArrayList<String> bandsAvailable = Band.getAllBands();

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
			addedPerfList = new JList();
			addedPerfList.setBackground(SystemColor.activeCaption);
			scrollPane_1.setViewportView(addedPerfList);

			JLabel performersAddedLabel = new JLabel("Added Performers");
			performersAddedLabel.setBounds(957, 377, 169, 16);
			performersAddedLabel.setFont(new Font("Open Sans", Font.BOLD, 18));
			performersAddedLabel.setForeground(SystemColor.inactiveCaption);
			frame.getContentPane().add(performersAddedLabel);

            JButton addPerformerToEvent = new JButton("Add ->");
            addPerformerToEvent.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String addedBand = (String) allPerformersList.getSelectedValue();
                    if(addedPerformersModel.contains(addedBand))
                        JOptionPane.showMessageDialog(null,"Band already added.");
                    else
                        addedPerformersModel.addElement(addedBand);
                    allPerformersModel.removeElement(addedBand);
                }
            });
            addPerformerToEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addPerformerToEvent.setFont(new Font("Dialog", Font.PLAIN, 18));
            addPerformerToEvent.setBounds(793, 447, 167, 25);
            addPerformerToEvent.setContentAreaFilled(false);
            addPerformerToEvent.setOpaque(false);
            addPerformerToEvent.setBorderPainted(false);
            addPerformerToEvent.setForeground(SystemColor.inactiveCaption);
            frame.getContentPane().add(addPerformerToEvent);

            JButton removePerformerFromEvent = new JButton("<- Remove");
            removePerformerFromEvent.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String addedBand = (String) addedPerfList.getSelectedValue();
                    if(allPerformersModel.contains(addedBand))
                        JOptionPane.showMessageDialog(null,"Band already added.");
                    else
                        allPerformersModel.addElement(addedBand);
                    addedPerformersModel.removeElement(addedBand);

                }
            });
            removePerformerFromEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            removePerformerFromEvent.setFont(new Font("Dialog", Font.PLAIN, 18));
            removePerformerFromEvent.setBounds(793, 487, 167, 25);
            removePerformerFromEvent.setContentAreaFilled(false);
            removePerformerFromEvent.setOpaque(false);
            removePerformerFromEvent.setBorderPainted(false);
            removePerformerFromEvent.setForeground(SystemColor.inactiveCaption);
            frame.getContentPane().add(removePerformerFromEvent);

            JSeparator addSeperator = new JSeparator();
            addSeperator.setBackground(SystemColor.inactiveCaption);
            addSeperator.setForeground(SystemColor.inactiveCaption);
            addSeperator.setOpaque(true);
            addSeperator.setBounds(805, 470, 140, 3);
            frame.getContentPane().add(addSeperator);

            JSeparator removeSeperator = new JSeparator();
            removeSeperator.setBackground(SystemColor.inactiveCaption);
            removeSeperator.setForeground(SystemColor.inactiveCaption);
            removeSeperator.setOpaque(true);
            removeSeperator.setBounds(805, 510, 140, 3);
            frame.getContentPane().add(removeSeperator);

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
	        
	        
	        JButton btnSaveButton = new JButton("Save Changes");
	        btnSaveButton.setOpaque(false);
	        btnSaveButton.setForeground(SystemColor.inactiveCaption);
	        btnSaveButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
	        btnSaveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnSaveButton.setContentAreaFilled(false);
	        btnSaveButton.setBorderPainted(false);
	        btnSaveButton.setBounds(967, 554, 179, 53);
	        btnSaveButton.setEnabled(false);
	        frame.getContentPane().add(btnSaveButton);
	        
	        
	        JLabel lblSavecancelEvent = new JLabel("Edit/Cancel Event");
	        lblSavecancelEvent.setForeground(SystemColor.inactiveCaption);
	        lblSavecancelEvent.setFont(new Font("Open Sans", Font.BOLD, 18));
	        lblSavecancelEvent.setBounds(776, 86, 215, 21);
	        frame.getContentPane().add(lblSavecancelEvent);

	        
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
	        btnCancel.setEnabled(false);
	        frame.getContentPane().add(btnCancel);
	        
	        
	        JSeparator separatorCancel = new JSeparator();
	        separatorCancel.setBounds(633, 592, 142, 3);
	        separatorCancel.setBackground(SystemColor.inactiveCaption);
	        separatorCancel.setForeground(SystemColor.inactiveCaption);
	        separatorCancel.setOpaque(true);
	        frame.getContentPane().add(separatorCancel);
	        

	        JLabel lblImageLabel = new JLabel("Image");
	        lblImageLabel.setForeground(Color.BLACK);
	        lblImageLabel.setIcon(new ImageIcon(LoginView.class.getResource("Images/Silhouette-Rock-Concert-Wallpaper1.jpg")));
	        lblImageLabel.setBounds(0, 0, 1297, 693);
	        frame.getContentPane().add(lblImageLabel);

			comboBoxEventName.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<String> details = Event.eventDetailsList(comboBoxEventName.getSelectedItem().toString());
					textFieldPrice.setText(details.get(2));
					comboBoxVenue.setSelectedItem(details.get(3));
					LocalDate date = null;
					try{
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						date = format.parse(details.get(4)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();}
					catch(ParseException f){
						System.out.println("wrong format");}
					datePicker.setDate(date);

					lblImgName.setText(details.get(5));
					textDuration.setText(details.get(6));

					ArrayList<String> bandsInEvent = Band.getEventBands(Integer.parseInt(details.get(0)));
					addedPerformersModel.removeAllElements();
					for(String band : bandsInEvent) {
						addedPerformersModel.addElement(band); }
					addedPerfList.setModel(addedPerformersModel);

					comboBoxVenue.setEnabled(true);
					btnSaveButton.setEnabled(true);
					addPerformerToEvent.setEnabled(true);
					btnCancel.setEnabled(true);

				}
			});

			btnSaveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int eventId = Event.getEventId(comboBoxEventName.getSelectedItem().toString());
                    String eventName = comboBoxEventName.getSelectedItem().toString().replace("'", "''");
                    float price = Float.parseFloat(textFieldPrice.getText());
                    int venueID = Venue.getVenueId(comboBoxVenue.getSelectedItem().toString());
                    String date = datePicker.getDate().toString();
                    String image = lblImgName.getText();
                    int duration = Integer.parseInt(textDuration.getText());
                    Event.updateEventDetails(eventId, eventName, price, venueID, date, image, duration);
                    for (int i = 0; i < addedPerfList.getModel().getSize(); i++) {
                        String perfomerName = addedPerfList.getModel().getElementAt(i).toString().replace("'", "''");
                        int bandID = Band.getPerfID(perfomerName);
                        String query = "INSERT IGNORE INTO tbl_event_band VALUES(" + eventId + "," + bandID + ");";
                        try {
                            Connect.updateData(query);
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    JOptionPane.showMessageDialog(null,"Update successful!");
                }
            });

	        btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int eventId = Event.getEventId(comboBoxEventName.getSelectedItem().toString());
					Booking.updateStatus(eventId,"cancelled");
                    Event.deleteEvent(eventId);
                    frame.dispose();
                    new EditEventView();


                }
            });
	    }
}
