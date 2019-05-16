import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.awt.SystemColor;
import java.util.Locale;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class for editing or canceling an event.
 *
 */
public class EditEventView {

    private JFrame frame;
    private JTextField textFieldDuration;
    private JTextField textFieldPrice;
    static JList allPerformersList, addedPerfList;
    private JComboBox<String> comboBoxVenue;
    private DatePicker datePicker;
    private JLabel lblImgName;



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

        User user = new User();
        Event event = new Event();
        Band band = new Band();
        Booking booking = new Booking();

        JButton btnExitButton = new JButton("X");
        /**
         * Listener for exiting the application after confirmation.
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
         * Listener for minimizing the application.
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





        JButton btnAddNewEvent = new JButton("Add New Event");
        /**
         * Listener for the Add Event button.
         */
        btnAddNewEvent.addActionListener(e -> {
            new EventOrganizerView();
            frame.setVisible(false);
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
        /**
         * Logout Button listener, opens the Login window.
         */
        btnLogOut.addActionListener(arg0 -> {
            new LoginView();
            frame.setVisible(false);
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
        
        
        /**
         * Combobox populated with all the events made available by a certain organizer.
         */
        JComboBox<String> comboBoxEventName = new JComboBox<>();
        comboBoxEventName.setBackground(SystemColor.activeCaption);
        comboBoxEventName.setBounds(605, 191, 159, 20);
        comboBoxEventName.setModel(new DefaultComboBoxModel(Event.getFutureEventsOrganizer(user.getUserId(User.username)).toArray()));
        comboBoxEventName.setSelectedIndex(-1);
        comboBoxEventName.setEditable(false);
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
        datePicker.setLocale(Locale.UK);
        datePicker.getComponentDateTextField().setBackground(SystemColor.activeCaption);
        datePicker.getComponentToggleCalendarButton().setText("Date");
        datePicker.setBounds(604, 250, 168, 22);
        frame.getContentPane().add(datePicker);


        JLabel lblDuration = new JLabel("Duration");
        lblDuration.setForeground(SystemColor.inactiveCaption);
        lblDuration.setFont(new Font("Open Sans", Font.BOLD, 13));
        lblDuration.setBounds(496, 312, 99, 21);
        frame.getContentPane().add(lblDuration);

        JLabel lblInvalidNumber = new JLabel("Invalid Number");
        lblInvalidNumber.setForeground(Color.RED);
        lblInvalidNumber.setBounds(605, 333, 159, 14);
        frame.getContentPane().add(lblInvalidNumber);
        lblInvalidNumber.setVisible(false);

        textFieldDuration = new JTextField();
        textFieldDuration.setColumns(10);
        textFieldDuration.setBorder(new MatteBorder(2, 2, 2, 2, SystemColor.activeCaption));
        textFieldDuration.setBackground(SystemColor.activeCaption);
        textFieldDuration.setBounds(605, 313, 159, 20);
        /**
         * focus listener for duration field format. only integers accepted.
         */
        textFieldDuration.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    Integer.parseInt(textFieldDuration.getText());
                    lblInvalidNumber.setVisible(false);
                }catch(NumberFormatException f){
                    lblInvalidNumber.setVisible(true);
                    textFieldDuration.setText("");
                }
            }
        } );
        frame.getContentPane().add(textFieldDuration);


        JLabel lblVenue = new JLabel("Venue");
        lblVenue.setForeground(SystemColor.inactiveCaption);
        lblVenue.setFont(new Font("Open Sans", Font.BOLD, 13));
        lblVenue.setBounds(878, 187, 99, 21);
        frame.getContentPane().add(lblVenue);


        /**
         * Combobox populated with available venues, with 'add venue' as the default option.
         * If selected, opens the newVenueView window.
         */
        comboBoxVenue = new JComboBox<>();
        ArrayList venuesList = Venue.getVenueList();
        venuesList.add(0, "-Add Venue-");
        comboBoxVenue.setBounds(987, 188, 159, 20);
        comboBoxVenue.setBackground(SystemColor.activeCaption);
        comboBoxVenue.setModel(new DefaultComboBoxModel(venuesList.toArray()));
        comboBoxVenue.setSelectedIndex(-1);
        comboBoxVenue.setEnabled(false);
        comboBoxVenue.setEditable(false);
        comboBoxVenue.addActionListener(e -> {
            Object selected = comboBoxVenue.getSelectedItem();
            if (selected.toString().equals("-Add Venue-"))
                new NewVenueView();
        });
        frame.getContentPane().add(comboBoxVenue);


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
        /**
         * Listener for uploading a new image. Only certain formats accepted, "jpg","png","jpeg".
         */
        btnUploadButton.addActionListener(arg0 -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images","jpg","png","jpeg");
            fileChooser.setFileFilter(filter);

            try {
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    if(filter.accept(file)){
                    lblImgName.setText(file.getName());

                    Files.copy(file.toPath(), Paths.get(System.getProperty("user.dir")+"/"+Main.EVENT_IMAGE_DIR+file.getName()),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING,
                            java.nio.file.StandardCopyOption.COPY_ATTRIBUTES,
                            java.nio.file.LinkOption.NOFOLLOW_LINKS );}
                    else{JOptionPane.showMessageDialog(null,"Invalid File Type");}
                }else {
                    if(lblImgName.getText().equals("")){
                        lblImgName.setText("No file selected!");}
                }
            }catch(Exception e1) {
                e1.printStackTrace();
            }
        });
        btnUploadButton.setBorderPainted(false);
        btnUploadButton.setIcon(new ImageIcon(Main.IMAGE_DIR+"UploadButton.jpg"));
        btnUploadButton.setBounds(987, 312, 159, 23);
        btnUploadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(btnUploadButton);


        JLabel lblPerformers = new JLabel("Available Performers");
        lblPerformers.setFont(new Font("Dialog", Font.BOLD, 18));
        lblPerformers.setForeground(SystemColor.inactiveCaption);
        lblPerformers.setBounds(605, 373, 189, 21);
        frame.getContentPane().add(lblPerformers);

        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(Main.IMAGE_DIR+"Logo.jpg"));
        lblLogo.setBounds(186, 583, 200, 96);
        frame.getContentPane().add(lblLogo);

        ArrayList<String> bandsAvailable = band.getAllBands();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(605, 407, 189, 140);
        frame.getContentPane().add(scrollPane);

        /**
         * Two lists, one for all performers available and the other for performers added to the event. 
         */
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
        /**
         * Listener for adding an artist to the event.
         */
        addPerformerToEvent.addActionListener(e -> {
            if(!allPerformersList.isSelectionEmpty()) {
                String addedBand = (String) allPerformersList.getSelectedValue();
                if (addedPerformersModel.contains(addedBand))
                    JOptionPane.showMessageDialog(null, "Band already added.");
                else{
                    addedPerformersModel.addElement(addedBand); }
            }
        });
        addPerformerToEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addPerformerToEvent.setFont(new Font("Dialog", Font.PLAIN, 18));
        addPerformerToEvent.setBounds(833, 439, 99, 25);
        addPerformerToEvent.setContentAreaFilled(false);
        addPerformerToEvent.setOpaque(false);
        addPerformerToEvent.setBorderPainted(false);
        addPerformerToEvent.setForeground(SystemColor.inactiveCaption);
        addPerformerToEvent.setEnabled(false);
        frame.getContentPane().add(addPerformerToEvent);

        JButton removePerformerFromEvent = new JButton("<- Remove");
        /**
         * Listener for removing the artist from the already added list.
         */
        removePerformerFromEvent.addActionListener(e -> {
            if(!addedPerfList.isSelectionEmpty()){
                String addedBand = (String) addedPerfList.getSelectedValue();
                addedPerformersModel.removeElement(addedBand);}
        });
        removePerformerFromEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removePerformerFromEvent.setFont(new Font("Dialog", Font.PLAIN, 18));
        removePerformerFromEvent.setBounds(793, 487, 167, 25);
        removePerformerFromEvent.setContentAreaFilled(false);
        removePerformerFromEvent.setOpaque(false);
        removePerformerFromEvent.setBorderPainted(false);
        removePerformerFromEvent.setForeground(SystemColor.inactiveCaption);
        removePerformerFromEvent.setEnabled(false);
        frame.getContentPane().add(removePerformerFromEvent);

        JSeparator addSeparator = new JSeparator();
        addSeparator.setBackground(SystemColor.inactiveCaption);
        addSeparator.setForeground(SystemColor.inactiveCaption);
        addSeparator.setOpaque(true);
        addSeparator.setBounds(845, 460, 70, 3);
        frame.getContentPane().add(addSeparator);

        JSeparator removeSeparator = new JSeparator();
        removeSeparator.setBackground(SystemColor.inactiveCaption);
        removeSeparator.setForeground(SystemColor.inactiveCaption);
        removeSeparator.setOpaque(true);
        removeSeparator.setBounds(822, 510, 110, 3);
        frame.getContentPane().add(removeSeparator);

        JButton addNewPerfButton = new JButton("Add Performer");
        /**
         * Listener for adding a new performer to the database, opens NewBandView window.
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


        JLabel lblSaveCancelEvent = new JLabel("Edit/Cancel Event");
        lblSaveCancelEvent.setForeground(SystemColor.inactiveCaption);
        lblSaveCancelEvent.setFont(new Font("Open Sans", Font.BOLD, 18));
        lblSaveCancelEvent.setBounds(776, 86, 215, 21);
        frame.getContentPane().add(lblSaveCancelEvent);


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
        lblImageLabel.setIcon(new ImageIcon(Main.IMAGE_DIR+"Silhouette-Rock-Concert-Wallpaper1.jpg"));
        lblImageLabel.setBounds(0, 0, 1297, 693);
        frame.getContentPane().add(lblImageLabel);

        /**
         * Combobox listener for populating the fields with the selected event's details by creating an arrayList and extracting values one by one.
         */
        comboBoxEventName.addActionListener(e -> {
            int eventID = event.getEventId(comboBoxEventName.getSelectedItem().toString().replace("'","''"));
            ArrayList<String> details = event.eventDetailsList(eventID);
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
            textFieldDuration.setText(details.get(6));

            ArrayList<String> bandsInEvent = band.getEventBands(Integer.parseInt(details.get(0)));
            addedPerformersModel.removeAllElements();
            for(String band1 : bandsInEvent) {
                addedPerformersModel.addElement(band1); }
            addedPerfList.setModel(addedPerformersModel);

            comboBoxVenue.setEnabled(true);
            btnSaveButton.setEnabled(true);
            addPerformerToEvent.setEnabled(true);
            btnCancel.setEnabled(true);
            removePerformerFromEvent.setEnabled(true);
            addPerformerToEvent.setEnabled(true);

        });

        /**
         * Listener for updating event details by querying the database.
         */
        btnSaveButton.addActionListener(arg0 -> {
            if (checkEmptyFields()) {

                String eventName = comboBoxEventName.getSelectedItem().toString().replace("'", "''");
                int eventId = event.getEventId(eventName);
                float price = Float.parseFloat(textFieldPrice.getText());
                int venueID = Venue.getVenueId(comboBoxVenue.getSelectedItem().toString().replace("'", "''"));
                String date = datePicker.getDate().toString();
                String image = lblImgName.getText();
                int duration = Integer.parseInt(textFieldDuration.getText());
                event.updateEventDetails(eventId, eventName, price, venueID, date, image, duration);
                try{
                	String query = "DELETE FROM tbl_event_band WHERE EventID = " + eventId + "; " ;
                	Connect.updateData(query);
                for (int i = 0; i < addedPerfList.getModel().getSize(); i++) {
                    String performerName = addedPerfList.getModel().getElementAt(i).toString().replace("'", "''");
                    int bandID = band.getPerfID(performerName);
                    
                    String query2 = "INSERT IGNORE INTO tbl_event_band VALUES(" + eventId + "," + bandID + ");";
                    Connect.updateData(query2);}
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(null, "Update successful!");

        });

        /**
         * Listener for canceling the event. Opens the EditEventView.
         */
        btnCancel.addActionListener(e -> {
            int eventId = event.getEventId(comboBoxEventName.getSelectedItem().toString());
            List<List<String>> customerInfo = booking.getCustomerInfo(eventId);

            for (List cust : customerInfo){
                String email = cust.get(0).toString();
                String title = cust.get(1).toString();
                String lName = cust.get(2).toString();
                new SendMail(title,lName,email).sendCancellationMail();
            }

            booking.updateStatus(eventId,"cancelled");
            event.deleteEvent(eventId);
            JOptionPane.showMessageDialog(null,"Event cancelled.");
            frame.dispose();
            new EditEventView();


        });
    }

    /**
     * private method for checking for empty fields.
     * @return false if any 'if' is false.
     * else return true.
     */
    private boolean checkEmptyFields(){
        if (textFieldPrice.getText().equals("") || textFieldDuration.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please fill all fields!");
            return false;
        }else if (datePicker.getDate().equals(LocalDate.now())){
            JOptionPane.showMessageDialog(null,"Please choose a future date!");
            return false;
        }else if (addedPerfList.getModel().getSize()==0){
            JOptionPane.showMessageDialog(null,"No Performers Added!");
            return false;
        }else{return true;}

    }
}
