import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 * Class for changing the user's password.
 *
 */
public class ChangePasswordView extends JPanel {


	private JPasswordField oldPassField;
	private JPasswordField newPassField;
	private JPasswordField confPassField;
    private User user = new User();
    private String password = user.getData(User.username,"Pass");

	/**
	 * Create the window
	 */
	public ChangePasswordView() {
        this.setBounds(250,50,1000,550);
        this.setOpaque(false);
        this.setLayout(null);


		
		JLabel changePassLabel = new JLabel("Account Security");
		changePassLabel.setForeground(SystemColor.inactiveCaption);
		changePassLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
		changePassLabel.setBounds(122, 130, 197, 38);
		this.add(changePassLabel);

        
        JLabel oldPassLabel = new JLabel("Old Password");
        oldPassLabel.setForeground(SystemColor.inactiveCaption);
        oldPassLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
        oldPassLabel.setBounds(151, 225, 87, 16);
        this.add(oldPassLabel);
        
        JLabel incorrectPassLabel = new JLabel("* incorrect password");
        incorrectPassLabel.setForeground(Color.RED);
        incorrectPassLabel.setBounds(264, 203, 124, 16);
        this.add(incorrectPassLabel);
        incorrectPassLabel.setVisible(false);
        
        oldPassField = new JPasswordField();
        /**
         * Focus listener for checking if the old password is correct.
         */
        oldPassField.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {}
        	
        	@Override
        	public void focusLost(FocusEvent e) {
        	    password = user.getData(User.username,"Pass");
        		if (!(password.equals(String.valueOf(oldPassField.getPassword()))))
        			incorrectPassLabel.setVisible(true);
        		else
        			incorrectPassLabel.setVisible(false);
        	}
        });
        oldPassField.setBounds(250, 222, 152, 22);
        oldPassField.setBorder(new MatteBorder(3, 3, 3, 3, SystemColor.activeCaption));
        oldPassField.setBackground(SystemColor.activeCaption);
        this.add(oldPassField);
        
        JLabel newPassLabel = new JLabel("New Password");
        newPassLabel.setBounds(144, 270, 94, 16);
        newPassLabel.setForeground(SystemColor.inactiveCaption);
        newPassLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
        this.add(newPassLabel);
        
        JLabel lblBetween5and16 = new JLabel("between 5 and 16 characters");
        lblBetween5and16.setForeground(SystemColor.inactiveCaption);
        lblBetween5and16.setBounds(242, 250, 179, 16);
        this.add(lblBetween5and16);
        
        newPassField = new JPasswordField();
        /**
         * Focus listener for checking the length of the new password.
         */
        newPassField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {
                if (newPassField.getPassword().length < 5 | newPassField.getPassword().length > 15)
                	lblBetween5and16.setForeground(Color.RED);
                else
                	lblBetween5and16.setForeground(SystemColor.inactiveCaption);
            }
        });
        newPassField.setBounds(250, 267, 152, 22);
        newPassField.setBorder(new MatteBorder(3, 3, 3, 3, SystemColor.activeCaption));
        newPassField.setBackground(SystemColor.activeCaption);
        this.add(newPassField);
        
        JLabel confPassLabel = new JLabel("Confirm Password");
        confPassLabel.setBounds(122, 316, 116, 16);
        confPassLabel.setForeground(SystemColor.inactiveCaption);
        confPassLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
        this.add(confPassLabel);
        
        JLabel passDontMatchLabel = new JLabel("* passwords don't match");
        passDontMatchLabel.setForeground(Color.RED);
        passDontMatchLabel.setBounds(253, 335, 152, 16);
        this.add(passDontMatchLabel);
        passDontMatchLabel.setVisible(false);
        
        confPassField = new JPasswordField();
        /**
         * Focus listener for checking if the new passwords match.
         */
        confPassField.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {}
        	
        	@Override
        	public void focusLost(FocusEvent e) {
        		if (!(String.valueOf(newPassField.getPassword()).equals(String.valueOf(confPassField.getPassword()))))
    				passDontMatchLabel.setVisible(true);
        		else 
        			passDontMatchLabel.setVisible(false);
        	}
        });
        confPassField.setBounds(250, 313, 152, 22);
        confPassField.setBorder(new MatteBorder(3, 3, 3, 3, SystemColor.activeCaption));
        confPassField.setBackground(SystemColor.activeCaption);
        this.add(confPassField);
        
        JButton saveButton = new JButton("Save");
        /**
         * Listener for updating the password by querying the database.
         */
        saveButton.addActionListener(arg0 -> {
            if (String.valueOf(oldPassField.getPassword()).equals(password) && String.valueOf(newPassField.getPassword()).equals(
                    String.copyValueOf(confPassField.getPassword()))) {
                user.updatePass(String.valueOf(newPassField.getPassword()).replace("'", "''"), User.username);
                JOptionPane.showMessageDialog(null,"Password changed!");
            }

        });
        saveButton.setBounds(277, 378, 97, 25);
        saveButton.setForeground(SystemColor.inactiveCaption);
        saveButton.setFont(new Font("Open Sans", Font.PLAIN, 20));
        saveButton.setOpaque(false);
        saveButton.setContentAreaFilled(false);
        saveButton.setBorderPainted(false);
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(saveButton);
        
        JSeparator saveSeparator = new JSeparator();
        saveSeparator.setForeground(SystemColor.text);
        saveSeparator.setBackground(SystemColor.inactiveCaption);
        saveSeparator.setOpaque(true);
        saveSeparator.setBounds(287, 402, 78, 3);
        this.add(saveSeparator);

        JLabel label = new JLabel("* passwords don't match");
        label.setForeground(Color.RED);
        label.setBounds(253, 335, 152, 16);
        this.add(label);
        label.setVisible(false);   

	}
}
