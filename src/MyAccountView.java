import java.awt.Color;
import java.awt.Cursor;
import javax.swing.*;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.border.MatteBorder;

/**
 * Class for viewing and editing account details.
 * @author x64
 *
 */

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
	private CreditCardIconsPanel cardIconPanel;
	public JButton cancelButton;
	public JSeparator cancelBtnSeparator;



    /**
	 * Create the window
	 */
	public MyAccountView() {
		this.setBounds(250,50,1000,550);
		this.setOpaque(false);
		this.setLayout(null);

		User user = new User();

		JLabel existDetailsLabel = new JLabel("Existing Details");
		existDetailsLabel.setForeground(SystemColor.inactiveCaption);
		existDetailsLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
		existDetailsLabel.setBounds(35, 92, 142, 25);
		this.add(existDetailsLabel);
		
		JComboBox<String> titleComboBox = new JComboBox<>();
		titleComboBox.setBackground(SystemColor.activeCaption);
		titleComboBox.setToolTipText("");
		titleComboBox.setEditable(false);
		titleComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Mr.", "Ms."}));
		titleComboBox.setBounds(118, 159, 47, 22);
		this.add(titleComboBox);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		firstNameTextField.setBackground(SystemColor.activeCaption);
		firstNameTextField.setBounds(116, 200, 180, 30);
		firstNameTextField.setColumns(10);
		this.add(firstNameTextField);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setForeground(SystemColor.inactiveCaption);
		firstNameLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		firstNameLabel.setBounds(35, 207, 69, 16);
		this.add(firstNameLabel);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		lastNameTextField.setBackground(SystemColor.activeCaption);
		lastNameTextField.setBounds(432, 200, 180, 30);
		lastNameTextField.setColumns(10);
		this.add(lastNameTextField);

		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setForeground(SystemColor.inactiveCaption);
		lastNameLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		lastNameLabel.setBounds(351, 207, 69, 16);
		this.add(lastNameLabel);
		
		address1TextField = new JTextField();
		address1TextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		address1TextField.setBackground(SystemColor.activeCaption);
		address1TextField.setBounds(116, 241, 180, 30);
		address1TextField.setColumns(10);
		this.add(address1TextField);

		
		JLabel address1Label = new JLabel("Address 1");
		address1Label.setBounds(35, 250, 69, 16);
		address1Label.setForeground(SystemColor.inactiveCaption);
		address1Label.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(address1Label);
		
		address2TextField = new JTextField();
		address2TextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		address2TextField.setBounds(432, 241, 180, 30);
		address2TextField.setBackground(SystemColor.activeCaption);
		address2TextField.setColumns(10);
		this.add(address2TextField);

		
		JLabel address2Label = new JLabel("Address 2");
		address2Label.setForeground(SystemColor.inactiveCaption);
		address2Label.setFont(new Font("Open Sans", Font.BOLD, 13));
		address2Label.setBounds(351, 250, 69, 16);
		this.add(address2Label);
		
		townTextField = new JTextField();
		townTextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		townTextField.setBackground(SystemColor.activeCaption);
		townTextField.setBounds(116, 284, 180, 30);
		townTextField.setColumns(10);
		this.add(townTextField);
		
		JLabel townLabel = new JLabel("Town");
		townLabel.setBounds(70, 291, 47, 16);
		townLabel.setForeground(SystemColor.inactiveCaption);
		townLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(townLabel);
		
		postcodeTextField = new JTextField();
		postcodeTextField.setBounds(432, 284, 180, 30);
		postcodeTextField.setBorder(new MatteBorder(3,3,3,3,SystemColor.activeCaption));
		postcodeTextField.setBackground(SystemColor.activeCaption);
		postcodeTextField.setColumns(10);
		this.add(postcodeTextField);
		
		JLabel postcodeLabel = new JLabel("Postcode");
		postcodeLabel.setBounds(360, 291, 60, 16);
		postcodeLabel.setForeground(SystemColor.inactiveCaption);
		postcodeLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(postcodeLabel);

		JLabel lblEmailInvalid = new JLabel("Email already exists");
		lblEmailInvalid.setForeground(Color.RED);
		lblEmailInvalid.setBounds(118, 353, 162, 16);
		this.add(lblEmailInvalid);
		lblEmailInvalid.setVisible(false);
		
		emailTextField = new JTextField();
		emailTextField.setBorder(new MatteBorder(3, 3, 3, 3,SystemColor.activeCaption));
		emailTextField.setBounds(116, 325, 180, 30);
		emailTextField.setBackground(SystemColor.activeCaption);
		emailTextField.setColumns(10);
		/**
		 * Focus listener to check the format of the email address.
		 */
		emailTextField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			public void focusLost(FocusEvent e) {
				String email = emailTextField.getText();
				//Regex source: emailregex.com
				Pattern emailRegex = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)" +
						"*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
						"[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
						"[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
						"(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
						"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");

				if(!emailRegex.matcher(email).matches()){
					lblEmailInvalid.setText("Invalid email address");
					lblEmailInvalid.setVisible(true);
					emailTextField.setText("");
				}
				else if (user.checkFieldInDB("Email",email.replace("'", "''"))) {
					lblEmailInvalid.setText("Email already exists");
					lblEmailInvalid.setVisible(true);
					emailTextField.setText("");
				}
				else {lblEmailInvalid.setVisible(false);}

			}
		});
		this.add(emailTextField);
		
		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setBounds(66, 330, 70, 20);
		emailLabel.setForeground(SystemColor.inactiveCaption);
		emailLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(emailLabel);
		
		phoneNoTextField = new JTextField();
		phoneNoTextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		phoneNoTextField.setBounds(432, 327, 180, 30);
		phoneNoTextField.setBackground(SystemColor.activeCaption);
		phoneNoTextField.setColumns(10);
		this.add(phoneNoTextField);

		
		JLabel phoneLabel = new JLabel("Phone No");
		phoneLabel.setBounds(360, 332, 69, 20);
		phoneLabel.setForeground(SystemColor.inactiveCaption);
		phoneLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(phoneLabel);

		JLabel lblInvalidCardNumber = new JLabel("Invalid Card Number");
		lblInvalidCardNumber.setForeground(Color.RED);
		lblInvalidCardNumber.setBounds(116, 442, 125, 14);
		this.add(lblInvalidCardNumber);
		lblInvalidCardNumber.setVisible(false);

		cardIconPanel = new CreditCardIconsPanel(116,370);
		cardIconPanel.setLocation(116, 391);
		this.add(cardIconPanel);
		
		cardNoTextField = new JTextField();
		cardNoTextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		cardNoTextField.setBounds(116, 411, 180, 30);
		cardNoTextField.setBackground(SystemColor.activeCaption);
		cardNoTextField.setColumns(10);
		/**
		 * Focus listener for checking the card number format.
		 */
		cardNoTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {}

			@Override
			public void focusLost(FocusEvent e) {
				boolean validNo = cardIconPanel.checkAndRepaint(cardNoTextField.getText());
				if (!validNo){
					lblInvalidCardNumber.setText("Invalid Card No");
					lblInvalidCardNumber.setVisible(true);
					cardNoTextField.setText("");
				}
				else{lblInvalidCardNumber.setVisible(false);}
			}
		});
		this.add(cardNoTextField);

		
		JLabel cardNoLabel = new JLabel("Card No");
		cardNoLabel.setBounds(54, 418, 63, 16);
		cardNoLabel.setForeground(SystemColor.inactiveCaption);
		cardNoLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(cardNoLabel);

		JLabel lblInvalidCVV = new JLabel("Invalid CVV");
		lblInvalidCVV.setForeground(Color.RED);
		lblInvalidCVV.setBounds(432, 421, 125, 14);
		this.add(lblInvalidCVV);
		lblInvalidCVV.setVisible(false);
		
		cvvTextField = new JTextField();
		cvvTextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		cvvTextField.setBounds(432, 390, 180, 30);
		cvvTextField.setBackground(SystemColor.activeCaption);
		cvvTextField.setColumns(10);
		/**
		 * Focus listener to check the cvvNumber format.
		 */
		cvvTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {}

			@Override
			public void focusLost(FocusEvent e) {
				try{
					Integer.parseInt(cvvTextField.getText());
					if (!(cvvTextField.getText().length()==3)){
						lblInvalidCVV.setVisible(true);
					} else {
						lblInvalidCVV.setVisible(false);
					}
				}
				catch(NumberFormatException k){
					lblInvalidCVV.setVisible(true);
					cvvTextField.setText("");}
			}
		});
		this.add(cvvTextField);

		
		JLabel cvvLabel = new JLabel("CVV");
		cvvLabel.setForeground(SystemColor.inactiveCaption);
		cvvLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		cvvLabel.setBounds(394, 397, 63, 16);
		this.add(cvvLabel);
		
		orgNameTextField = new JTextField();
		orgNameTextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		orgNameTextField.setBounds(798, 200, 180, 30);
		orgNameTextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		orgNameTextField.setBackground(SystemColor.activeCaption);
		orgNameTextField.setColumns(10);
		this.add(orgNameTextField);
		
		JLabel orgNameLabel = new JLabel("Organization Name");
		orgNameLabel.setBounds(656, 207, 142, 16);
		orgNameLabel.setForeground(SystemColor.inactiveCaption);
		orgNameLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(orgNameLabel);
		
		orgEmailTextField = new JTextField();
		orgEmailTextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		orgEmailTextField.setBounds(798, 241, 180, 30);
		orgEmailTextField.setBackground(SystemColor.activeCaption);
		orgEmailTextField.setColumns(10);
		this.add(orgEmailTextField);
		
		JLabel orgEmailLabel = new JLabel("Organization E-mail");
		orgEmailLabel.setBounds(656, 248, 142, 16);
		orgEmailLabel.setForeground(SystemColor.inactiveCaption);
		orgEmailLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(orgEmailLabel);
		
		JComboBox<String> paymentComboBox = new JComboBox<>();
		paymentComboBox.setBackground(SystemColor.activeCaption);
		paymentComboBox.setToolTipText("");
		paymentComboBox.setEditable(false);
		paymentComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"On Booking", "Monthly Invoice"}));
		paymentComboBox.setBounds(798, 284, 180, 30);
		this.add(paymentComboBox);
		
		JLabel paymentLabel = new JLabel("Payment Method");
		paymentLabel.setBounds(676, 291, 110, 16);
		paymentLabel.setForeground(SystemColor.inactiveCaption);
		paymentLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(paymentLabel);
		
		webAddressTextField = new JTextField();
		webAddressTextField.setBorder(new MatteBorder(3, 3, 3, 3,  SystemColor.activeCaption));
		webAddressTextField.setBackground(SystemColor.activeCaption);
		webAddressTextField.setBounds(798, 327, 180, 30);
		webAddressTextField.setColumns(10);
		this.add(webAddressTextField);
		
		JLabel webAddressLabel = new JLabel("Web Address");
		webAddressLabel.setBounds(698, 334, 88, 16);
		webAddressLabel.setForeground(SystemColor.inactiveCaption);
		webAddressLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		this.add(webAddressLabel);
		
		/**
		 * if statement to check the user's type. For displaying or not certain textFields and labels.
		 */
		if (user.getData(User.username,"Type").equalsIgnoreCase("Customer")) {
			orgNameTextField.setVisible(false);
			orgNameLabel.setVisible(false);
			orgEmailTextField.setVisible(false);
			orgEmailLabel.setVisible(false);
			paymentComboBox.setVisible(false);
			paymentLabel.setVisible(false);
			webAddressTextField.setVisible(false);
			webAddressLabel.setVisible(false);
		}
		
		/**
		 * First a string ArrayList to get the user's details.
		 * Secondly, populating all the textField with the existing details.
		 */
		ArrayList<String> details = user.detailsList(User.username);
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
		/**
		 * Action listener for the Save button which will eventually update the database after checking if certain textFields are not empty.
		 * The fields are checked by making a new arraylist and checking if any string contained by it equals "".
		 */
		saveButton.addActionListener(arg0 -> {
            ArrayList<JTextField> textFieldArray;
            textFieldArray = new ArrayList<>();
            textFieldArray.add(firstNameTextField);
            textFieldArray.add(lastNameTextField);
            textFieldArray.add(address1TextField);
            textFieldArray.add(townTextField);
            textFieldArray.add(postcodeTextField);
            textFieldArray.add(emailTextField);
            textFieldArray.add(cardNoTextField);
            textFieldArray.add(cvvTextField);

            if (user.getData(User.username,"Type").equalsIgnoreCase("organization")){
                textFieldArray.add(orgNameTextField);
            }
            boolean checker = true;
            for(JTextField field : textFieldArray) {
                if (field.getText().equals("")) {
                    checker = false;
                    field.setBackground(new Color(255, 228, 225));
                } else {
                    field.setBackground(SystemColor.activeCaption);
                }
            }
            if(checker){
            user.updateDetails(User.username, titleComboBox.getSelectedItem().toString(), firstNameTextField.getText().replace("'", "''"),
                    lastNameTextField.getText().replace("'", "''"), address1TextField.getText().replace("'", "''"), address2TextField.getText().replace("'", "''"),
                    townTextField.getText().replace("'", "''"), postcodeTextField.getText().replace("'", "''"), emailTextField.getText().replace("'", "''"),
                    phoneNoTextField.getText().replace("'", "''"), Long.parseLong(cardNoTextField.getText().replace("'", "''")),
                    Integer.parseInt(cvvTextField.getText().replace("'", "''")), orgNameTextField.getText().replace("'", "''"), orgEmailTextField.getText().replace("'", "''"),
                    webAddressTextField.getText(),paymentComboBox.getSelectedItem().toString());
            JOptionPane.showMessageDialog(null,"Update successful!");}
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
		
        
        JLabel invalidCardNoLabel = new JLabel("Invalid Number");
        invalidCardNoLabel.setForeground(Color.RED);
        invalidCardNoLabel.setBounds(118, 400, 100, 16);
        this.add(invalidCardNoLabel);
        invalidCardNoLabel.setVisible(false);
        
        JLabel invalidCVVLabel = new JLabel("Invalid Number");
        invalidCVVLabel.setForeground(Color.RED);
        invalidCVVLabel.setBounds(432, 400, 100, 16);
        invalidCVVLabel.setVisible(false);
        this.add(invalidCVVLabel);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(320, 457, 97, 25);
		cancelButton.setOpaque(false);
		cancelButton.setContentAreaFilled(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setForeground(SystemColor.inactiveCaption);
        cancelButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
        cancelButton.setVisible(false);
        add(cancelButton);
        
        cancelBtnSeparator = new JSeparator();
        cancelBtnSeparator.setBackground(SystemColor.inactiveCaption);
        cancelBtnSeparator.setOpaque(true);
        cancelBtnSeparator.setBounds(320, 482, 100, 3);
        cancelBtnSeparator.setVisible(false);
        add(cancelBtnSeparator);

        
	}
}