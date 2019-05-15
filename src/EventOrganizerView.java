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
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.SystemColor;
import java.util.Locale;
import java.util.Objects;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JList;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

/**
 * Class for displaying the event organizer type of user's main window.
 * @author x64
 *
 */
public class EventOrganizerView {

	 private JFrame frame;
	 private JTextField textEventName;
	 private JTextField textDuration;
	 private JTextField textFieldPrice;
	 static JComboBox<String> venueComboBox;
	 static JList allPerformersList;
	 private String imageName = "";
	 private JLabel lblImageName;
	 private DatePicker datePicker;
	 private JList addedPerfList;


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


	        User user = new User();
	        Event event = new Event();
	        Band band = new Band();

	        JButton btnExitButton = new JButton("X");
	        /**
	         * Exit button listener for exiting the application after confirmation.
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


	        JButton btnEditEvent = new JButton("Edit/Cancel Event");
	        btnEditEvent.setBounds(10, 164, 202, 60);
	        btnEditEvent.setForeground(SystemColor.inactiveCaption);
	        btnEditEvent.setFont(new Font("Open Sans", Font.PLAIN, 20));
	        btnEditEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnEditEvent.setOpaque(false);
	        btnEditEvent.setBorderPainted(false);
	        btnEditEvent.setContentAreaFilled(false);
	        /**
	         * Listener for opening the EditEvent frame.
	         */
	        btnEditEvent.addActionListener(arg0 -> {
				new EditEventView();
				frame.setVisible(false);
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
	        /**
	         * Listener for the logout button which opens the login frame.
	         */
	        btnLogOut.addActionListener(arg0 -> {
				new LoginView();
				frame.setVisible(false);
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
	        textEventName.setBorder(new MatteBorder(2, 2, 2, 2, SystemColor.activeCaption));
	        frame.getContentPane().add(textEventName);
	        textEventName.setColumns(10);

	        DatePickerSettings dateSettings = new DatePickerSettings();
	        dateSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
	        dateSettings.setAllowKeyboardEditing(false);
	        dateSettings.setAllowEmptyDates(false);

	        datePicker = new DatePicker(dateSettings);
	        datePicker.setLocale(Locale.UK);
	        datePicker.setDateToToday();
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
			textDuration.setBorder(new MatteBorder(2, 2, 2, 2, SystemColor.activeCaption));
			textDuration.setBackground(SystemColor.activeCaption);
			textDuration.setBounds(605, 313, 159, 20);
			/**
			 * Focus listener to check the format of the duration textField. Only integers accepted.
			 */
			textDuration.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) { }

				@Override
				public void focusLost(FocusEvent e) {
					try{
						Integer.parseInt(textDuration.getText());
						durationFormatLabel.setVisible(false);
					}catch(NumberFormatException f){
						durationFormatLabel.setVisible(true);
						textDuration.setText("");
					}
				}
			} );
			frame.getContentPane().add(textDuration);


	        JLabel lblVenue = new JLabel("Venue");
	        lblVenue.setForeground(SystemColor.inactiveCaption);
	        lblVenue.setFont(new Font("Open Sans", Font.BOLD, 13));
	        lblVenue.setBounds(878, 187, 99, 21);
	        frame.getContentPane().add(lblVenue);


	        /**
	         * Combobox populated with the available venues.
	         * First option set to "Add Venue" which when clicked opens the newVenue frame for adding a new location.
	         */
	        venueComboBox = new JComboBox<>();
	        ArrayList venuesList = Venue.getVenueList();
	        venuesList.add(0, "-Add Venue-");
	        venueComboBox.setModel(new DefaultComboBoxModel(venuesList.toArray()));
	        venueComboBox.setBounds(987, 188, 159, 20);
	        venueComboBox.setEditable(false);
	        venueComboBox.setBackground(SystemColor.activeCaption);
	        venueComboBox.addActionListener(e -> {
				Object selected = venueComboBox.getSelectedItem();
				if (selected.toString().equals("-Add Venue-"))
					new NewVenueView();
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
	        textFieldPrice.setBorder(new MatteBorder(2, 2, 2, 2, SystemColor.activeCaption));
	        textFieldPrice.setBackground(SystemColor.activeCaption);
	        textFieldPrice.setBounds(987, 251, 159, 20);
	        /**
	         * Focus listener for the price textField format, only integers of floats accepted.
	         */
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
	        /**
	         * Listener for adding an image to the event.Restricted to .jpg, .png and .jpeg formats.
	         */
	        btnUploadButton.addActionListener(arg0 -> {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Images","jpg","png","jpeg"));

				try {
					if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
						File file = fileChooser.getSelectedFile();
						imageName = file.getName();
						lblImageName.setText(imageName);

						Files.copy(file.toPath(),Paths.get(System.getProperty("user.dir")+"/"+Main.EVENT_IMAGE_DIR+file.getName()),
								StandardCopyOption.REPLACE_EXISTING,
								StandardCopyOption.COPY_ATTRIBUTES,
								LinkOption.NOFOLLOW_LINKS );
					}else {
if(lblImageName.getText().equals("")){
lblImageName.setText("No file selected!");}
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			});
	        btnUploadButton.setBorderPainted(false);
	        btnUploadButton.setIcon(new ImageIcon(Main.IMAGE_DIR+"UploadButton.jpg"));//right dir
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
	        lblLogo.setIcon(new ImageIcon(Main.IMAGE_DIR+"Logo.jpg"));
	        lblLogo.setBounds(186, 583, 200, 96);
	        frame.getContentPane().add(lblLogo);

	        ArrayList<String> bandsAvailable = band.getAllBands();

	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(605, 407, 189, 140);
	        frame.getContentPane().add(scrollPane);

	        /**
	         * Default model for populating the JList with available performers.
	         */
	        DefaultListModel allPerformersModel = new DefaultListModel();
	        for(int i = 0; i < bandsAvailable.size(); i++) {
	        	allPerformersModel.addElement(bandsAvailable.get(i));
	        }

	        /**
	         * JList for all the performers available.
	         */
	        allPerformersList = new JList(allPerformersModel);
	        scrollPane.setViewportView(allPerformersList);
	        allPerformersList.setBackground(SystemColor.activeCaption);

	        JScrollPane scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBounds(957, 407, 189, 140);
	        frame.getContentPane().add(scrollPane_1);

	        /**
	         * Default model for the added performers JList.
	         */
	        DefaultListModel addedPerformersModel = new DefaultListModel();

	        addedPerfList = new JList(addedPerformersModel);
	        addedPerfList.setBackground(SystemColor.activeCaption);
	        scrollPane_1.setViewportView(addedPerfList);

	        JLabel performersAddedLabel = new JLabel("Added Performers");
	        performersAddedLabel.setBounds(957, 377, 169, 16);
	        performersAddedLabel.setFont(new Font("Open Sans", Font.BOLD, 18));
	        performersAddedLabel.setForeground(SystemColor.inactiveCaption);
	        frame.getContentPane().add(performersAddedLabel);

	        JButton addPerformerToEvent = new JButton("Add ->");
	        /**
	         * Listener for adding a performer to the 'added performers' list.
	         */
	        addPerformerToEvent.addActionListener(e -> {
				if (!allPerformersList.isSelectionEmpty()) {
					String addedBand = (String) allPerformersList.getSelectedValue();
					if (addedPerformersModel.contains(addedBand))
						JOptionPane.showMessageDialog(null, "Band already added.");
					else{
						addedPerformersModel.addElement(addedBand); }
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
            /**
             * Listener for removing a performer from the 'added performers' list.
             */
            removePerformerFromEvent.addActionListener(e -> {
				if(!addedPerfList.isSelectionEmpty()) {
					String addedBand = (String) addedPerfList.getSelectedValue();
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

	        JSeparator addSeparator = new JSeparator();
	        addSeparator.setBackground(SystemColor.inactiveCaption);
	        addSeparator.setForeground(SystemColor.inactiveCaption);
	        addSeparator.setOpaque(true);
	        addSeparator.setBounds(805, 470, 140, 3);
	        frame.getContentPane().add(addSeparator);

            JSeparator removeSeparator = new JSeparator();
            removeSeparator.setBackground(SystemColor.inactiveCaption);
            removeSeparator.setForeground(SystemColor.inactiveCaption);
            removeSeparator.setOpaque(true);
            removeSeparator.setBounds(805, 510, 140, 3);
            frame.getContentPane().add(removeSeparator);

	        JButton addNewPerfButton = new JButton("Add Performer");
	        /**
	         * Listener for adding a new performer which will open the newBandView frame.
	         */
	        addNewPerfButton.addActionListener(e -> new NewBandView());
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
	        /**
	         * Listener for adding a new event.
	         * After all the required fields are filled in, the database will be updated with the new details.
	         * 
	         */
	        btnAddEventButton.addActionListener(arg0 -> {
				if (checkEmptyFields()) {
					LocalDate eventDate = datePicker.getDate();
					new Event(textEventName.getText().replace("'", "''"), Float.parseFloat(textFieldPrice.getText()), user.getUserId(User.username),
							Venue.getVenueId(venueComboBox.getSelectedItem().toString().replace("'", "''")), eventDate.toString(), imageName, Integer.parseInt(textDuration.getText()));
					int EventID = event.getEventId(textEventName.getText().replace("'", "''"));
					for (int i = 0; i < addedPerfList.getModel().getSize(); i++) {
						String query = "INSERT INTO tbl_event_band VALUES(" + EventID + "," + band.getPerfID(addedPerfList.getModel().getElementAt(i).toString().replace("'", "''")) + ");";
						try {
							Connect.updateData(query);

						} catch (SQLException | ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
					JOptionPane.showMessageDialog(null,"Event Added successfully");
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
	        lblImageLabel.setIcon(new ImageIcon(Main.IMAGE_DIR+"Silhouette-Rock-Concert-Wallpaper1.jpg"));
	        lblImageLabel.setBounds(0, 0, 1280, 690);
	        frame.getContentPane().add(lblImageLabel);
	    }

	    /**
	     * private method to check for empty fields in the process of adding a new event.
	     * @return false if any of the 'ifs' is false.
	     * else return true.
	     * 
	     */
	private boolean checkEmptyFields(){
		if (textFieldPrice.getText().equals("") || textDuration.getText().equals("") || textEventName.getText().equals("")){
			JOptionPane.showMessageDialog(null,"Please fill all fields!");
			return false;
		}else if (datePicker.getDate().equals(LocalDate.now())){
			JOptionPane.showMessageDialog(null,"Please choose a future date!");
			return false;
		}else if (lblImageName.getText().equalsIgnoreCase("No file selected!") || lblImageName.getText().equals("")){
			JOptionPane.showMessageDialog(null,"No image uploaded!");
			return false;
		}else if (Objects.requireNonNull(venueComboBox.getSelectedItem()).toString().equals("-Add Venue-") || venueComboBox.getSelectedItem() == null){
			JOptionPane.showMessageDialog(null,"No Venue selected!");
			return false;
		}else if (addedPerfList.getModel().getSize()==0){
			JOptionPane.showMessageDialog(null,"No Performers Added!");
			return false;
		}else{return true;}

	}


}
