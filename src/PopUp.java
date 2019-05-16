import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 * This class is for the admin, it gives him the option to edit users accounts.
 * The pop up uses the MyAccountView class.
 *
 */
public class PopUp extends JFrame {
	
	/**
	 * This constructs the pop up for the admin edit accounts option.
	 */
	public PopUp() {
        getContentPane().setForeground(new Color(128, 128, 128));
        setResizable(false);
        setTitle("Global Music");
        setBounds(100, 100, 1280, 690);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        MyAccountView panel = new MyAccountView();
        panel.setBounds(0, 0, 0, 0);

        //Alter the MyAccountView to show cancel button and dispose frame on save
        panel.saveButton.addActionListener(e -> dispose());
        panel.cancelButton.setVisible(true);
        panel.cancelBtnSeparator.setVisible(true);
        panel.cancelButton.addActionListener(e -> dispose());
        getContentPane().add(panel);
        setVisible(true);

        //Exit the application
        JButton btnExitButton = new JButton("X");
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
        this.getContentPane().add(btnExitButton);
        
        //Minimize the application
        JButton minimizeButton = new JButton("___");
        minimizeButton.addActionListener(arg0 -> {
        });
        minimizeButton.setForeground(SystemColor.inactiveCaption);
        minimizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                setExtendedState(JFrame.ICONIFIED);
            }
        });
        minimizeButton.setOpaque(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        minimizeButton.setBounds(1154, 20, 63, 38);
        this.getContentPane().add(minimizeButton);
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(Main.IMAGE_DIR+"Logo.jpg"));
        lblLogo.setBounds(186, 583, 200, 96);
        this.getContentPane().add(lblLogo);


        JLabel lblImageLabel = new JLabel("Image");
        lblImageLabel.setForeground(Color.BLACK);
        lblImageLabel.setIcon(new ImageIcon(Main.IMAGE_DIR+"Silhouette-Rock-Concert-Wallpaper1.jpg"));
        lblImageLabel.setBounds(0, 0, 1280, 690);
        this.getContentPane().add(lblImageLabel);
        
        
	}
}
