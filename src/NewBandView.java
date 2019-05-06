import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Rectangle;

public class NewBandView extends JFrame {

	private JFrame frame;
	private JTextField nameTxtField;
	private JTextField genreTxtField;
	private JTextField linkTxtField;
	static JComboBox<String> agentComboBox; //this is a combobox
	private String imageName = "";
	private JLabel lblImageName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBandView window = new NewBandView();
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
	public NewBandView() {
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
		
		JLabel windowTitleLabel = new JLabel("Band details");
		windowTitleLabel.setFont(new Font("Open Sans", Font.PLAIN, 16));
		windowTitleLabel.setForeground(SystemColor.inactiveCaption);
		windowTitleLabel.setBounds(12, 13, 85, 16);
		frame.getContentPane().add(windowTitleLabel);
		
		JLabel nameLabel = new JLabel("Name*");
		nameLabel.setForeground(SystemColor.inactiveCaption);
		nameLabel.setBounds(25, 61, 56, 16);
		frame.getContentPane().add(nameLabel);
		
		JLabel genreLabel = new JLabel("Genre*");
		genreLabel.setForeground(SystemColor.inactiveCaption);
		genreLabel.setBounds(25, 90, 56, 16);
		frame.getContentPane().add(genreLabel);
		
		JLabel agentLabel = new JLabel("Agent*");
		agentLabel.setForeground(SystemColor.inactiveCaption);
		agentLabel.setBounds(25, 119, 56, 16);
		frame.getContentPane().add(agentLabel);
		
		JLabel linkLabel = new JLabel("Link");
		linkLabel.setForeground(SystemColor.inactiveCaption);
		linkLabel.setBounds(25, 148, 56, 16);
		frame.getContentPane().add(linkLabel);
		
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
        nameTxtField.setBackground(SystemColor.activeCaption);
		nameTxtField.setBounds(93, 58, 116, 22);
		frame.getContentPane().add(nameTxtField);
		nameTxtField.setColumns(10);
		
		genreTxtField = new JTextField();
		genreTxtField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
        genreTxtField.setBackground(SystemColor.activeCaption);
		genreTxtField.setBounds(93, 87, 116, 22);
		frame.getContentPane().add(genreTxtField);
		genreTxtField.setColumns(10);
		
		linkTxtField = new JTextField();
		linkTxtField.setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.activeCaption));
        linkTxtField.setBackground(SystemColor.activeCaption);
		linkTxtField.setBounds(93, 145, 116, 22);
		frame.getContentPane().add(linkTxtField);
		linkTxtField.setColumns(10);
		
		agentComboBox = new JComboBox<String>();
		ArrayList<String> aList = Agent.getAgentsList();
		aList.add(0, "-Add new Agent-");
		agentComboBox.setModel(new DefaultComboBoxModel(aList.toArray()));
		agentComboBox.setBackground(SystemColor.activeCaption);
		agentComboBox.setBounds(93, 116, 116, 22);
		agentComboBox.setEditable(false);
		agentComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selected = agentComboBox.getSelectedItem();
                if(selected.toString().equals("-Add new Agent-"))
                	new NewAgentView();
			}
		});
		frame.getContentPane().add(agentComboBox);

		lblImageName = new JLabel("");
		lblImageName.setBounds(245,123, 159, 23);
		lblImageName.setForeground(SystemColor.inactiveCaption);
		lblImageName.setFont(new Font("Open Sans", Font.PLAIN, 12));
		frame.getContentPane().add(lblImageName);
		
		JButton uploadButton = new JButton();
		uploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Images","jpg","png","jpeg"));

				try {
					if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
						File file = fileChooser.getSelectedFile();
						imageName = file.getName();
						lblImageName.setText(imageName);

						Files.copy(file.toPath(), Paths.get(System.getProperty("user.dir")+"/GroupProject/src/"+Main.ARTIST_IMAGE_DIR+file.getName()),
								java.nio.file.StandardCopyOption.REPLACE_EXISTING,
								java.nio.file.StandardCopyOption.COPY_ATTRIBUTES,
								java.nio.file.LinkOption.NOFOLLOW_LINKS );
					}else {
						imageName = "No file selected!";
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		uploadButton.setBorderPainted(false);
        uploadButton.setIcon(new ImageIcon(LoginView.class.getResource("Images/UploadButton.jpg")));
		uploadButton.setBounds(245, 100, 159, 23);
		uploadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(uploadButton);
		
		JLabel imageLabel = new JLabel("Image");
		imageLabel.setForeground(SystemColor.inactiveCaption);
		imageLabel.setBounds(308, 76, 36, 16);
		frame.getContentPane().add(imageLabel);
		
		JButton addButton = new JButton("Add Band");
		addButton.setForeground(SystemColor.inactiveCaption);
		addButton.setOpaque(false);
        addButton.setContentAreaFilled(false);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setBorderPainted(false);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( (nameTxtField.getText() == null || nameTxtField.getText().isEmpty()) || (genreTxtField.getText() == null ||
						genreTxtField.getText().isEmpty()) || agentComboBox.getSelectedItem().toString().equals("-Add new Agent-")) {
					JOptionPane.showMessageDialog(null,"Please fill in all the * fields.");
				}
				else if(lblImageName.getText().equals("")){
					JOptionPane.showMessageDialog(null,"No Image Selected!");
				}
				else {
					int agentID = Agent.getAgentId(agentComboBox.getSelectedItem().toString());					
					new Band(nameTxtField.getText().replace("'", "''"),genreTxtField.getText().replace("'", "''"),linkTxtField.getText().replace("'", "''"),imageName,agentID);
					DefaultListModel performersModel = new DefaultListModel();
					for(int i = 0; i < Band.getBands().size(); i++) {
						performersModel.addElement(Band.getBands().get(i));
			        	EventOrganizerView.allPerformersList.setModel(performersModel);
			        }
					JOptionPane.showMessageDialog(null,"Band added.");
				}
			}
		});
		addButton.setBounds(236, 204, 98, 25);
		frame.getContentPane().add(addButton);
		
		JLabel backgroundLabel = new JLabel("New label");
		backgroundLabel.setIcon(new ImageIcon(NewBandView.class.getResource("/Images/rsz_silhouette-rock-concert-wallpaper1.jpg")));
		backgroundLabel.setBounds(0, 0, 432, 253);
		frame.getContentPane().add(backgroundLabel);
	}
}
