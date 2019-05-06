import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;


import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SearchEventsPanel extends JPanel {

    JTextField searchTxtField;


    public SearchEventsPanel(){
        this.setBounds(250,50,1000,550);
        this.setOpaque(false);
        this.setLayout(null);

        searchTxtField = new JTextField();
        searchTxtField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
        searchTxtField.setBackground(SystemColor.activeCaption);
        searchTxtField.setBounds(490, 22, 300, 30);
        this.add(searchTxtField);

        JLabel upcomingLabel = new JLabel("Upcoming");
        upcomingLabel.setForeground(SystemColor.inactiveCaption);
        upcomingLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        upcomingLabel.setBounds(123, 96,200 , 30);
        this.add(upcomingLabel);

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
        dateSettings.setAllowKeyboardEditing(false);
        dateSettings.setAllowEmptyDates(false);

        DatePicker datePicker = new DatePicker(dateSettings);
        dateSettings.setDateRangeLimits(LocalDate.now(),null);
        datePicker.getComponentDateTextField().setBackground(SystemColor.activeCaption);
        datePicker.getComponentToggleCalendarButton().setText("Select Date");
        datePicker.setBounds(622, 65, 168, 30);
        this.add(datePicker);

        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(123, 139, 844, 374);
        scrollPane.setBackground(Color.BLACK);
        this.add(scrollPane);
        scrollPane.setViewportView(new ResultPanel());

        JButton searchButton1 = new JButton("");
        searchButton1.setBorderPainted(false);
        searchButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton1.setIcon(new ImageIcon(LoginView.class.getResource("Images/SearchIcon.png")));
        searchButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(new ResultPanel(searchTxtField.getText().replace("'", "''")));
                upcomingLabel.setText("Search Results");            }
        });
        searchButton1.setBounds(802, 22, 30, 30);
        this.add(searchButton1);

        JButton searchButton2 = new JButton("");
        searchButton2.setBorderPainted(false);
        searchButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton2.setIcon(new ImageIcon(LoginView.class.getResource("Images/SearchIcon.png")));
        searchButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    scrollPane.setViewportView(new ResultPanel(datePicker));
                    upcomingLabel.setText("Search Results");
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        searchButton2.setBounds(802, 65, 30, 30);
        this.add(searchButton2);

        JLabel searchLabel = new JLabel("Search");
        searchLabel.setBounds(415, 29, 63, 16);
        searchLabel.setForeground(SystemColor.inactiveCaption);
        searchLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        this.add(searchLabel);

        JLabel searchDateLabel = new JLabel("or Search by date");
        searchDateLabel.setBounds(480, 71, 150, 20);
        searchDateLabel.setForeground(SystemColor.inactiveCaption);
        searchDateLabel.setFont(new Font("Open Sans", Font.PLAIN, 16));
        this.add(searchDateLabel);

    }

}
