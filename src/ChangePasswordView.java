import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class ChangePasswordView extends JPanel {

	private JPasswordField oldPassField;
	private JPasswordField newPassField;
	private JPasswordField confPassField;

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
        oldPassField.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {}
        	
        	@Override
        	public void focusLost(FocusEvent e) {
        		if (!(User.getData(User.username,"Pass").equals(String.valueOf(oldPassField.getPassword()))))
        			incorrectPassLabel.setVisible(true);
        		else
        			incorrectPassLabel.setVisible(false);
        	}
        });
        oldPassField.setBounds(250, 222, 152, 22);
        oldPassField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
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
        newPassField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
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
        confPassField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
        confPassField.setBackground(SystemColor.activeCaption);
        this.add(confPassField);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if (String.valueOf(oldPassField.getPassword()).equals(User.getData(User.username,"Pass")) && String.valueOf(newPassField.getPassword()).equals(
        				String.copyValueOf(confPassField.getPassword()))) {
    				User.updatePass(newPassField.getPassword().toString().replace("'", "''"), User.username);
    				JOptionPane.showMessageDialog(null,"Password changed!");
        		}
        		
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


        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(250, 203, 56, 16);
        this.add(lblNewLabel);
        
        
	}
}
