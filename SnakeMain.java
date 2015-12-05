import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnakeMain window = new SnakeMain();
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
	public SnakeMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel JPanel = new ClientLogin(this);
		frame.getContentPane().add(JPanel);
		
	}
	
	public void changePage(JPanel newJPanel) { // Skifte Panel metode
		Container panel = frame.getContentPane();
		panel.removeAll();
		panel.add(newJPanel);
		panel.repaint();
		panel.revalidate();
	}

}
