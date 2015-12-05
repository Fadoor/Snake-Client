import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientLogin extends JPanel {

	private JTextField textField;
	private JLabel lblUsername;
	private JLabel label;
	private JPasswordField passwordField;
	private JButton btnLogIn;
	private SnakeMain client;
	
	/**
	 * Create the panel.
	 */
	public ClientLogin(SnakeMain client) {
		this.client = client;
		
		this.setFont(new Font("Segoe Script", Font.PLAIN, 11));
		this.setBackground(new Color(192, 192, 192));
		this.setLayout(null);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome To The Snakepit");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Nyala", Font.ITALIC, 26));
		lblWelcomeToThe.setBounds(62, 25, 298, 48);
		this.add(lblWelcomeToThe);
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(Color.WHITE);
		textField.setFont(new Font("Nyala", Font.ITALIC, 16));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(103, 101, 218, 30);
		this.add(textField);
		textField.setColumns(10);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Nyala", Font.ITALIC, 18));
		lblUsername.setBounds(103, 84, 98, 19);
		this.add(lblUsername);
		
		label = new JLabel("Password:");
		label.setFont(new Font("Nyala", Font.ITALIC, 18));
		label.setBounds(103, 142, 98, 19);
		this.add(label);

		JCheckBox chckbxRememberMe = new JCheckBox("Remember me?");
		chckbxRememberMe.setFont(new Font("Nyala", Font.ITALIC, 14));
		chckbxRememberMe.setBackground(Color.LIGHT_GRAY);
		chckbxRememberMe.setBounds(103, 198, 124, 23);
		this.add(chckbxRememberMe);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setBounds(103, 158, 218, 30);
		this.add(passwordField);
		
		btnLogIn = new JButton("Log in");
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientLogin This = (ClientLogin) (e.getComponent().getParent());
				
				This.client.changePage(new ClientMenu(This.client));
				
			}
		});
		btnLogIn.setBackground(Color.LIGHT_GRAY);
		btnLogIn.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogIn.setBounds(103, 228, 89, 23);
		this.add(btnLogIn);
	}
}
