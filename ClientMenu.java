import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;

public class ClientMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMenu window = new ClientMenu();
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
	public ClientMenu() {
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
		
		JLabel lblWelcomeToThe = new JLabel("Welcome Name");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Nyala", Font.ITALIC, 26));
		lblWelcomeToThe.setBounds(62, 25, 298, 48);
		frame.getContentPane().add(lblWelcomeToThe);
		
		JButton button = new JButton("Start new game");
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBounds(141, 84, 153, 23);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("Highscore");
		button_1.setFont(new Font("Nyala", Font.ITALIC, 18));
		button_1.setBounds(141, 116, 153, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Delete game");
		button_2.setFont(new Font("Nyala", Font.ITALIC, 18));
		button_2.setBounds(141, 150, 153, 23);
		frame.getContentPane().add(button_2);
		
		JButton btnLogUd = new JButton("Log ud");
		btnLogUd.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogUd.setBounds(322, 217, 82, 23);
		frame.getContentPane().add(btnLogUd);
	}
}
