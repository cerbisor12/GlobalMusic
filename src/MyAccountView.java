import java.awt.Color;
import java.awt.Cursor;
import javax.swing.*;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.border.MatteBorder;

public class MyAccountView extends JPanel {

	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField address1TextField;
	private JTextField address2TextField;
	private JTextField townTextField;
	private JTextField postcodeTextField;
	private JTextField emailTextField;
	private JTextField phoneNoTextField;
	private JTextField cardNoTextField;
	private JTextField cvvTextField;
	private JTextField orgNameTextField;
	private JTextField orgEmailTextField;
	private JTextField webAddressTextField;
	public JButton saveButton;


	

	/**
	 * Create the window
	 */
	public MyAccountView() {
		this.setBounds(250,50,1000,550);
		this.setOpaque(false);
		this.setLayout(null);

		
		JLabel existDetailsLabel = new JLabel("Existing Details");
		existDetailsLabel.setForeground(SystemColor.inactiveCaption);
		existDetailsLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
		existDetailsLabel.setBounds(35, 92, 142, 25);
		this.add(existDetailsLabel);
		
		JComboBox<String> titleComboBox = new JComboBox<String>();
		titleComboBox.setBackground(SystemColor.activeCaption);
		titleComboBox.setToolTipText("");
		titleComboBox.setEditable(false);
		titleComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Mr.", "Ms."}));
		titleComboBox.setBounds(118, 159, 47, 22);
		this.add(titleComboBox);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		firstNameTextField.setBackground(SystemColor.activeCaption);
		firstNameTextField.setBounds(116, 200, 162, 30);
		firstNameTextField.setColumns(10);
		this.add(firstNameTextField);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setForeground(SystemColor.inactiveCaption);
		firstNameLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		firstNameLabel.setBounds(35, 207, 69, 16);
		this.add(firstNameLabel);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		lastNameTextField.setBackground(SystemColor.activeCaption);
		lastNameTextField.setBounds(432, 200, 162, 30);
		lastNameTextField.setColumns(10);
		this.add(lastNameTextField);

		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setForeground(SystemColor.inactiveCaption);
		lastNameLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		lastNameLabel.setBounds(351, 207, 69, 16);
		this.add(lastNameLabel);
		
		address1TextField = new JTextField();
		address1TextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		address1TextField.setBackground(SystemColor.activeCaption);
		address1TextField.setBounds(116, 241, 162, 30);
		address1TextField.setColumns(10);
		this.add(address1TextField);

		
		JLabel address1Label = new JLabel("Address 1");
		address1Label.setBounds(35, 254, 69, 16);
		address1Label.setForeground(SystemColor.inactiveCaption);
		address1Label.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(address1Label);
		
		address2TextField = new JTextField();
		address2TextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		address2TextField.setBounds(432, 241, 162, 30);
		address2TextField.setBackground(SystemColor.activeCaption);
		address2TextField.setColumns(10);
		this.add(address2TextField);

		
		JLabel address2Label = new JLabel("Address 2");
		address2Label.setForeground(SystemColor.inactiveCaption);
		address2Label.setFont(new Font("Open Sans", Font.BOLD, 13));
		address2Label.setBounds(351, 244, 69, 16);
		this.add(address2Label);
		
		townTextField = new JTextField();
		townTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		townTextField.setBackground(SystemColor.activeCaption);
		townTextField.setBounds(116, 284, 162, 30);
		townTextField.setColumns(10);
		this.add(townTextField);
		
		JLabel townLabel = new JLabel("Town");
		townLabel.setBounds(70, 291, 47, 16);
		townLabel.setForeground(SystemColor.inactiveCaption);
		townLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(townLabel);
		
		postcodeTextField = new JTextField();
		postcodeTextField.setBounds(432, 284, 162, 30);
		postcodeTextField.setBorder(new MatteBorder(3,3,3,3, (Color)SystemColor.activeCaption));
		postcodeTextField.setBackground(SystemColor.activeCaption);
		postcodeTextField.setColumns(10);
		this.add(postcodeTextField);
		
		JLabel postcodeLabel = new JLabel("Postcode");
		postcodeLabel.setBounds(360, 291, 60, 16);
		postcodeLabel.setForeground(SystemColor.inactiveCaption);
		postcodeLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(postcodeLabel);
		
		emailTextField = new JTextField();
		emailTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		emailTextField.setBounds(116, 327, 162, 30);
		emailTextField.setBackground(SystemColor.activeCaption);
		emailTextField.setColumns(10);
		this.add(emailTextField);
		
		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setBounds(66, 332, 70, 20);
		emailLabel.setForeground(SystemColor.inactiveCaption);
		emailLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(emailLabel);
		
		phoneNoTextField = new JTextField();
		phoneNoTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		phoneNoTextField.setBounds(432, 327, 162, 30);
		phoneNoTextField.setBackground(SystemColor.activeCaption);
		phoneNoTextField.setColumns(10);
		this.add(phoneNoTextField);

		
		JLabel phoneLabel = new JLabel("Phone No");
		phoneLabel.setBounds(360, 332, 69, 20);
		phoneLabel.setForeground(SystemColor.inactiveCaption);
		phoneLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(phoneLabel);
		
		cardNoTextField = new JTextField();
		cardNoTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		cardNoTextField.setBounds(116, 370, 162, 30);
		cardNoTextField.setBackground(SystemColor.activeCaption);
		cardNoTextField.setColumns(10);
		this.add(cardNoTextField);

		
		JLabel cardNoLabel = new JLabel("Card No");
		cardNoLabel.setBounds(54, 377, 63, 16);
		cardNoLabel.setForeground(SystemColor.inactiveCaption);
		cardNoLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(cardNoLabel);
		
		cvvTextField = new JTextField();
		cvvTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		cvvTextField.setBounds(432, 370, 162, 30);
		cvvTextField.setBackground(SystemColor.activeCaption);
		cvvTextField.setColumns(10);
		this.add(cvvTextField);

		
		JLabel cvvLabel = new JLabel("CVV");
		cvvLabel.setForeground(SystemColor.inactiveCaption);
		cvvLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		cvvLabel.setBounds(394, 377, 63, 16);
		this.add(cvvLabel);
		
		
		
		orgNameTextField = new JTextField();
		orgNameTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		orgNameTextField.setBounds(798, 200, 162, 30);
		orgNameTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		orgNameTextField.setBackground(SystemColor.activeCaption);
		orgNameTextField.setColumns(10);
		this.add(orgNameTextField);
		
		JLabel orgNameLabel = new JLabel("Organization Name");
		orgNameLabel.setBounds(656, 207, 142, 16);
		orgNameLabel.setForeground(SystemColor.inactiveCaption);
		orgNameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.add(orgNameLabel);
		
		orgEmailTextField = new JTextField();
		orgEmailTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		orgEmailTextField.setBounds(798, 241, 162, 30);
		orgEmailTextField.setBackground(SystemColor.activeCaption);
		orgEmailTextField.setColumns(10);
		this.add(orgEmailTextField);
		
		JLabel orgEmailLabel = new JLabel("Organization E-mail");
		orgEmailLabel.setBounds(656, 248, 142, 16);
		orgEmailLabel.setForeground(SystemColor.inactiveCaption);
		orgEmailLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(orgEmailLabel);
		
		JComboBox<String> paymentComboBox = new JComboBox<String>();
		paymentComboBox.setBackground(SystemColor.activeCaption);
		paymentComboBox.setToolTipText("");
		paymentComboBox.setEditable(false);
		paymentComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"On Booking", "Monthly Invoice"}));
		paymentComboBox.setBounds(798, 284, 162, 30);
		this.add(paymentComboBox);
		
		JLabel paymentLabel = new JLabel("Payment Method");
		paymentLabel.setBounds(676, 291, 110, 16);
		paymentLabel.setForeground(SystemColor.inactiveCaption);
		paymentLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(paymentLabel);
		
		webAddressTextField = new JTextField();
		webAddressTextField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
		webAddressTextField.setBackground(SystemColor.activeCaption);
		webAddressTextField.setBounds(798, 327, 162, 30);
		webAddressTextField.setColumns(10);
		this.add(webAddressTextField);
		
		JLabel webAddressLabel = new JLabel("Web Address");
		webAddressLabel.setBounds(698, 334, 88, 16);
		webAddressLabel.setForeground(SystemColor.inactiveCaption);
		webAddressLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(webAddressLabel);
		
		if (User.getData(User.username,"Type").equalsIgnoreCase("Customer")) {
			orgNameTextField.setVisible(false);
			orgNameLabel.setVisible(false);
			orgEmailTextField.setVisible(false);
			orgEmailLabel.setVisible(false);
			paymentComboBox.setVisible(false);
			paymentLabel.setVisible(false);
			webAddressTextField.setVisible(false);
			webAddressLabel.setVisible(false);
		}
		
		ArrayList<String> details = User.detailsList(User.username);
		titleComboBox.setSelectedItem(details.get(0));
		firstNameTextField.setText(details.get(1));
		lastNameTextField.setText(details.get(2));
		address1TextField.setText(details.get(3));
		address2TextField.setText(details.get(4));
		townTextField.setText(details.get(5));
		postcodeTextField.setText(details.get(6));
		emailTextField.setText(details.get(7));
		phoneNoTextField.setText(details.get(8));
		cardNoTextField.setText(details.get(9));
		cvvTextField.setText(details.get(10));
		orgNameTextField.setText(details.get(11));
		orgEmailTextField.setText(details.get(12));
		paymentComboBox.setSelectedItem(details.get(13));
		webAddressTextField.setText(details.get(14));
		
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User.updateDetails(User.username, titleComboBox.getSelectedItem().toString(), firstNameTextField.getText().replace("'", "''"), 
						lastNameTextField.getText().replace("'", "''"), address1TextField.getText().replace("'", "''"), address2TextField.getText().replace("'", "''"), 
						townTextField.getText().replace("'", "''"), postcodeTextField.getText().replace("'", "''"), emailTextField.getText().replace("'", "''"), 
						phoneNoTextField.getText().replace("'", "''"), Long.parseLong(cardNoTextField.getText().replace("'", "''")), 
						Integer.parseInt(cvvTextField.getText().replace("'", "''")), orgNameTextField.getText().replace("'", "''"), orgEmailTextField.getText().replace("'", "''"),
						webAddressTextField.getText(),paymentComboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null,"Update succesfull!");
			}
		});
		saveButton.setBounds(497, 457, 97, 25);
		saveButton.setOpaque(false);
		saveButton.setContentAreaFilled(false);
        saveButton.setBorderPainted(false);
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.setForeground(SystemColor.inactiveCaption);
        saveButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
        this.add(saveButton);
		
		JSeparator saveSeparator = new JSeparator();
		saveSeparator.setBackground(SystemColor.inactiveCaption);
		saveSeparator.setOpaque(true);
		saveSeparator.setBounds(500, 482, 80, 3);
		this.add(saveSeparator);
		
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(72, 162, 34, 16);
		titleLabel.setForeground(SystemColor.inactiveCaption);
		titleLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(titleLabel);

        JLabel emailInUseLabel = new JLabel("Email already exists");
        emailInUseLabel.setForeground(Color.RED);
        emailInUseLabel.setBounds(116, 355, 162, 16);
        this.add(emailInUseLabel);
        emailInUseLabel.setVisible(false);
        
        JLabel invalidCardNoLabel = new JLabel("Invalid Number");
        invalidCardNoLabel.setForeground(Color.RED);
        invalidCardNoLabel.setBounds(118, 400, 100, 16);
        this.add(invalidCardNoLabel);
        invalidCardNoLabel.setVisible(false);
        
        JLabel invalidCVVLabel = new JLabel("Invalid Number");
        invalidCVVLabel.setForeground(Color.RED);
        invalidCVVLabel.setBounds(432, 400, 100, 16);
        this.add(invalidCVVLabel);
        invalidCVVLabel.setVisible(false);
        



	}

}