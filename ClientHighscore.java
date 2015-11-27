import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;

public class ClientHighscore {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientHighscore window = new ClientHighscore();
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
	public ClientHighscore() {
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
		
		JLabel lblNewLabel = new JLabel("My Highscore:");
		lblNewLabel.setFont(new Font("Nyala", Font.ITALIC, 15));
		lblNewLabel.setBounds(40, 48, 83, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblGameHighscores = new JLabel("Game Highscores:");
		lblGameHighscores.setFont(new Font("Nyala", Font.ITALIC, 15));
		lblGameHighscores.setBounds(163, 48, 107, 26);
		frame.getContentPane().add(lblGameHighscores);
		
		JLabel lblGlobalHighscore = new JLabel("Global Highscore:");
		lblGlobalHighscore.setFont(new Font("Nyala", Font.ITALIC, 15));
		lblGlobalHighscore.setBounds(299, 48, 105, 26);
		frame.getContentPane().add(lblGlobalHighscore);
		
		JList list = new JList();
		list.setBounds(152, 79, 128, 136);
		frame.getContentPane().add(list);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBackground(Color.LIGHT_GRAY);
		btnLogOut.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogOut.setBounds(321, 217, 83, 23);
		frame.getContentPane().add(btnLogOut);
		
		JButton button = new JButton("Back");
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(29, 218, 83, 23);
		frame.getContentPane().add(button);
	}
}
