import java.awt.Cursor;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Rectangle;

/**
 * Class that creates a frame for adding new performers
 *
 */

public class NewBandView extends JFrame {

	private JFrame frame;
	private JTextField nameTxtField;
	private JTextField genreTxtField;
	private JTextField linkTxtField;
	static JComboBox<String> agentComboBox; 
	private String imageName = "";
	private JLabel lblImageName;


	/**
	 * Create the frame.
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

		Agent agent = new Agent();

		JLabel windowTitleLabel = new JLabel("Band details");
		windowTitleLabel.setFont(new Font("Open Sans", Font.PLAIN, 16));
		windowTitleLabel.setForeground(SystemColor.inactiveCaption);
		windowTitleLabel.setBounds(12, 13, 98, 16);
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
        //Dispose the frame without adding a band
        cancelButton.addActionListener(e -> frame.dispose());
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
		nameTxtField.setBorder(new MatteBorder(3, 3, 3, 3, SystemColor.activeCaption));
        nameTxtField.setBackground(SystemColor.activeCaption);
		nameTxtField.setBounds(93, 58, 116, 22);
		frame.getContentPane().add(nameTxtField);
		nameTxtField.setColumns(10);
		
		genreTxtField = new JTextField();
		genreTxtField.setBorder(new MatteBorder(3, 3, 3, 3, SystemColor.activeCaption));
        genreTxtField.setBackground(SystemColor.activeCaption);
		genreTxtField.setBounds(93, 87, 116, 22);
		frame.getContentPane().add(genreTxtField);
		genreTxtField.setColumns(10);
		
		linkTxtField = new JTextField();
		linkTxtField.setBorder(new MatteBorder(3, 3, 3, 3, SystemColor.activeCaption));
        linkTxtField.setBackground(SystemColor.activeCaption);
		linkTxtField.setBounds(93, 145, 116, 22);
		frame.getContentPane().add(linkTxtField);
		linkTxtField.setColumns(10);
		
		
		/*
		Static combobox to be altered from other frames.
		Populated with existing agents and the option to add a new one
		 */
		agentComboBox = new JComboBox<>();
		ArrayList<String> aList = agent.getAgentsList();
		aList.add(0, "-Add new Agent-");
		agentComboBox.setModel(new DefaultComboBoxModel(aList.toArray()));
		agentComboBox.setBackground(SystemColor.activeCaption);
		agentComboBox.setBounds(93, 116, 116, 22);
		agentComboBox.setEditable(false);
		//if "add new agent" is selected, load the NewAgentView to add a new agent
		agentComboBox.addActionListener(e -> {
			Object selected = agentComboBox.getSelectedItem();
			if(selected.toString().equals("-Add new Agent-"))
				new NewAgentView();
		});
		frame.getContentPane().add(agentComboBox);

		lblImageName = new JLabel("");
		lblImageName.setBounds(245,123, 159, 23);
		lblImageName.setForeground(SystemColor.inactiveCaption);
		lblImageName.setFont(new Font("Open Sans", Font.PLAIN, 12));
		frame.getContentPane().add(lblImageName);


		//Loads a JFileChooser , limited on Images and saves the selected image to the project directory
		JButton uploadButton = new JButton();
		uploadButton.addActionListener(arg0 -> {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Images","jpg","png","jpeg");
			fileChooser.setFileFilter(filter);
			try {
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					if(filter.accept(file)){
						imageName = file.getName();
						lblImageName.setText(imageName);

						Files.copy(file.toPath(),Paths.get(System.getProperty("user.dir")+"/"+Main.EVENT_IMAGE_DIR+file.getName()),
								StandardCopyOption.REPLACE_EXISTING,
								StandardCopyOption.COPY_ATTRIBUTES,
								LinkOption.NOFOLLOW_LINKS );}
					else{JOptionPane.showMessageDialog(null,"Invalid file type");}
				}else {
					if(lblImageName.getText().equals("")){
						lblImageName.setText("No file selected!");}
				}
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		});
		uploadButton.setBorderPainted(false);
        uploadButton.setIcon(new ImageIcon(Main.IMAGE_DIR+"UploadButton.jpg"));
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
        
        /*
         * Inserts all the details of the new artist into the database after checking if some requirements are met or not.
         * Such as null name, null genre, the agent combobox set to "Add new Agent", or the image is not uploaded.
         * After completing the process, the JList's default model will be modified to include the new artist.
         */
		addButton.addActionListener(arg0 -> {
			if ( (nameTxtField.getText() == null || nameTxtField.getText().isEmpty()) || (genreTxtField.getText() == null ||
					genreTxtField.getText().isEmpty()) || agentComboBox.getSelectedItem().toString().equals("-Add new Agent-")) {
				JOptionPane.showMessageDialog(null,"Please fill in all the * fields.");
			}
			else if(lblImageName.getText().equals("")){
				JOptionPane.showMessageDialog(null,"No Image Selected!");
			}
			else {
				int agentID = agent.getAgentId(agentComboBox.getSelectedItem().toString().replace("'", "''"));
				Band band =new Band(nameTxtField.getText().replace("'", "''"),genreTxtField.getText().replace("'", "''"),linkTxtField.getText().replace("'", "''"),imageName,agentID);
				DefaultListModel performersModel = new DefaultListModel();
				for(int i = 0; i < band.getAllBands().size(); i++) {
					performersModel.addElement(band.getAllBands().get(i));
					OrganizerAddEventView.allPerformersList.setModel(performersModel);
				}
				frame.dispose();
				JOptionPane.showMessageDialog(null,"Band added.");

			}
		});
		addButton.setBounds(236, 204, 98, 25);
		frame.getContentPane().add(addButton);
		
		JLabel backgroundLabel = new JLabel("New label");
		backgroundLabel.setIcon(new ImageIcon(Main.IMAGE_DIR+"Silhouette-Rock-Concert-Wallpaper1.jpg"));
		backgroundLabel.setBounds(0, 0, 432, 253);
		frame.getContentPane().add(backgroundLabel);
	}
}
