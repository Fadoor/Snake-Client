import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class ClientLogin {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblUsername;
	private JLabel label;
	private JPasswordField passwordField;
	private JButton btnLogIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogin window = new ClientLogin();
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
	public ClientLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Segoe Script", Font.PLAIN, 11));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome To The Snakepit");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Nyala", Font.ITALIC, 26));
		lblWelcomeToThe.setBounds(62, 25, 298, 48);
		frame.getContentPane().add(lblWelcomeToThe);
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(Color.WHITE);
		textField.setFont(new Font("Nyala", Font.ITALIC, 16));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(103, 101, 218, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Nyala", Font.ITALIC, 18));
		lblUsername.setBounds(103, 84, 98, 19);
		frame.getContentPane().add(lblUsername);
		
		label = new JLabel("Password:");
		label.setFont(new Font("Nyala", Font.ITALIC, 18));
		label.setBounds(103, 142, 98, 19);
		frame.getContentPane().add(label);
		
		JCheckBox chckbxRememberMe = new JCheckBox("Remember me?");
		chckbxRememberMe.setFont(new Font("Nyala", Font.ITALIC, 14));
		chckbxRememberMe.setBackground(Color.LIGHT_GRAY);
		chckbxRememberMe.setBounds(103, 198, 124, 23);
		frame.getContentPane().add(chckbxRememberMe);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setBounds(103, 158, 218, 30);
		frame.getContentPane().add(passwordField);
		
		btnLogIn = new JButton("Log in");
		btnLogIn.setBackground(Color.LIGHT_GRAY);
		btnLogIn.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogIn.setBounds(103, 228, 89, 23);
		frame.getContentPane().add(btnLogIn);
	}
}
