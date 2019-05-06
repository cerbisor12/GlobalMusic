import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView {

	private JFrame frame;
    JTextField usernameField;
    JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView window = new LoginView();
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
    public LoginView() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Global Music");
        frame.setBounds(100, 100, 1280, 690);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);

        usernameField = new JTextField();
        usernameField.setBackground(SystemColor.activeCaption);
        usernameField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
        usernameField.setBounds(654, 202, 247, 31);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBackground(SystemColor.activeCaption);
        passwordField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
        passwordField.setBounds(654, 284, 247, 31);
        frame.getContentPane().add(passwordField);

        JLabel lblIncorrectPassword = new JLabel("Incorrect Password");
        lblIncorrectPassword.setForeground(Color.RED);
        lblIncorrectPassword.setBounds(684, 328, 165, 23);
        frame.getContentPane().add(lblIncorrectPassword);
        lblIncorrectPassword.setVisible(false);

        JLabel lblIncorrectUsername = new JLabel("Incorrect Username");
        lblIncorrectUsername.setForeground(Color.RED);
        lblIncorrectUsername.setBounds(684, 244, 165, 23);
        frame.getContentPane().add(lblIncorrectUsername);
        lblIncorrectUsername.setVisible(false);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setUsername(usernameField.getText().replace("'", "''"));
                user.setPassword(new String(passwordField.getPassword()));
                if (user.loginCheck()) {
                    if (User.getData(User.username,"Type").equalsIgnoreCase("Customer") ||
                            User.getData(User.username,"Type").equalsIgnoreCase("organization"))
                    {
                        new HomePageView();
                        frame.setVisible(false);
                    }
                    else if (User.getData(User.username,"Type").equalsIgnoreCase("organizer"))
                    {
                        new EventOrganizerView();
                        frame.setVisible(false);
                    }
                    else{
                        new AdminView();
                        frame.setVisible(false);
                    }
                } else if (user.checkFieldInDB("Username",User.username)) {
                    lblIncorrectUsername.setVisible(false);
                    lblIncorrectPassword.setVisible(true);
                    passwordField.setText("");
                } else if (!user.checkFieldInDB("Username",User.username)) {

                    lblIncorrectPassword.setVisible(false);
                    lblIncorrectUsername.setVisible(true);
                    usernameField.setText("");}
            }
        });
        btnLogin.setBounds(796, 360, 120, 40);
        btnLogin.setForeground(SystemColor.inactiveCaption);
        btnLogin.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setOpaque(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setContentAreaFilled(false);
        frame.getContentPane().add(btnLogin);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(SystemColor.inactiveCaption);
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setFont(new Font("Open Sans", Font.BOLD, 16));
        lblUsername.setBounds(488, 191, 122, 48);
        frame.getContentPane().add(lblUsername);

        JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setForeground(SystemColor.inactiveCaption);
        lblNewLabel_1.setBackground(Color.BLACK);
        lblNewLabel_1.setFont(new Font("Open Sans", Font.BOLD, 16));
        lblNewLabel_1.setBounds(488, 278, 122, 39);
        frame.getContentPane().add(lblNewLabel_1);




        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterView();
                frame.setVisible(false);
            }
        });
        btnRegister.setForeground(SystemColor.inactiveCaption);
        btnRegister.setFont(new Font("Open Sans", Font.PLAIN, 20));
        btnRegister.setBounds(641, 360, 120, 40);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.setOpaque(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setContentAreaFilled(false);
        frame.getContentPane().add(btnRegister);




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


        JSeparator separator = new JSeparator();
        separator.setBackground(SystemColor.inactiveCaption);
        separator.setForeground(SystemColor.inactiveCaption);
        separator.setBounds(652, 392, 100, 3);
        separator.setOpaque(true);
        frame.getContentPane().add(separator);



        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(SystemColor.inactiveCaption);
        separator_1.setForeground(SystemColor.inactiveCaption);
        separator_1.setBounds(811, 392, 90, 3);
        separator_1.setOpaque(true);
        frame.getContentPane().add(separator_1);
        
        
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

        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(LoginView.class.getResource("Images/Logo.jpg")));
        lblLogo.setBounds(186, 583, 200, 96);
        frame.getContentPane().add(lblLogo);

        JLabel lblImageLabel = new JLabel("Image");
        lblImageLabel.setFont(new Font("Open Sans", Font.PLAIN, 11));
        lblImageLabel.setForeground(Color.BLACK);
        lblImageLabel.setIcon(new ImageIcon(LoginView.class.getResource("Images/Silhouette-Rock-Concert-Wallpaper1.jpg")));
        lblImageLabel.setBounds(0, 0, 1297, 693);
        frame.getContentPane().add(lblImageLabel);
        
        frame.getRootPane().setDefaultButton(btnLogin);

    }
}
