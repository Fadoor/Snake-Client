import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ClientNewGame {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientNewGame window = new ClientNewGame();
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
	public ClientNewGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Nyala", Font.ITALIC, 15));
		textField.setBounds(170, 100, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblGameName = new JLabel("Game Name:");
		lblGameName.setBounds(99, 103, 61, 14);
		frame.getContentPane().add(lblGameName);
		
		JButton button = new JButton("Log ud");
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBounds(322, 217, 82, 23);
		frame.getContentPane().add(button);
		
		JLabel label = new JLabel("Create Game");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Nyala", Font.ITALIC, 26));
		label.setBounds(62, 25, 298, 48);
		frame.getContentPane().add(label);
	}
}
