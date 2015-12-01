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
	private JTextField textField_1;

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
		
		JLabel lblGameName = new JLabel("Game Name:");
		lblGameName.setFont(new Font("Nyala", Font.ITALIC, 20));
		lblGameName.setBounds(29, 75, 103, 14);
		frame.getContentPane().add(lblGameName);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBackground(Color.LIGHT_GRAY);
		btnLogOut.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogOut.setBounds(315, 217, 89, 23);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblCreatejoinGame = new JLabel("Create/Join Game");
		lblCreatejoinGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreatejoinGame.setFont(new Font("Nyala", Font.ITALIC, 26));
		lblCreatejoinGame.setBounds(62, 25, 298, 48);
		frame.getContentPane().add(lblCreatejoinGame);
		
		JButton btnCreateGame = new JButton("Create Game");
		btnCreateGame.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnCreateGame.setBackground(Color.LIGHT_GRAY);
		btnCreateGame.setBounds(132, 110, 132, 23);
		frame.getContentPane().add(btnCreateGame);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Nyala", Font.ITALIC, 16));
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(Color.WHITE);
		textField.setBounds(132, 69, 218, 30);
		frame.getContentPane().add(textField);
		
		JButton button = new JButton("Back");
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(29, 218, 83, 23);
		frame.getContentPane().add(button);
		
		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnJoinGame.setBackground(Color.LIGHT_GRAY);
		btnJoinGame.setBounds(132, 190, 132, 23);
		frame.getContentPane().add(btnJoinGame);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Nyala", Font.ITALIC, 16));
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(132, 149, 218, 30);
		frame.getContentPane().add(textField_1);
		
		JLabel label_1 = new JLabel("Game Name:");
		label_1.setFont(new Font("Nyala", Font.ITALIC, 20));
		label_1.setBounds(29, 157, 103, 14);
		frame.getContentPane().add(label_1);
	}
}
