import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class ClientNewGame extends JPanel {

	private JTextField textField;
	private SnakeMain client;

	/**
	 * Create the panel.
	 */
	public ClientNewGame(SnakeMain client) {
		this.client = client;
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		
		JLabel lblGameName = new JLabel("Game Name:");
		lblGameName.setFont(new Font("Nyala", Font.ITALIC, 20));
		lblGameName.setBounds(39, 90, 103, 14);
		this.add(lblGameName);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent());
				
				This.client.changePage(new ClientLogin(This.client));
			}
		});
		btnLogOut.setBackground(Color.LIGHT_GRAY);
		btnLogOut.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogOut.setBounds(338, 254, 90, 23);
		this.add(btnLogOut);
		
		JLabel lblCreatejoinGame = new JLabel("Create/Join Game");
		lblCreatejoinGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreatejoinGame.setFont(new Font("Nyala", Font.ITALIC, 26));
		lblCreatejoinGame.setBounds(62, 25, 298, 48);
		this.add(lblCreatejoinGame);
		
		JButton btnCreateGame = new JButton("Create Game");
		btnCreateGame.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnCreateGame.setBackground(Color.LIGHT_GRAY);
		btnCreateGame.setBounds(142, 125, 132, 23);
		this.add(btnCreateGame);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Nyala", Font.ITALIC, 16));
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(Color.WHITE);
		textField.setBounds(142, 84, 218, 30);
		this.add(textField);
		
		JButton button = new JButton("Back");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent());
				
				This.client.changePage(new ClientMenu(This.client));
			}
		});
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(10, 254, 83, 23);
		this.add(button);
		
		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnJoinGame.setBackground(Color.LIGHT_GRAY);
		btnJoinGame.setBounds(142, 205, 132, 23);
		this.add(btnJoinGame);
		
		JLabel label_1 = new JLabel("Game Name:");
		label_1.setFont(new Font("Nyala", Font.ITALIC, 20));
		label_1.setBounds(39, 172, 103, 14);
		this.add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(142, 164, 218, 30);
		add(comboBox);
	}
}
