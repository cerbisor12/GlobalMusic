
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class NewVenueView {

	private JFrame frame;
	private JTextField nameTxtField;
	private JTextField addressTxtField;
	private JTextField capacityTxtField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewVenueView window = new NewVenueView();
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
	public NewVenueView() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBounds(new Rectangle(0, 0, 432, 253));
		frame.setBounds(0,0,432,253);
		frame.getContentPane().setBackground(SystemColor.text);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		
		JLabel windowTitleLabel = new JLabel("Venue details");
		windowTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		windowTitleLabel.setForeground(SystemColor.inactiveCaption);
		windowTitleLabel.setBounds(12, 13, 99, 16);
		frame.getContentPane().add(windowTitleLabel);
		
		JLabel nameLabel = new JLabel("Name*");
		nameLabel.setForeground(SystemColor.inactiveCaption);
		nameLabel.setBounds(123, 80, 56, 16);
		frame.getContentPane().add(nameLabel);
		
		JLabel addressLabel = new JLabel("Address*");
		addressLabel.setForeground(SystemColor.inactiveCaption);
		addressLabel.setBounds(123, 147, 56, 16);
		frame.getContentPane().add(addressLabel);
		
		JLabel capacityLabel = new JLabel("Capacity*");
		capacityLabel.setForeground(SystemColor.inactiveCaption);
		capacityLabel.setBounds(123, 112, 56, 16);
		frame.getContentPane().add(capacityLabel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setForeground(SystemColor.inactiveCaption);
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setBounds(98, 204, 85, 25);
		cancelButton.setOpaque(false);
		cancelButton.setContentAreaFilled(false);
		cancelButton.setBorderPainted(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(cancelButton);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(SystemColor.inactiveCaption);
		separator.setOpaque(true);
		separator.setBounds(113, 227, 56, 3);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOpaque(true);
		separator_1.setBackground(SystemColor.inactiveCaption);
		separator_1.setBounds(250, 227, 70, 3);
		frame.getContentPane().add(separator_1);
		
		nameTxtField = new JTextField();
		nameTxtField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		nameTxtField.setBounds(191, 77, 116, 22);
		nameTxtField.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(nameTxtField);
		nameTxtField.setColumns(10);
		
		addressTxtField = new JTextField();
		addressTxtField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		addressTxtField.setBounds(191, 144, 116, 22);
		addressTxtField.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(addressTxtField);
		addressTxtField.setColumns(10);
		
		JLabel numberFormatLabel = new JLabel("Invalid number");
		numberFormatLabel.setForeground(Color.RED);
		numberFormatLabel.setBounds(191, 129, 116, 16);
		numberFormatLabel.setVisible(false);
		frame.getContentPane().add(numberFormatLabel);
		
		capacityTxtField = new JTextField();
		capacityTxtField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					Integer.parseInt(capacityTxtField.getText());
					numberFormatLabel.setVisible(false);
				}catch(NumberFormatException n) {
					capacityTxtField.setText("");
					numberFormatLabel.setVisible(true);
				}
			}
		});
		capacityTxtField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		capacityTxtField.setBackground(SystemColor.activeCaption);
		capacityTxtField.setBounds(191, 109, 116, 22);
		frame.getContentPane().add(capacityTxtField);
		capacityTxtField.setColumns(10);
		
		JButton addButton = new JButton("Add venue");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((nameTxtField.getText() == null | nameTxtField.getText().isEmpty()) | (addressTxtField.getText() == null | addressTxtField.getText().isEmpty())
						| (capacityTxtField.getText() == null | capacityTxtField.getText().toString().isEmpty())) {
					JOptionPane.showMessageDialog(null,"Please fill in all the * fields.");
				}
				else {
				new Venue(nameTxtField.getText().replace("'", "''"), addressTxtField.getText().replace("'", "''"), Integer.parseInt(capacityTxtField.getText()));
				JOptionPane.showMessageDialog(null, "Venue added.");
				DefaultComboBoxModel model = new DefaultComboBoxModel(Venue.getVenueList().toArray());
				model.insertElementAt("-Add Venue-",0);
				EventOrganizerView.venueComboBox.setModel(model);
				EventOrganizerView.venueComboBox.setSelectedIndex(model.getSize()-1);
				frame.dispose();
				}
			}
		});
		addButton.setBounds(237, 205, 97, 25);
		addButton.setForeground(SystemColor.inactiveCaption);
		addButton.setOpaque(false);
        addButton.setContentAreaFilled(false);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setBorderPainted(false);
		frame.getContentPane().add(addButton);

		JLabel backgroundLabel = new JLabel("New label");
		backgroundLabel.setIcon(new ImageIcon(NewBandView.class.getResource("/Images/rsz_silhouette-rock-concert-wallpaper1.jpg")));
		backgroundLabel.setBounds(0, 0, 432, 253);
		frame.getContentPane().add(backgroundLabel);
		
	}
}
