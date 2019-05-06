import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Color;

public class FrameTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTest window = new FrameTest();
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
	public FrameTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(222, 115, 175, 90);
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		for(int x = 0; x<10;x++) {
			JPanel panelll = new JPanel();
			panelll.setBackground(Color.CYAN);
			panelll.setPreferredSize(new Dimension(50,50));
			JLabel label1421 = new JLabel("Cacat");
			panelll.add(label1421);
			panel.add(panelll);
		}
		
//		JPanel panel_1 = new JPanel();
//		panel_1.setBackground(Color.CYAN);
//		panel_1.setPreferredSize(new Dimension(50, 50));
//		panel.add(panel_1);
//		
//		JPanel panel_2 = new JPanel();
//		panel_2.setBackground(Color.MAGENTA);
//		panel_2.setPreferredSize(new Dimension(50, 50));
//		panel.add(panel_2);
//		
//		JPanel panel_3 = new JPanel();
//		panel.add(panel_3);
//		
//		JPanel panel_4 = new JPanel();
//		panel.add(panel_4);
		
	}
}
